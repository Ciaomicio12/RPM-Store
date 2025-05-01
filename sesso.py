#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import os
import random
from datetime import datetime, timedelta
from git import Repo, Actor

# ✅ CONFIGURAZIONE
AUTHORS = [
    {"name": "carmi", "email": "c.aiello12@studenti.unisa.it"},
    {"name": "ggrauso7429", "email": "g.grauso1@studenti.unisa.it"},
]

START_DATE = "2025-05-01T00:00:00"
END_DATE = "2025-06-05T23:59:59"
NEW_REMOTE_REPO = "https://github.com/Ciaomicio12/RPM-Store"

start_dt = datetime.fromisoformat(START_DATE)
end_dt = datetime.fromisoformat(END_DATE)

repo = Repo(".")

# Trova il branch attuale
branch_name = repo.git.rev_parse("--abbrev-ref", "HEAD")
if branch_name == "HEAD":
    # Se HEAD è staccata
    branch_name = repo.git.symbolic_ref("refs/remotes/origin/HEAD").split("/")[-1]
    print(f"⚠️ HEAD staccata. Userò il branch remoto: {branch_name}")

print(f"📌 Branch di partenza: {branch_name}")

# Commit in ordine cronologico
commits = list(repo.iter_commits(branch_name, reverse=True))
print(f"🚀 Inizio rewriting di {len(commits)} commit...")

# Genera N date random ordinate
def generate_ordered_dates(start_dt, end_dt, n):
    timestamps = sorted(random.randint(int(start_dt.timestamp()), int(end_dt.timestamp())) for _ in range(n))
    return [datetime.fromtimestamp(ts) for ts in timestamps]

ordered_dates = generate_ordered_dates(start_dt, end_dt, len(commits))

new_commits = {}

# Usa un orphan branch temporaneo
repo.git.checkout("--orphan", "rewrite_tmp")
repo.git.rm("-rf", ".")

for i, (commit, new_date) in enumerate(zip(commits, ordered_dates)):
    print(f"🔄 Rewriting commit {i+1}/{len(commits)}: {commit.hexsha[:7]}")

    author_data = random.choice(AUTHORS)
    author = Actor(author_data["name"], author_data["email"])

    new_date_str = new_date.strftime("%Y-%m-%dT%H:%M:%S")

    repo.git.checkout(commit)
    repo.git.add(all=True)

    # Mappa i parent commits riscritti
    new_parents = [new_commits[p.hexsha] for p in commit.parents if p.hexsha in new_commits]

    new_commit = repo.index.commit(
        commit.message,
        author=author,
        committer=author,
        author_date=new_date_str,
        commit_date=new_date_str,
        parent_commits=new_parents
    )

    new_commits[commit.hexsha] = new_commit

print("✅ Rewriting completato.")

# Sovrascrivi il branch di partenza
repo.git.branch("-f", branch_name, new_commit.hexsha)
repo.git.checkout(branch_name)

# Rimuovi il branch temporaneo se esiste
if "rewrite_tmp" in repo.git.branch("--list").split():
    repo.git.branch("-D", "rewrite_tmp")

# Aggiorna il remote
repo.git.remote("set-url", "origin", NEW_REMOTE_REPO)
print("✅ Nuovo remote impostato.")
print("⚠️ Esegui manualmente il push forzato:")
print(f"    git push --force origin {branch_name}")
print("🎉 Tutto fatto, pronto per il push!")
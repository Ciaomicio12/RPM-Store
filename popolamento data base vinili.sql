-- Inserimento dati nella tabella "utente"

INSERT INTO utente (username, password, nome, cognome, sesso, email, admin, disabled)
VALUES ('utente1', 'password1', 'Tommaso', 'Spataro', 'M', 'utente1@email.com', 0, 1),
       ('utente2', 'password2', 'Alfredo', 'Amendola', 'M', 'utente2@email.com', 0, 1),
       ('admin1', 'adminpass', 'Admin', 'Admin', 'F', 'admin@example.com', 1, 1);

-- Inserimento dati nella tabella "vinile"

INSERT INTO vinile (EAN, anno_pubblicazione, prezzo, numero_disponibili, autore, titolo, copertina)
VALUES ('EAN001', 1983, 20, 10, 'Mettalica', 'Master of Puppez', ''),
       ('EAN002', 1956, 15, 5, 'Elvis Presly', 'Elvis Presly', ''),
       ('EAN003', 1956, 25, 8, 'Nirvana', 'Never Minds', '');

-- Inserimento dati nella tabella "genere"

INSERT INTO genere (nome)
VALUES ('Trash Mettalica'),
       ('Rock and Roll'),
       ('Grunge');

-- Inserimento dati nella tabella "ordine"
INSERT INTO ordine (id_utente, oradiordine, totale)
VALUES (1, '2023-06-28 10:00:00', 50),
       (2, '2023-06-28 11:30:00', 35),
       (1, '2023-06-28 15:45:00', 40);


-- Inserimento dati nella tabella "vinile_in_ordine"

INSERT INTO vinile_in_ordine (quantita, prezzoacq)
VALUES (2, 40.0),
       (1, 15.0),
       (3, 60.0);

-- Inserimento dati nella tabella "vinile_genere"

INSERT INTO vinile_genere (EAN, id)
VALUES ('EAN001', 1),
       ('EAN001', 2),
       ('EAN002', 3),
       ('EAN003', 4);
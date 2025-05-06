-- Inserimento dati nella tabella "utente"

INSERT INTO utente (username, password, nome, cognome, sesso, email, admin, disabled)
VALUES ('utente1', 'password1', 'Tommaso', 'Spataro', 'M', 'utente1@email.com', 0, 1),
       ('utente2', 'password2', 'Alfredo', 'Amendola', 'M', 'utente2@email.com', 0, 1),
       ('admin1', 'adminpass', 'Admin', 'Admin', 'F', 'admin@example.com', 1, 1);

-- Inserimento dati nella tabella "vinile"

INSERT INTO vinile (EAN, anno_pubblicazione, prezzo, numero_disponibili, autore, anno, titolo, copertina)
VALUES ('9781234567890', 1986, 20, 10, 'Metallica', 'Master of Puppets', 'copertina1.jpg'),
       ('9780987654321', 1956, 25, 5, 'Elvis Presley', 'Elvis Presley', 'copertina2.jpg'),
       ('9785432167890', 1991, 18, 8, 'Nirvana', 'Nevermind', 'copertina3.jpg');

-- Inserimento dati nella tabella "genere"

INSERT INTO genere (nome)
VALUES ('Thrash Metal'),
       ('Rock & Roll'),
       ('Grunge');

-- Inserimento dati nella tabella "ordine"

INSERT INTO ordine (id_utente, oradiordine, totale, stato)
VALUES (1, '2023-07-08 10:30:00', 50, 'P'),
       (2, '2023-07-07 15:45:00', 35, 'C'),
       (3, '2023-07-06 09:15:00', 40, 'P');


-- Inserimento dati nella tabella "vinile_in_ordine"

INSERT INTO vinile_in_ordine (ordine_id, quantita, vinile_ean, prezzoacq)
VALUES (1, 2, '9781234567890', 40),
       (1, 1, '9785432167890', 18),
       (2, 3, '9780987654321', 75),
       (3, 1, '9785432167890', 18);

-- Inserimento dati nella tabella "vinile_genere"

INSERT INTO vinile_genere (EAN, id)
VALUES ('9781234567890', 1),
       ('9780987654321', 2),
       ('9785432167890', 3);




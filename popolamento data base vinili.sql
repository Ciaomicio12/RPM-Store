-- Inserimento dati nella tabella "utente"
INSERT INTO utente (username, password, nome, cognome, sesso, email, admin, disabled)
VALUES ('utente1', 'password1', 'Gianluca', 'Grauso', 'M', 'utente1@email.com', 0, 1),
       ('utente2', 'password2', 'Carmine', 'Aiello', 'M', 'utente2@email.com', 0, 1),
       ('admin1', 'adminpass', 'Admin', 'Admin', 'F', 'admin@example.com', 1, 1);

-- Inserimento dati nella tabella "vinile" (originali)
INSERT INTO vinile (EAN, anno_pubblicazione, prezzo, numero_disponibili, autore, titolo, copertina)
VALUES ('9781234567890', 1983, 20, 10, 'Mettalica', 'Master of Puppez', 'copertina1.jpg'),
       ('9780987654321', 1956, 25, 5, 'Elvis Presly', 'Elvis Presly', 'copertina2.jpg'),
       ('9785432167890', 1956, 18, 8, 'Nirvana', 'Never Minds', 'copertina3.jpg');

-- Inserimento dati nella tabella "vinile" (nuovi, copertina unknown)
INSERT INTO vinile (EAN, anno_pubblicazione, prezzo, numero_disponibili, autore, titolo, copertina)
VALUES ('9789876543210', 2000, 30, 6, 'Pink Floyd', 'The Wall', 'copertina4.jpg'),
       ('9785678901234', 1991, 22, 7, 'Pearl Jam', 'Ten', 'copertina5.jpg'),
       ('9783456789012', 1985, 26, 9, 'Dire Straits', 'Brothers in Arms', 'copertina6.jpg');

-- Inserimento dati nella tabella "genere" (aggiunti nuovi generi)
INSERT INTO genere (nome)
VALUES ('Trash Mettalica'),
       ('Rock and Roll'),
       ('Grunge'),
       ('Progressive Rock'),
       ('Alternative Rock'),
       ('Classic Rock');

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

-- Inserimento dati nella tabella "vinile_genere" (tutti i vinili matchati con generi)
INSERT INTO vinile_genere (EAN, id)
VALUES ('9781234567890', 1),
       ('9780987654321', 2),
       ('9785432167890', 3),
       ('9789876543210', 4),
       ('9785678901234', 5),
       ('9783456789012', 6);
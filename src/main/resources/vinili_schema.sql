DROP DATABASE IF EXISTS progetto;
CREATE DATABASE progetto;
USE progetto;

create TABLE indirizzo
(
    id            integer auto_increment primary key,
    strada        varchar(128) NOT NULL,
    citta         varchar(128) NOT NULL,
    cap           char(5)      NOT NULL,
    numero_civico varchar(12)  NOT NULL,
    telefono      varChar(15)  NOT NULL
);

CREATE TABLE utente
(
    indirizzo integer,
    id        int(11)      NOT NULL AUTO_INCREMENT,
    username  varchar(45)  NOT NULL,
    password  char(40)     NOT NULL,
    nome      varchar(100) NOT NULL,
    cognome   varchar(100) NOT NULL,
    sesso     varchar(7)   NOT NULL,
    email     varchar(100) NOT NULL,
    admin     tinyint(1)   NOT NULL,
    disabled  tinyint(1) default 0,
    PRIMARY KEY (id),
    UNIQUE KEY (username),
    UNIQUE KEY (email),
    foreign key (indirizzo) references indirizzo (id) on update cascade on delete set null
);

CREATE TABLE vinile
(
    EAN                varchar(14),
    anno_pubblicazione int         not null,
    prezzo             float       not null,
    numero_disponibili int,
    autore             varchar(50),
    titolo             text        not null,
    copertina          varchar(50) not null unique,
    primary key (EAN)
);

CREATE TABLE genere
(
    id   int primary key auto_increment,
    nome text not null
);

create table ordine
(
    indirizzo   int          not null,
    id          int primary key auto_increment,
    id_utente   int(11),
    oradiordine varchar(100) not null,
    totale      float,
    stato       varchar(1) DEFAULT 'P',
    FOREIGN KEY (id_utente) REFERENCES utente (id) on update cascade on delete cascade,
    FOREIGN KEY (indirizzo) REFERENCES indirizzo (id) on delete restrict on update cascade
);

create table vinile_in_ordine
(
    ordine_id  int,
    quantita   int,
    vinile_ean char(14),
    prezzoacq  DECIMAL(5, 2),
    FOREIGN KEY (ordine_id) REFERENCES ordine (id) on update cascade on delete cascade,
    FOREIGN KEY (vinile_ean) REFERENCES vinile (EAN) on update cascade on delete cascade
);

CREATE TABLE vinile_genere
(
    EAN varchar(14),
    id  int,
    FOREIGN KEY (id) REFERENCES genere (id) on update cascade on delete cascade,
    FOREIGN KEY (EAN) REFERENCES vinile (EAN) on update cascade on delete cascade,
    primary key (EAN, id)
);

-- Inserimento dati "indirizzo"

INSERT INTO indirizzo (strada, citta, cap, numero_civico, telefono)
VALUES ('Via Roma', 'Roma', '00100', '10', '3248955432'),
       ('Via del Corso ', 'Roma', '00100', '30', '2334777889'),
       ('Via Montenapoleone', 'Milano', '20121', '100b', '5454663345'),
       ('Via Foria', 'Napoli', '80185', '5c', '54546878678');

-- Inserimento dati nella tabella "utente"

INSERT INTO utente (indirizzo, username, password, nome, cognome, sesso, email, admin, disabled)
VALUES (1, 'utente1', SHA1('password1'), 'Tommaso', 'Spataro', 'M', 'utente1@email.com', 0, 0),
       (3, 'utente2', SHA1('password2'), 'Alfredo', 'Amendola', 'M', 'utente3@email.com', 0, 0),
       (2, 'AbSoluTe', SHA1('password2'), 'Angelo', 'Spataro', 'M', 'utente2@email.com', 0, 0),
       (4, 'admin1', SHA1('adminpass'), 'Admin', 'Admin', 'F', 'admin@example.com', 1, 0);

-- Inserimento dati nella tabella "vinile"

INSERT INTO vinile (EAN, anno_pubblicazione, prezzo, numero_disponibili, autore, titolo, copertina)
VALUES ('602557382594', 1986, 34.99, 10, 'Metallica', 'Master of Puppets', 'Master_of_Puppets.jpg'),
       ('194397971514', 1956, 21.99, 5, 'Elvis Presley', 'Elvis Presley', 'Elvis_Presley.jpg'),
       ('720642442517', 1991, 29.90, 8, 'Nirvana', 'Nevermind', 'Nevermind.jpg'),
       ('93624941422', 2000, 24.51, 15, 'Linkin Park', 'Hybrid Theory', 'Hybrid_Theory.jpg'),
       ('93624915959', 2003, 28, 5, 'Linkin Park', 'Meteora', 'Meteora.jpg'),
       ('93624913245', 2017, 29.99, 20, 'Linkin Park', 'One More Light', 'One_More_Light.jpg'),
       ('93624935711', 2014, 40.84, 5, 'Linkin Park', 'The Hunting Party', 'The_Hunting_Party.jpg'),
       ('93624921127', 2012, 28.69, 5, 'Linkin Park', 'Living Things', 'Living_Things.jpg'),
       ('93624963189', 2010, 37.09, 3, 'Linkin Park', 'A Thousand Suns', 'A_Thousand_Suns.jpg'),
       ('93624998105', 2007, 28, 5, 'Linkin Park', 'Minutes to Midnight', 'Minutes_to_Midnight.jpg'),
       ('194398019116', 1986, 27.95, 10, 'Europe', 'The Final Countdown', 'The_Final_Countdown.jpg'),
       ('602557646146', 1999, 40, 2, 'Limp Bizkit', 'Significant Other', 'Significant_Other.jpg'),
       ('602567098348', 1967, 38.99, 11, 'The Beatles', 'Sgt. Peppers Lonely Hearts Club Band',
        'Sgt._Peppers_Lonely_Hearts_Club_Band.jpg'),
       ('75678669637', 2015, 51.21, 5, 'Twenty One Pilots', 'Blurryface', 'Blurryface.jpg'),
       ('720642414811', 1987, 33.99, 10, 'Guns N Roses', 'Appetite for Destruction', 'Appetite_for_Destruction.jpg'),
       ('602552962104', 1975, 36.81, 5, 'Queen', 'A Night at the Opera', 'A_Night_at_the_Opera.jpg');

-- Inserimento dati nella tabella "genere"

INSERT INTO genere (id, nome)
VALUES (1, 'Thrash Metal'),
       (2, 'Rock and Roll'),
       (3, 'Grunge'),
       (4, 'Nu Metal'),
       (5, 'Hard Rock'),
       (6, 'Alternative Rock'),
       (7, 'Alternative Hip Hop'),
       (8, 'Electronic Rock'),
       (9, 'Psychedelic Rock'),
       (10, 'Glam Metal'),
       (11, 'Pop Rock'),
       (12, 'Alternative Metal'),
       (13, 'Heavy Metal'),
       (14, 'Progressive Rock');

-- Inserimento dati nella tabella "ordine"

INSERT INTO ordine (indirizzo, id_utente, oradiordine, totale, stato)
VALUES (1, 1, '1688805000', 87.98, 'P'),
       (1, 2, '	1688737500', 89.70, 'S'),
       (2, 3, '1688627700', 18, 'P');


-- Inserimento dati nella tabella "vinile_in_ordine"

INSERT INTO vinile_in_ordine (ordine_id, quantita, vinile_ean, prezzoacq)
VALUES (1, 2, '602557382594', 34.99),
       (1, 1, '194397971514', 18),
       (2, 3, '720642442517', 29.90),
       (3, 1, '93624915959', 18);

-- Inserimento dati nella tabella "vinile_genere"

INSERT INTO vinile_genere (EAN, id)
VALUES ('602557382594', 1),
       ('194397971514', 2),
       ('720642442517', 3),
       ('93624941422', 4),
       ('93624915959', 4),
       ('93624915959', 8),
       ('93624915959', 7),
       ('93624913245', 11),
       ('93624913245', 6),
       ('93624935711', 12),
       ('93624935711', 6),
       ('93624921127', 6),
       ('93624921127', 8),
       ('93624963189', 8),
       ('93624963189', 6),
       ('93624998105', 6),
       ('194398019116', 10),
       ('194398019116', 5),
       ('602557646146', 4),
       ('602567098348', 9),
       ('602567098348', 11),
       ('75678669637', 7),
       ('75678669637', 6),
       ('720642414811', 12),
       ('720642414811', 5),
       ('602552962104', 5),
       ('602552962104', 14),
       ('602552962104', 11);

ALTER TABLE Vinile
    ADD FULLTEXT (titolo);

ALTER TABLE Vinile
    ADD FULLTEXT (autore);



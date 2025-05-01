DROP
DATABASE IF EXISTS progetto;
CREATE
DATABASE progetto;
USE
progetto;

CREATE TABLE utente
(
    id       int(11) NOT NULL AUTO_INCREMENT,
    username varchar(45)  NOT NULL,
    password char(40)     NOT NULL,
    nome     varchar(100) NOT NULL,
    cognome  varchar(100) NOT NULL,
    sesso    varchar(7)   NOT NULL,
    email    varchar(100) NOT NULL,
    admin    tinyint(1) NOT NULL,
    disabled tinyint(1) default 0,
    PRIMARY KEY (id),
    UNIQUE KEY (username),
    UNIQUE KEY (email)
);

CREATE TABLE indirizzo
(
    citta         varchar(30) not null,
    provincia     char(2)     not null,
    via           varchar(30) not null,
    numero_civico int         not null,
    primary key (citta, provincia, via, numero_civico)
);

CREATE TABLE vinile
(
    EAN                varchar(14),
    anno_pubblicazione int         not null,
    prezzo             int         not null,
    numero_disponibili int,
    descrizione        mediumtext  not null,
    autore             varchar(50),
    titolo             text        not null,
    acquisti           int default 0,
    copertina          varchar(50) not null unique,
    FULLTEXT KEY (titolo),
    FULLTEXT KEY (titolo,descrizione),
    primary key (EAN)
);

CREATE TABLE categoria
(
    id          int auto_increment,
    nome        text       not null,
    descrizione mediumtext not null,
    primary key (id)
);

create table ordine
(
    id_utente   int(11),
    oradiordine varchar(100) not null,
    id          int primary key auto_increment,
    totale      int,
    FOREIGN KEY (id_utente) REFERENCES utente (id) on update cascade on delete cascade
);

create table faparte
(
    quantita  int,
    prezzoacq float
);

CREATE TABLE login
(
    id       char(36)  NOT NULL,
    idutente int(11) NOT NULL,
    time     timestamp NOT NULL,
    PRIMARY KEY (id),
    KEY (idutente),
    CONSTRAINT FOREIGN KEY (idutente) REFERENCES utente (id)
);

CREATE TABLE vinile_categoria
(
    EAN varchar(14),
    id  int,
    FOREIGN KEY (id) REFERENCES categoria (id) on update cascade on delete cascade,
    FOREIGN KEY (EAN) REFERENCES vinile (EAN) on update cascade on delete cascade,
    primary key (EAN, id)
);

CREATE TABLE carrello
(
    EAN varchar(14),
    id  int,
    FOREIGN KEY (id) REFERENCES categoria (id) on update cascade on delete cascade,
    FOREIGN KEY (EAN) REFERENCES vinile (EAN) on update cascade on delete cascade,
    primary key (EAN, id)
);

LOCK
TABLES utente WRITE;
INSERT INTO utente
VALUES (1, 'utente1', SHA1('password1'), 'Utente 1', 'cognome', 'Maschio', 'utente1@test.com', 1, 0),
       (2, 'utente2', SHA1('password2'), 'Utente 2', 'cognome', 'Maschio', 'utente2@test.com', 0, 0),
       (3, 'utente3', SHA1('password3'), 'Utente 3', 'cognome', 'Maschio', 'utente3@test.com', 0, 0);
UNLOCK
TABLES;


DROP DATABASE IF EXISTS progetto;
CREATE DATABASE progetto;
USE progetto;

CREATE TABLE utente
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    username varchar(45)  NOT NULL,
    password char(40)     NOT NULL,
    nome     varchar(100) NOT NULL,
    cognome  varchar(100) NOT NULL,
    sesso    varchar(7)   NOT NULL,
    email    varchar(100) NOT NULL,
    admin    tinyint(1)   NOT NULL,
    disabled tinyint(1) default 0,
    PRIMARY KEY (id),
    UNIQUE KEY (username),
    UNIQUE KEY (email)
);

CREATE TABLE vinile
(
    EAN                varchar(14),
    anno_pubblicazione int         not null,
    prezzo             int         not null,
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
    id          int primary key auto_increment,
    id_utente   int(11),
    oradiordine varchar(100) not null,
    totale      int,
    stato       varchar(1),
    FOREIGN KEY (id_utente) REFERENCES utente (id) on update cascade on delete cascade
);

create table vinile_in_ordine
(
    ordine_id  int,
    quantita   int,
    vinile_ean char(14),
    prezzoacq  float,
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
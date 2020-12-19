CREATE TABLE authors
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    picUrl TEXT NOT NULL
);

CREATE TABLE albums
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    name       TEXT    NOT NULL,
    authors_id INTEGER NOT NULL REFERENCES authors,
    year       INTEGER NOT NULL CHECK (year > 0),
    count      INTEGER NOT NULL CHECK (count > 0),
    length     INTEGER NOT NULL CHECK (length > 0),
    liked      BOOLEAN,
    picUrl     TEXT NOT NULL
);

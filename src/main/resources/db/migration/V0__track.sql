CREATE TABLE tracks (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    artistId BIGINT NOT NULL
);
CREATE TABLE artists (
                         id SERIAL PRIMARY KEY,
                         name TEXT NOT NULL
);

CREATE TABLE artist_names (
                              id BIGINT NOT NULL,
                              name TEXT NOT NULL
);
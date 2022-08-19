CREATE TABLE category(
    id VARCHAR PRIMARY KEY,
    description VARCHAR NOT NULL
);

CREATE TABLE skill(
    id VARCHAR PRIMARY KEY,
    description VARCHAR NOT NULL,
    category VARCHAR NOT NULL,
    FOREIGN KEY (category) REFERENCES category(id)
);


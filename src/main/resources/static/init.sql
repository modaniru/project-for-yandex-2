CREATE TABLE file(
    id varchar PRIMARY KEY,
    type VARCHAR NOT NULL,
    url VARCHAR(255),
    parent_id VARCHAR,
    size INT,
    update_date BIGINT
);

CREATE TABLE file_validation(
    id serial PRIMARY KEY,
    uuid VARCHAR,
    url VARCHAR(255),
    parent_id VARCHAR,
    type VARCHAR,
    size INT,
    update_date BIGINT
);
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
    roles VARCHAR(255),
    INDEX (name),
    INDEX (roles)
);

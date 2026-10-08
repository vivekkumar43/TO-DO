CREATE TABLE toUser
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255)          NULL,
    email    VARCHAR(255)          NULL,
    password VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE toUser
    ADD CONSTRAINT uc_249ba36000029bbe97499c03d UNIQUE (name);
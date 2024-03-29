CREATE TABLE cars
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    active       BOOLEAN                                 NOT NULL,
    car_body     VARCHAR(255)                            NOT NULL,
    color        VARCHAR(255),
    engine       VARCHAR(255)                            NOT NULL,
    gearbox      VARCHAR(255)                            NOT NULL,
    manufacturer VARCHAR(255),
    model        VARCHAR(255),
    plate        VARCHAR(255),
    power        INTEGER                                 NOT NULL,
    price        INTEGER                                 NOT NULL,
    wheel_drive  VARCHAR(255)                            NOT NULL,
    year         INTEGER                                 NOT NULL
        constraint cars_year_check
            check (year >= 1900)
);

CREATE TABLE cars_classifications
(
    car_id          BIGINT NOT NULL
        REFERENCES cars,
    classifications VARCHAR(255)
);

CREATE TABLE cars_photo
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    cover     BOOLEAN,
    file_name VARCHAR(255),
    car_id    BIGINT
        REFERENCES cars
);

CREATE TABLE personality_data
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    driver_license_number VARCHAR(255),
    last_name             VARCHAR(255),
    name                  VARCHAR(255),
    passport_number       VARCHAR(255),
    phone                 VARCHAR(255)
);

CREATE TABLE personality_docs_photo
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    file_name VARCHAR(255),
    data_id   BIGINT
        REFERENCES personality_data
);

CREATE TABLE users
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    active              BOOLEAN,
    created             TIMESTAMP(6),
    password            VARCHAR(255),
    updated             TIMESTAMP(6),
    username            VARCHAR(255),
    personality_data_id BIGINT
        REFERENCES personality_data
);

CREATE TABLE orders
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
    active      BOOLEAN,
    ending      TIMESTAMP(6),
    start       TIMESTAMP(6),
    status      VARCHAR(255),
    total_price INTEGER,
    car_id      BIGINT
        REFERENCES cars,
    user_id     BIGINT
        REFERENCES users
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL
        REFERENCES users,
    roles   VARCHAR(255)
);
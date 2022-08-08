CREATE TABLE sensors
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE measurements
(
    id        INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    value     DOUBLE PRECISION NOT NULL CHECK ( value > -100 AND value < 100),
    raining   BOOLEAN          NOT NULL,
    date_time TIMESTAMP        NOT NULL,
    sensor    VARCHAR(30) references sensors (name)
);
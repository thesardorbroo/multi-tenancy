-----------------------------------------------------

-- 1. Создание базы данных для common-данных
CREATE DATABASE multi_tenancy WITH OWNER = root;

-- 2. Создание первой базы данных для первого тенанта
CREATE DATABASE st_tenant WITH OWNER = root;

-- 3. Создание второй базы данных для второго тенанта
CREATE DATABASE nd_tenant WITH OWNER = root;

-----------------------------------------------------

-- запускай скрипт внутри multi_tenancy БД
CREATE TABLE person
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO person VALUES (1, 'John Doe', 'johndoe@email.com');
INSERT INTO person VALUES (2, 'Sarah Conor', 'sarahconor@email.com');
INSERT INTO person VALUES (3, 'John Conor', 'johnconor@email.com');
INSERT INTO person VALUES (4, 'Denzel Washington', 'denzelwashington@email.com');
INSERT INTO person VALUES (5, 'Tom Cruise', 'tomcruise@email.com');

-----------------------------------------------------

-- запускай скрипт внутри st_tenant и nd_tenant БД
CREATE TABLE fruit
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

-- для st_tenant
INSERT INTO fruit VALUES (1, 'Apple');
INSERT INTO fruit VALUES (2, 'Banana');
INSERT INTO fruit VALUES (3, 'Cherry');

-- для nd_tenant
INSERT INTO fruit VALUES (1, 'Pineapple');
INSERT INTO fruit VALUES (2, 'Kiwi');
INSERT INTO fruit VALUES (3, 'Peach');

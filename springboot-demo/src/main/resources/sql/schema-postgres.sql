DROP TABLE IF EXISTS cities;
CREATE TABLE cities(id serial PRIMARY KEY, name VARCHAR(255), population integer);
DROP TABLE IF EXISTS student;
CREATE TABLE student(
    id serial PRIMARY KEY,
    name varchar(30) NOT NULL ,
    age INT NOT NULL,
    city_id BIGINT
);

COMMENT on column student.id is '唯一标识id';
COMMENT on column student.name is '姓名';
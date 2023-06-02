CREATE TABLE todo
(
    id        SERIAL primary key,
    username varchar(255),
    title     varchar(255),
    completed boolean
);

INSERT INTO todo (title, username, completed)
VALUES ('Clean your room', 'axelote', true);
INSERT INTO todo (title, username, completed)
VALUES ('Learn Axelote', 'axelote', false);
INSERT INTO todo (title, username, completed)
VALUES ('Make breakfast', 'axelote', false);
INSERT INTO todo (title, username, completed)
VALUES ('Learn english for two hours', 'axelote', true);
INSERT INTO todo (title, username, completed)
VALUES ('Bake a cake', 'axelote', false);

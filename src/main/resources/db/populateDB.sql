DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (datetime, description, calories, userid) VALUES ('2016-12-21 11:00:00', 'Test1', 150, 100000);
INSERT INTO meals (datetime, description, calories, userid) VALUES ('2016-12-22 23:00:00', 'Test2', 500, 100000);
INSERT INTO meals (datetime, description, calories, userid) VALUES ('2016-12-23 03:00:00', 'Test3', 1000, 100000);
INSERT INTO meals ( datetime, description, calories, userid) VALUES ('2016-12-23 15:00:00', 'Test4', 1000, 100000);
INSERT INTO meals (datetime, description, calories, userid) VALUES ('2016-12-26 00:40:00', 'Admin1', 700, 100001);
INSERT INTO meals (datetime, description, calories, userid) VALUES ('2016-12-27 08:41:00', 'Admin2', 4000, 100001);



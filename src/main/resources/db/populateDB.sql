DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 100000),
  ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES  ('2016-01-15 10:00:00', 'Завтрак', 500, 100000),
        ('2016-01-15 13:00:00', 'Обед', 1000, 100000),
        ('2016-01-15 20:00:00', 'Ужин', 500, 100000),
        ('2016-01-16 10:00:00', 'Завтрак', 500, 100000),
        ('2016-01-16 13:00:00', 'Обед', 1000, 100000),
        ('2016-01-16 20:00:00', 'Ужин', 510, 100000),
        ('2016-01-04 14:00:00', 'Админ ланч', 510, 100001),
        ('2016-01-04 21:00:00', 'Админ ужин', 1500, 100001);


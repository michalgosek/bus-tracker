INSERT INTO users VALUES(1, 1, 1, 1, 1, "$2a$10$QwVpZJ4Up9YbeqboJnNPzeqkJgD6IYrMPEeU0G7ye3uIT6aNTPN76", "admin");
INSERT INTO users VALUES(2, 1, 1, 1, 1, "$2a$10$QwVpZJ4Up9YbeqboJnNPzeqkJgD6IYrMPEeU0G7ye3uIT6aNTPN76", "user");
INSERT INTO roles VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles VALUES(2, 'ROLE_USER');
INSERT INTO roles VALUES(3, 'ROLE_DRIVER');
INSERT INTO users_roles VALUES(1, 1);
INSERT INTO users_roles VALUES(1, 2);
INSERT INTO users_roles VALUES(2, 2);

INSERT INTO streets VALUES(1, 'Wyspianskiego');
INSERT INTO streets VALUES(2, 'Norwida');
INSERT INTO streets VALUES(3, 'Warszawska');
INSERT INTO streets VALUES(4, 'Tarnwoska');
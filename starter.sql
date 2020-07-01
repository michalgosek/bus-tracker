INSERT INTO users VALUES(1, 1, 1, 1, 1, "$2a$10$QwVpZJ4Up9YbeqboJnNPzeqkJgD6IYrMPEeU0G7ye3uIT6aNTPN76", "admin");
INSERT INTO users VALUES(2, 1, 1, 1, 1, "$2a$10$QwVpZJ4Up9YbeqboJnNPzeqkJgD6IYrMPEeU0G7ye3uIT6aNTPN76", "user");
INSERT INTO roles VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles VALUES(2, 'ROLE_USER');
INSERT INTO roles VALUES(3, 'ROLE_DRIVER');
INSERT INTO users_roles VALUES(1, 1);
INSERT INTO users_roles VALUES(1, 2);
INSERT INTO users_roles VALUES(2, 2);

INSERT INTO streets VALUES(1, 'Warszawska');
INSERT INTO streets VALUES(2, 'Wapiennikowa');

INSERT INTO stops VALUES(1, 'Warszawska / Jaworskiego', 1);
INSERT INTO stops VALUES(2, 'Warszawska / Orkana', 1);
INSERT INTO stops VALUES(3, 'Wapiennikowa / Ściegiennego', 2);


INSERT INTO bus_lines VALUES(1, 'Kolberga', 5, 1);

INSERT INTO time_schedule VALUES(1, 1, '10:30', 1);
INSERT INTO time_schedule VALUES(2, 1, '12:30', 1);
INSERT INTO time_schedule VALUES(3, 1, '13:30', 1);
INSERT INTO time_schedule VALUES(4, 1, '14:30', 1);

INSERT INTO time_schedule VALUES(5, 2, '10:30', 1);
INSERT INTO time_schedule VALUES(6, 2, '12:30', 1);
INSERT INTO time_schedule VALUES(7, 2, '13:30', 1);
INSERT INTO time_schedule VALUES(8, 2, '14:30', 1);

 
INSERT INTO bus_lines VALUES(2, 'Wazna', 5, 1);

INSERT INTO time_schedule VALUES(9, 1, '10:20', 2);
INSERT INTO time_schedule VALUES(10, 1, '12:30', 2);
INSERT INTO time_schedule VALUES(11, 1, '13:40', 2);
INSERT INTO time_schedule VALUES(12, 1, '14:50', 2);

INSERT INTO time_schedule VALUES(13, 2, '10:10', 2);
INSERT INTO time_schedule VALUES(14, 2, '12:30', 2);
INSERT INTO time_schedule VALUES(15, 2, '13:40', 2);
INSERT INTO time_schedule VALUES(16, 2, '14:50', 2);




INSERT INTO bus_lines VALUES(3, 'Kolberga1', 5, 2);
INSERT INTO bus_lines VALUES(4, 'Wazna1', 5, 2);
 
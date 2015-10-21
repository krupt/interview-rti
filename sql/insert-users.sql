INSERT INTO tuser(id, description, email, enabled, password)
VALUES (1, 'Администратор', 'admin@rti.ru', TRUE, '$2a$10$HkamMnkeULxO2DXAXUSJ7OigVltF.nCkPEFcHzptoU2bjv59b/bP2');
INSERT INTO tuser(id, description, email, enabled, password)
VALUES (2, 'Ковалев Андрей', 'akovalev@rti.ru', TRUE, '$2a$10$J5Oj8cmVnbwx0pAlw521Nermc3NDYPwroxmdjeD1kY0cFWJ7mLw1q');
INSERT INTO tuser(id, description, email, enabled, password)
VALUES (3, 'Петров Петр', 'ppetrov@rti.ru', TRUE, '$2a$10$VTydjlPiu56YoJnwCmpii.HnNOL3E1iz7g6DMcHQTJDf0sWpnpXki');
INSERT INTO tuser(id, description, email, enabled, password)
VALUES (4, 'Иванов Иван', 'ivanov@rti.ru', TRUE, '$2a$10$iLIyGArOD6n/UeG4WapTV.THP7kgTFWXIBliOPfPSb7phg6pNajHm');

INSERT INTO tuserrole(userid, role)
VALUES (1, 'ADMIN');
INSERT INTO tuserrole(userid, role)
VALUES (1, 'USER');
INSERT INTO tuserrole(userid, role)
VALUES (2, 'USER');
INSERT INTO tuserrole(userid, role)
VALUES (3, 'USER');
INSERT INTO tuserrole(userid, role)
VALUES (4, 'USER');
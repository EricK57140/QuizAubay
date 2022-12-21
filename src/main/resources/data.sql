INSERT INTO person (name,first_name,email,password,active)
VALUES ("bob","jean","kk@l.com","$2a$10$0e0VsNHNubX94fd/YPAjLux/HYiZHBbMUV/WWBzhzuwo0dJRGj2oC",1);

INSERT INTO person (name,first_name,email,password,active)
VALUES ("bb","jean","k@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);
INSERT INTO person (name,first_name,email,password,active)
VALUES ("bb2","jean2","k2@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);

INSERT INTO administrator ( personId, number_administrator)
VALUES(1,1);

--UPDATE hr set number_hr = 1 where personId = 2;
INSERT INTO hr ( personId, number_hr)
VALUES(2,1);
----
--INSERT INTO candidate ( personId, number_candidate)
--VALUES(3,1);


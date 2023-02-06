INSERT INTO person (name,first_name,email,password,active)
VALUES ("Pierre","jean","kk@l.com","$2a$10$0e0VsNHNubX94fd/YPAjLux/HYiZHBbMUV/WWBzhzuwo0dJRGj2oC",1);

INSERT INTO person (name,first_name,email,password,active)
VALUES ("Smith","John","k@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);
INSERT INTO person (name,first_name,email,password,active)
VALUES ("Ro","naldo","k2@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);



--UPDATE hr set number_hr = 1 where personId = 2;
INSERT INTO hr ( personId, number_hr)
VALUES(2,1);


INSERT INTO technology ( name_technology, active)
VALUES('Java',1),
('PHP',1);



INSERT INTO questions ( question_title, active ,score_by_question,timer,technology_id_technology)
VALUES('Is Java an oriented object language?',1,3,60,1),
('Is Symfony a PHP framework?',1,2,30,2);


INSERT INTO answers(active, answer_wording, correct, questions_id_questions)
VALUES
(1,"Yes",1,1),(1,"No",0,1),
(1,"No",1,2),(1,"Yes",0,2);


INSERT INTO test(active, name_test)
VALUES
(1,"Java débutant"),
(1,"C# confirmé");

INSERT INTO test_question(test_id,question_id)
VALUES
(1,1),
(1,2),
(2,2);


----
--INSERT INTO candidate ( personId, number_candidate)
--VALUES(3,1);


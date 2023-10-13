INSERT INTO person (name,first_name,email,password,active)
VALUES ("Pierre","jean","kk@l.com","$2a$10$0e0VsNHNubX94fd/YPAjLux/HYiZHBbMUV/WWBzhzuwo0dJRGj2oC",1);

INSERT INTO person (name,first_name,email,password,active)
VALUES ("Smith","John","k@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);
INSERT INTO person (name,first_name,email,password,active)
VALUES ("Ro","naldo","k2@l.com","$2a$10$9QO/duy6Xz/XZln1aFlT1.yGWfstXNwUXvosNhFikV4E0A/RMUN96",1);



--UPDATE hr set number_hr = 1 where personId = 2;
INSERT INTO hr ( personId, number_hr)
VALUES(2,1);

INSERT INTO candidate ( personId, number_candidate)
VALUES(1,1),(3,2);



INSERT INTO technology ( name_technology, active)
VALUES('Java',1),
('PHP',1);



INSERT INTO questions ( question_title, active ,timer,technology_id_technology)
VALUES('Is Java an oriented object language?',1,60,1),
('Is Symfony a PHP framework?',1,30,2),
("HashSet s = new HashSet(); s.add(new Integer(1)); s.add(new Integer(1)); s.add(new Integer(2));  What is the value returned by s.size()?",1,30,1),
("What would you use to execute code only once when a class is first loaded?",1,60,1),
("Which data type is used to create a variable that should store text?",1,30,1),
("How do you create a variable with the numeric value 5?",1,30,1);


INSERT INTO answers(active, answer_wording, correct, questions_id_questions,score)
VALUES
(1,"Yes",1,1,1),(1,"No",0,1,0),
(1,"No",0,2,0),(1,"Yes",1,2,1),
(1,"1",0,3,0),(1,"2",1,3,1),(1,"4",0,3,0),(1,"3",0,3,0),
(1,"A constructor",0,4,0),(1,"A static block",1,4,1),(1,"An instance block",0,4,0),(1,"A method",0,4,0),
(1,"String",1,5,1),(1,"myString",0,5,0),(1,"TXY",0,5,0),(1,"string",0,5,0),
(1,"num x = 5",0,6,0),(1,"int x = 5",1,6,1),(1,"x = 5",0,6,0),(1,"float x = 5",0,6,0);


INSERT INTO test(active, name_test,hr_personid)
VALUES
(1,"Java débutant",2),
(1,"C# confirmé",2);

INSERT INTO test_question(test_id,question_id)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(2,2);

INSERT INTO test_assignation(active,candidate_personId,test_id_test,assignation_date)
VALUES
(1,3,1,"2023-03-01");



----
--INSERT INTO candidate ( personId, number_candidate)
--VALUES(3,1);


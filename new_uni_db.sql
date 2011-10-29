CREATE DATABASE UNIINFO CHARACTER SET utf8 COLLATE utf8_bin;
SET character_set_server=utf8;
SET character_set_database=utf8;
SET character_set_client=utf8;
SET character_set_connection=utf8;


USE UNIINFO;

DROP TABLE IF EXISTS direction;
CREATE TABLE direction (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL
); 

DROP TABLE IF EXISTS speciality;
CREATE TABLE speciality (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	direction_id INT NOT NULL,
 	name VARCHAR(100) NOT NULL
); 

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	university_id INT NOT NULL,
 	name VARCHAR(100) NOT NULL,
  description VARCHAR(3000)
); 

DROP TABLE IF EXISTS speciality_faculty;
CREATE TABLE speciality_faculty (
	speciality_id INT NOT NULL,
	faculty_id INT NOT NULL,
  CONSTRAINT specility_faculty_uk UNIQUE (speciality_id, faculty_id) 
); 

DROP TABLE IF EXISTS university;
CREATE TABLE university (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	name VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  description VARCHAR(3000)
); 

DROP TABLE IF EXISTS ranking_result;
CREATE TABLE ranking_result (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	method_id INT NOT NULL,
  faculty_id INT NOT NULL,
  rank INT NOT NULL
); 


DROP TABLE IF EXISTS ranking_method;
CREATE TABLE ranking_method (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	direction_id INT NOT NULL,
  coeff INT NOT NULL,
  implement_class VARCHAR(100) NOT NULL
); 

DROP TABLE IF EXISTS ranking_raw_info_description;
CREATE TABLE ranking_raw_info_description (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	method_id INT NOT NULL,
  description VARCHAR(3000) NOT NULL
); 


DROP TABLE IF EXISTS ranking_raw_info_result;
CREATE TABLE ranking_raw_info_result (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 	faculty_id INT NOT NULL,
  ranking_raw_info_description_id INT NOT NULL,
  value DOUBLE NOT NULL
); 


ALTER TABLE speciality ADD CONSTRAINT for_direction_speciality
FOREIGN KEY (direction_id)
REFERENCES direction(id);


ALTER TABLE faculty ADD CONSTRAINT for_faculty_university
FOREIGN KEY (university_id)
REFERENCES university(id);


ALTER TABLE speciality_faculty ADD CONSTRAINT for_speciality_faculty_1
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE speciality_faculty ADD CONSTRAINT for_speciality_faculty_2
FOREIGN KEY (speciality_id)
REFERENCES speciality(id);


ALTER TABLE ranking_raw_info_result ADD CONSTRAINT for_ranking_raw_info_result_faculty
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE ranking_raw_info_result ADD CONSTRAINT for_ranking_raw_info_result_description
FOREIGN KEY (ranking_raw_info_description_id)
REFERENCES ranking_raw_info_description(id);


ALTER TABLE ranking_raw_info_description ADD CONSTRAINT for_ranking_raw_info_description_method
FOREIGN KEY (method_id)
REFERENCES ranking_method(id);


ALTER TABLE ranking_method ADD CONSTRAINT for_ranking_method_direction
FOREIGN KEY (direction_id)
REFERENCES direction(id);


ALTER TABLE ranking_result ADD CONSTRAINT for_ranking_result_method
FOREIGN KEY (method_id)
REFERENCES ranking_method(id);


ALTER TABLE ranking_result ADD CONSTRAINT for_ranking_result_faculty
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);

INSERT INTO direction (name) VALUES ('010000 физико-математические науки');

insert into speciality (direction_id, name) VALUES ('1','математика');

insert into ranking_method (direction_id, coeff, implement_class) VALUES ('1', '1', 'topcoder.xml');

insert into ranking_raw_info_description (method_id, description) VALUES ('1', 'topcoder ranking formulae');



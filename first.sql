USE UNIINFO;

DROP TABLE IF EXISTS publication;
CREATE TABLE publication (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
    journal VARCHAR(45) NOT NULL,
	date DATE NOT NULL
); 

DROP TABLE IF EXISTS professor;
CREATE TABLE professor (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	age INT NOT NULL,
 	degree VARCHAR(45) NOT NULL
); 

DROP TABLE IF EXISTS publication_professor;
CREATE TABLE publication_professor (
	prof_id INT NOT NULL,
	public_id INT NOT NULL,
  CONSTRAINT publication_professor_uk UNIQUE (prof_id, public_id) 
); 


DROP TABLE IF EXISTS department;
CREATE TABLE department (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL
); 


DROP TABLE IF EXISTS department_professor;
CREATE TABLE department_professor (
	depart_id INT NOT NULL,
	prof_id INT NOT NULL,
  CONSTRAINT department_professor_uk UNIQUE (prof_id, depart_id) 
); 

DROP TABLE IF EXISTS department_faculty;
CREATE TABLE department_faculty (
	depart_id INT NOT NULL,
	faculty_id INT NOT NULL,
  CONSTRAINT department_faculty_uk UNIQUE (faculty_id, depart_id) 
); 


DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	dekan_name VARCHAR(100) NOT NULL,
	count_of_budget_places INT NOT NULL,
	count_of_nonbudget_places INT NOT NULL,
 	price INT NOT NULL,
 	addres_id INT NOT NULL 

); 


DROP TABLE IF EXISTS vuz_faculty;
CREATE TABLE vuz_faculty (
	vuz_id INT NOT NULL,
	faculty_id INT NOT NULL,
  CONSTRAINT vuz_faculty_uk UNIQUE (vuz_id, faculty_id) 
); 


DROP TABLE IF EXISTS vuz;
CREATE TABLE vuz (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	rektor_name VARCHAR(100) NOT NULL,
	url VARCHAR(100) NOT NULL,
	voennaya_kafedra BOOl NOT NULL
); 
 
DROP TABLE IF EXISTS international_programm;
CREATE TABLE international_programm (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	conditions VARCHAR(1000) NOT NULL
); 

DROP TABLE IF EXISTS international_programm_faculty;
CREATE TABLE international_programm_faculty (
	int_prog_id INT NOT NULL,
	faculty_id INT NOT NULL,
  CONSTRAINT international_programm_faculty_uk UNIQUE (int_prog_id, faculty_id) 
); 


DROP TABLE IF EXISTS campus;
CREATE TABLE campus (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	square INT NOT NULL,
	price INT NOT NULL,
	count_of_odinary_places INT NOT NULL,
	count_of_comfort_places INT
	addres_id INT NOT NULL
	vuz_id INT NOT NULL
); 


DROP TABLE IF EXISTS addres;
CREATE TABLE addres (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ind INT NOT NULL,
	street VARCHAR(45) NOT NULL,
	numb_of_house INT 
	city_id INT NOT NULL
); 


DROP TABLE IF EXISTS city;
CREATE TABLE city (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL
); 


DROP TABLE IF EXISTS profilac;
CREATE TABLE profilac (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	
	name VARCHAR(45) NOT NULL,
	vuz_id INT NOT NULL,
	addres_id INT NOT NULL
); 


DROP TABLE IF EXISTS rest_base;
CREATE TABLE rest_base (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,	
	name VARCHAR(45) NOT NULL,
	vuz_id INT NOT NULL,
	addres_id INT NOT NULL
); 


DROP TABLE IF EXISTS review;
CREATE TABLE review (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	date DATE NOT NULL,
	positiv BOOL NOT NULL,
	text VARCHAR(1000) NOT NULL,
	ref VARCHAR(45) NOT NULL, 
	prof_id INT,
	depart_id INT,
	faculty_id INT,
	campus_id INT,
	profilac_id INT,
	rest_base_id INT
); 

DROP TABLE IF EXISTS rank;
CREATE TABLE rank (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	date DATE NOT NULL,
	name VARCHAR(45) NOT NULL,
	place INT NOT NULL,
	faculty_id INT,
	department_id INT
	 
); 


DROP TABLE IF EXISTS abiturient;
CREATE TABLE abiturient (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	school VARCHAR(45) NOT NULL 
); 

DROP TABLE IF EXISTS abiturient_faculty;
CREATE TABLE abiturient_faculty (
	abitur_id INT NOT NULL,
	faculty_id INT NOT NULL,
  CONSTRAINT abiturient_faculty_uk UNIQUE (abitur_id, faculty_id) 
); 

DROP TABLE IF EXISTS graduated;
CREATE TABLE graduated (
	name VARCHAR(45) NOT NULL,
	company_name VARCHAR(45) NOT NULL,
	depart_id INT NOT NULL,
	prof_id INT NOT NULL 
); 




ALTER TABLE addres ADD CONSTRAINT for_addres_city
FOREIGN KEY (city_id)
REFERENCES city(id);


ALTER TABLE campus ADD CONSTRAINT for_campus_addres
FOREIGN KEY (addres_id)
REFERENCES addres(id);


ALTER TABLE campus ADD CONSTRAINT for_campus_vuz
FOREIGN KEY (vuz_id)
REFERENCES vuz(id);

ALTER TABLE profilac ADD CONSTRAINT for_profilac_vuz
FOREIGN KEY (vuz_id)
REFERENCES vuz(id);

ALTER TABLE profilac ADD CONSTRAINT for_profilac_addres
FOREIGN KEY (addres_id)
REFERENCES addres(id);

ALTER TABLE rest_base ADD CONSTRAINT for_rest_base_vuz
FOREIGN KEY (vuz_id)
REFERENCES vuz(id);

ALTER TABLE rest_base ADD CONSTRAINT for_rest_base_addres
FOREIGN KEY (addres_id)
REFERENCES addres(id);

ALTER TABLE review ADD CONSTRAINT for_review_professor
FOREIGN KEY (prof_id)
REFERENCES professor(id);

ALTER TABLE review ADD CONSTRAINT for_review_department
FOREIGN KEY (depart_id)
REFERENCES department(id);

ALTER TABLE review ADD CONSTRAINT for_review_faculty
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE review ADD CONSTRAINT for_review_campus
FOREIGN KEY (campus_id)
REFERENCES campus(id);

ALTER TABLE review ADD CONSTRAINT for_review_profilac
FOREIGN KEY (profilac_id)
REFERENCES profilac(id);


ALTER TABLE review ADD CONSTRAINT for_review_rest_base
FOREIGN KEY (rest_base_id)
REFERENCES rest_base(id);

ALTER TABLE rank ADD CONSTRAINT for_rank_faculty
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE rank ADD CONSTRAINT for_rank_department
FOREIGN KEY (department_id)
REFERENCES department(id);

ALTER TABLE publication_professor ADD CONSTRAINT for_publication_professor1_fk
FOREIGN KEY (prof_id)
REFERENCES professor(id);

ALTER TABLE publication_professor ADD CONSTRAINT for_publication_professor2_fk
FOREIGN KEY (public_id)
REFERENCES publication(id);


ALTER TABLE graduated ADD CONSTRAINT for_graduated_department
FOREIGN KEY (depart_id)
REFERENCES department(id);

ALTER TABLE graduated ADD CONSTRAINT for_graduated_prof
FOREIGN KEY (prof_id)
REFERENCES professor(id);

ALTER TABLE department_professor ADD CONSTRAINT for_department_professor1_fk
FOREIGN KEY (prof_id)
REFERENCES professor(id);

ALTER TABLE publication_professor ADD CONSTRAINT for_department_professor2_fk
FOREIGN KEY (depart_id)
REFERENCES department(id);

ALTER TABLE department_faculty ADD CONSTRAINT for_department_faculty1_fk
FOREIGN KEY (depart_id)
REFERENCES department(id);

ALTER TABLE department_faculty ADD CONSTRAINT for_department_faculty2_fk
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);

ALTER TABLE faculty ADD CONSTRAINT for_faculty_addres
FOREIGN KEY (addres_id)
REFERENCES addres(id);

ALTER TABLE vuz_faculty ADD CONSTRAINT for_vuz_faculty1_fk
FOREIGN KEY (vuz_id)
REFERENCES vuz(id);

ALTER TABLE vuz_faculty ADD CONSTRAINT for_vuz_faculty2_fk
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE international_programm_faculty ADD CONSTRAINT for_international_programm_faculty1_fk
FOREIGN KEY (int_prog_id)
REFERENCES international_programm(id);

ALTER TABLE international_programm_faculty ADD CONSTRAINT for_international_programm_faculty2_fk
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


ALTER TABLE abiturient_faculty ADD CONSTRAINT for_abiturient_faculty1_fk
FOREIGN KEY (abitur_id)
REFERENCES abiturient(id);

ALTER TABLE abiturient_faculty  ADD CONSTRAINT for_abiturient_faculty2_fk
FOREIGN KEY (faculty_id)
REFERENCES faculty(id);


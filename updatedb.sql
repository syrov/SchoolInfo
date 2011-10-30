INSERT INTO direction (name) VALUES ('010000 math-phis science');

insert into speciality (direction_id, name) VALUES ('1','mathematics');

insert into ranking_method (direction_id, coeff, implement_class) VALUES ('1', '1', 'topcoder.xml');

insert into ranking_raw_info_description (method_id, description) VALUES ('1', 'topcoder ranking formulae');



INSERT INTO faculty VALUES(1, 4, "Math faculty", "");

INSERT INTO speciality_faculty VALUES(1, 1);
CREATE DATABASE UNIINFO CHARACTER SET utf8 COLLATE utf8_bin;
set character_set_client=utf8;
set character_set_connection=utf8;
set character_set_server=utf8;

use UNIINFO;

CREATE TABLE univercity (id INT, name VARCHAR(30), about TEXT);
INSERT INTO univercity VALUES (1, "МФТИ", "Один замечательный московский университет");
INSERT INTO univercity VALUES (2, "МатМех", "Лучший питерский вуз!!!");
INSERT INTO univercity VALUES (3, "Яндекс", "А это вообще не университет");
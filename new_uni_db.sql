CREATE DATABASE UNIINFO CHARACTER SET utf8 COLLATE utf8_bin;
SET character_set_server=utf8;
SET character_set_database=utf8;
SET character_set_client=utf8;
SET character_set_connection=utf8;

USE UNIINFO;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`University` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `city` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(3000) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`Direction` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`Speciality` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `direction_id` INT NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_direction_speciality` (`direction_id` ASC) ,
  CONSTRAINT `for_direction_speciality`
    FOREIGN KEY (`direction_id` )
    REFERENCES `UNIINFO`.`Direction` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`Faculty` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `university_id` INT NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  `description` VARCHAR(3000) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_faculty_university` (`university_id` ASC) ,
  CONSTRAINT `for_faculty_university`
    FOREIGN KEY (`university_id` )
    REFERENCES `UNIINFO`.`University` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`SpecialityFaculty` (
  `speciality_id` INT NOT NULL ,
  `faculty_id` INT NOT NULL ,
  UNIQUE INDEX `specility_faculty_uk` (`speciality_id` ASC, `faculty_id` ASC) ,
  INDEX `for_speciality_faculty_1` (`faculty_id` ASC) ,
  CONSTRAINT `for_speciality_faculty_1`
    FOREIGN KEY (`faculty_id` )
    REFERENCES `UNIINFO`.`Faculty` (`id` ),
  CONSTRAINT `for_speciality_faculty_2`
    FOREIGN KEY (`speciality_id` )
    REFERENCES `UNIINFO`.`Speciality` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE  TABLE IF NOT EXISTS `UNIINFO`.`RankingMethod` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `direction_id` INT NOT NULL ,
  `coeff` INT NOT NULL ,
  `implement_class` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_ranking_method_direction` (`direction_id` ASC) ,
  CONSTRAINT `for_ranking_method_direction`
    FOREIGN KEY (`direction_id` )
    REFERENCES `UNIINFO`.`Direction` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`RankingResult` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `method_id` INT NOT NULL ,
  `faculty_id` INT NOT NULL ,
  `rank` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_ranking_result_method` (`method_id` ASC) ,
  INDEX `for_ranking_result_faculty` (`faculty_id` ASC) ,
  CONSTRAINT `for_ranking_result_method`
    FOREIGN KEY (`method_id` )
    REFERENCES `UNIINFO`.`RankingMethod` (`id` ),
  CONSTRAINT `for_ranking_result_faculty`
    FOREIGN KEY (`faculty_id` )
    REFERENCES `UNIINFO`.`Faculty` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`RankingRawIinfoDescription` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `method_id` INT NOT NULL ,
  `description` VARCHAR(3000) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_ranking_raw_info_description_method` (`method_id` ASC) ,
  CONSTRAINT `for_ranking_raw_info_description_method`
    FOREIGN KEY (`method_id` )
    REFERENCES `UNIINFO`.`RankingMethod` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`RankingRrawInfoResult` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `faculty_id` INT NOT NULL ,
  `ranking_raw_info_description_id` INT NOT NULL ,
  `value` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `for_ranking_raw_info_result_faculty` (`faculty_id` ASC) ,
  INDEX `for_ranking_raw_info_result_description` (`ranking_raw_info_description_id` ASC) ,
  CONSTRAINT `for_ranking_raw_info_result_faculty`
    FOREIGN KEY (`faculty_id` )
    REFERENCES `UNIINFO`.`Faculty` (`id` ),
  CONSTRAINT `for_ranking_raw_info_result_description`
    FOREIGN KEY (`ranking_raw_info_description_id` )
    REFERENCES `UNIINFO`.`RankingRawIinfoDescription` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE  TABLE IF NOT EXISTS `UNIINFO`.`TableOfFacts` (
  `idTableOfFacts` INT NOT NULL ,
  `direct_id` INT NOT NULL ,
  `spec_id` INT NOT NULL ,
  `facult_id` INT NOT NULL ,
  `uni_id` INT NOT NULL ,
  `method_id` INT NOT NULL ,
  `rank_id` INT NOT NULL ,
  `format_id` INT NOT NULL ,
  `info_id` INT NOT NULL ,
  PRIMARY KEY (`idTableOfFacts`) ,
  INDEX `Direction` (`direct_id` ASC) ,
  INDEX `Speciality` (`spec_id` ASC) ,
  INDEX `Faculty` (`facult_id` ASC) ,
  INDEX `University` (`uni_id` ASC) ,
  INDEX `Method` (`method_id` ASC) ,
  INDEX `Rank` (`rank_id` ASC) ,
  INDEX `Format` (`format_id` ASC) ,
  INDEX `RawInfo` (`info_id` ASC) ,
  CONSTRAINT `Direction`
    FOREIGN KEY (`direct_id` )
    REFERENCES `UNIINFO`.`Direction` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Speciality`
    FOREIGN KEY (`spec_id` )
    REFERENCES `UNIINFO`.`Speciality` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Faculty`
    FOREIGN KEY (`facult_id` )
    REFERENCES `UNIINFO`.`Faculty` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `University`
    FOREIGN KEY (`uni_id` )
    REFERENCES `UNIINFO`.`University` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Method`
    FOREIGN KEY (`method_id` )
    REFERENCES `UNIINFO`.`RankingMethod` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Rank`
    FOREIGN KEY (`rank_id` )
    REFERENCES `UNIINFO`.`RankingResult` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Format`
    FOREIGN KEY (`format_id` )
    REFERENCES `UNIINFO`.`RankingRawIinfoDescription` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RawInfo`
    FOREIGN KEY (`info_id` )
    REFERENCES `UNIINFO`.`RankingRrawInfoResult` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
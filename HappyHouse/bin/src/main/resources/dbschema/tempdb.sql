CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `happyhouse` ;
-- -----------------------------------------------------
-- Table `happyhouse`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`test11201` ;
CREATE TABLE test11201 (
                           id int primary key auto_increment,
                           dongCode VARCHAR(10),
                           aptCode VARCHAR(20) not null,
                           aptName VARCHAR(30),
                           pyoeng VARCHAR(10),
                           address VARCHAR(50),
                           roadAddress VARCHAR(50),
                           latitude VARCHAR(20),
                           longitude VARCHAR(20),
                           totalHouseholdCount TEXT,
                           totalLeaseHouseholdCount TEXT,
                           highFloor TEXT,
                           lowFloor TEXT,
                           representativePhoto TEXT,
                           batlRatio TEXT,
                           btlRatio TEXT,
                           parkingPossibleCount TEXT,
                           constructionCompanyName TEXT,
                           heatMethodTypeCode TEXT,
                           supplyArea TEXT,
                           exclusiveArea TEXT,
                           exclusiveRate TEXT,
                           roomCnt TEXT,
                           bathroomCnt TEXT,
                           householdCountByPyeong TEXT,
                           dealPrice TEXT,
                           dealAveragePrice VARCHAR(30),
                           leasePrice VARCHAR(30),
                           rentPrice VARCHAR(30)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

--   `겨울관리비` text,
--   `여름관리비` text,

--   `초등학교_학군정보` text,
--   `초등학교_설립정보` text,
--   `초등학교_남학생수` text,
--   `초등학교_여학생수` text

commit;
--   `겨울관리비` text,
--   `여름관리비` text,

--   `초등학교_학군정보` text,
--   `초등학교_설립정보` text,
--   `초등학교_남학생수` text,
--   `초등학교_여학생수` text

drop table housedealprice2;
CREATE TABLE `housedealprice` (
									id int primary key auto_increment,
                                   `aptCode` VARCHAR(20) not null,
                                   `dealAveragePrice` VARCHAR(30),
                                   `baseYearMonthDay` text,
                                   `dealDate` text,
                                   `dealPrice` VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `happyhouse`.`chartdata` ;
CREATE TABLE `chartdata` (
									id int primary key auto_increment,
                                  `aptCode` VARCHAR(20) not null,
                                  `realDateData` text,
                                  `tradeType` varchar(10),
                                  `realPriceData` VARCHAR(30),
                                  `BasisYearMonthDay` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

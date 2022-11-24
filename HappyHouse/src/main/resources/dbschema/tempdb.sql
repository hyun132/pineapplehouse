CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `happyhouse` ;
-- -----------------------------------------------------
-- Table `happyhouse`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`houseinfo` ;
CREATE TABLE `houseinfo` (
                             `dongCode` text,
                             `aptName` text,
                             `pyoeng` text,
                             `address` text,
                             `roadAddress` text,
                             `latitude` double DEFAULT NULL,
                             `longitude` double DEFAULT NULL,
                             `totalHouseholdCount` double DEFAULT NULL,
                             `totalLeaseHouseholdCount` double DEFAULT NULL,
                             `highFloor` double DEFAULT NULL,
                             `lowFloor` double DEFAULT NULL,
                             `representativePhoto` text,
                             `aptCode` text,
                             `batlRatio` text,
                             `btlRatio` text,
                             `parkingPossibleCount` double DEFAULT NULL,
                             `constructionCompanyName` text,
                             `heatMethodTypeCode` text,
                             `supplyArea` text,
                             `exclusiveArea` text,
                             `exclusiveRate` text,
                             `roomCnt` text,
                             `bathroomCnt` text,
                             `householdCountByPyeong` text,
                             `dealPrice` text,
                             `leasePrice` text,
                             `rentPrice` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


commit;


drop table housedealprice;
CREATE TABLE `housedealprice` (
                                  `baseYearMonthDay` text,
                                  `dealUpperPriceLimit` bigint DEFAULT NULL,
                                  `dealAveragePrice` bigint DEFAULT NULL,
                                  `dealLowPriceLimit` bigint DEFAULT NULL,
                                  `dealAveragePriceChangeAmount` bigint DEFAULT NULL,
                                  `leaseUpperPriceLimit` bigint DEFAULT NULL,
                                  `leaseAveragePrice` bigint DEFAULT NULL,
                                  `leaseLowPriceLimit` bigint DEFAULT NULL,
                                  `leaseAveragePriceChangeAmount` bigint DEFAULT NULL,
                                  `rentLowPrice` bigint DEFAULT NULL,
                                  `deposit` bigint DEFAULT NULL,
                                  `rentUpperPrice` bigint DEFAULT NULL,
                                  `upperPriceLimit` bigint DEFAULT NULL,
                                  `averagePriceLimit` bigint DEFAULT NULL,
                                  `lowPriceLimit` bigint DEFAULT NULL,
                                  `priceChangeAmount` bigint DEFAULT NULL,
                                  `leasePerDealRate` text,
                                  `aptCode` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



DROP TABLE IF EXISTS `happyhouse`.`chartdatas` ;
CREATE TABLE `chartdatas` (
                              `aptCode` text,
                              `marketPrice` text,
                              `upperMarketPrice` text,
                              `lowerMarketPrice` text,
                              `ChartBasisDate` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

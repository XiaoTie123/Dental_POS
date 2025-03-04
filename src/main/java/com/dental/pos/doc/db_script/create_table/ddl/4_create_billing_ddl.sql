-- Table structure for table `bill`
DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
                        `bill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'bill_id',
                        `patient_id` BIGINT DEFAULT NULL COMMENT 'patient_id', -- Foreign key by Patients table
                        `doctor_id` TINYINT(1) DEFAULT NULL COMMENT 'doctor_id',
                        `percentage` INTEGER DEFAULT NULL COMMENT 'percentage',
                        `total_amount` DOUBLE DEFAULT NULL COMMENT 'total_amount',
                        `tax` INTEGER DEFAULT NULL COMMENT 'tax',
                        `tax_amount` DOUBLE DEFAULT NULL COMMENT 'tax_amount',
                        `percentage_amount` DOUBLE DEFAULT NULL COMMENT 'percentage_amount',
                        `net_amount` DOUBLE DEFAULT NULL COMMENT 'net_amount',
                        `transfer` TINYINT(1) DEFAULT NULL COMMENT 'transfer',
                        `created_at` DATETIME DEFAULT NULL,
                        `updated_at` DATETIME DEFAULT NULL,
                        `del_flg` TINYINT(1) NOT NULL COMMENT 'del_flg',
                        PRIMARY KEY (`bill_id`) USING BTREE,
                        CONSTRAINT `fk2_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='bill';
-- Table structure for table `bill_Detail`
DROP TABLE IF EXISTS `bill_Detail`;

CREATE TABLE `bill_Detail` (
                               `bill_detail_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'billDetail_id',
                               `bill_id` BIGINT DEFAULT NULL COMMENT 'bill_id', -- Foreign key by Bill table
                               `service_id` BIGINT DEFAULT NULL COMMENT 'service_id', -- Foreign key by Service table
                               `service_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'service_name',
                               `service_amount` DOUBLE DEFAULT NULL COMMENT 'service_amount',
                               `qty` TINYINT DEFAULT NULL COMMENT 'qty',
                               `total_amount` DOUBLE DEFAULT NULL COMMENT 'total_amount',
                               `created_at` DATETIME DEFAULT NULL,
                               `updated_at` DATETIME DEFAULT NULL,
                               `del_flg` TINYINT(1) NOT NULL COMMENT 'del_flg',
                               PRIMARY KEY (`bill_detail_id`) USING BTREE,
                               CONSTRAINT `fk1_bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`bill_id`),
                               CONSTRAINT `fk1_service_id` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='bill_Detail';
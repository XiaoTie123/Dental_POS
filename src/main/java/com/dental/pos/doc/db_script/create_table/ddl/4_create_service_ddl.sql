-- Table structure for table `service`
DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
                           `service_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'service_id',
                           `name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'name',
                           `description` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'description',
                           `amount` DOUBLE DEFAULT NULL COMMENT 'amount',
                           `created_at` DATETIME DEFAULT NULL,
                           `updated_at` DATETIME DEFAULT NULL,
                           `del_flg` TINYINT(1) NOT NULL COMMENT 'del_flg',
                           PRIMARY KEY (`service_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='service';
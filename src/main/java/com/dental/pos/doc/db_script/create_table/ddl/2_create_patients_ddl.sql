-- Table structure for table `patients`
DROP TABLE IF EXISTS `patients`;

CREATE TABLE `patients` (
  `patient_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'patient_id',
  `ref` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ref',
  `name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'name',
  `phone` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'phone',
  `age` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'age',
  `address` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'address',
  `contact_detail` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'contact_detail',
  `doctor_id` TINYINT(1) DEFAULT NULL COMMENT 'doctor_id',
  `created_at` DATETIME DEFAULT NULL,
  `updated_at` DATETIME DEFAULT NULL,
  `del_flg` TINYINT(1) NOT NULL COMMENT 'del_flg',
  PRIMARY KEY (`patient_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='patients';

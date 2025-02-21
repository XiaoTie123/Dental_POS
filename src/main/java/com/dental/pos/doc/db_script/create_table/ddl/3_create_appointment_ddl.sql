-- Table structure for table `appointment`
DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
    `appointment_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'appointment_id',
    `patient_id` BIGINT DEFAULT NULL COMMENT 'patient_id', -- Foreign key by Patients table
    `doctor_id` TINYINT(1) DEFAULT NULL COMMENT 'doctor_id',
    `date` DATE DEFAULT NULL,
    `time_id` TINYINT(1) DEFAULT NULL COMMENT 'time_id',
    `status` TINYINT(1) NOT NULL COMMENT 'status',
    `created_at` DATETIME DEFAULT NULL,
    `updated_at` DATETIME DEFAULT NULL,
    `del_flg` TINYINT(1) NOT NULL COMMENT 'del_flg',
    PRIMARY KEY (`appointment_id`) USING BTREE,
    CONSTRAINT `fk_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='appointment';
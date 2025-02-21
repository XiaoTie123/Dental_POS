package com.dental.pos.util.enums;

import com.dental.pos.util.common.CommonObject;
import com.dental.pos.util.common.CommonUtil;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Time {
    MIDNIGHT_00_00(1, "12:00 AM"), MIDNIGHT_00_15(2, "12:15 AM"), MIDNIGHT_00_30(3, "12:30 AM"), MIDNIGHT_00_45(4, "12:45 AM"),
    ONE_AM_00(5, "01:00 AM"), ONE_AM_15(6, "01:15 AM"), ONE_AM_30(7, "01:30 AM"), ONE_AM_45(8, "01:45 AM"),
    TWO_AM_00(9, "02:00 AM"), TWO_AM_15(10, "02:15 AM"), TWO_AM_30(11, "02:30 AM"), TWO_AM_45(12, "02:45 AM"),
    THREE_AM_00(13, "03:00 AM"), THREE_AM_15(14, "03:15 AM"), THREE_AM_30(15, "03:30 AM"), THREE_AM_45(16, "03:45 AM"),
    FOUR_AM_00(17, "04:00 AM"), FOUR_AM_15(18, "04:15 AM"), FOUR_AM_30(19, "04:30 AM"), FOUR_AM_45(20, "04:45 AM"),
    FIVE_AM_00(21, "05:00 AM"), FIVE_AM_15(22, "05:15 AM"), FIVE_AM_30(23, "05:30 AM"), FIVE_AM_45(24, "05:45 AM"),
    SIX_AM_00(25, "06:00 AM"), SIX_AM_15(26, "06:15 AM"), SIX_AM_30(27, "06:30 AM"), SIX_AM_45(28, "06:45 AM"),
    SEVEN_AM_00(29, "07:00 AM"), SEVEN_AM_15(30, "07:15 AM"), SEVEN_AM_30(31, "07:30 AM"), SEVEN_AM_45(32, "07:45 AM"),
    EIGHT_AM_00(33, "08:00 AM"), EIGHT_AM_15(34, "08:15 AM"), EIGHT_AM_30(35, "08:30 AM"), EIGHT_AM_45(36, "08:45 AM"),
    NINE_AM_00(37, "09:00 AM"), NINE_AM_15(38, "09:15 AM"), NINE_AM_30(39, "09:30 AM"), NINE_AM_45(40, "09:45 AM"),
    TEN_AM_00(41, "10:00 AM"), TEN_AM_15(42, "10:15 AM"), TEN_AM_30(43, "10:30 AM"), TEN_AM_45(44, "10:45 AM"),
    ELEVEN_AM_00(45, "11:00 AM"), ELEVEN_AM_15(46, "11:15 AM"), ELEVEN_AM_30(47, "11:30 AM"), ELEVEN_AM_45(48, "11:45 AM"),
    TWELVE_PM_00(49, "12:00 PM"), TWELVE_PM_15(50, "12:15 PM"), TWELVE_PM_30(51, "12:30 PM"), TWELVE_PM_45(52, "12:45 PM"),
    ONE_PM_00(53, "01:00 PM"), ONE_PM_15(54, "01:15 PM"), ONE_PM_30(55, "01:30 PM"), ONE_PM_45(56, "01:45 PM"),
    TWO_PM_00(57, "02:00 PM"), TWO_PM_15(58, "02:15 PM"), TWO_PM_30(59, "02:30 PM"), TWO_PM_45(60, "02:45 PM"),
    THREE_PM_00(61, "03:00 PM"), THREE_PM_15(62, "03:15 PM"), THREE_PM_30(63, "03:30 PM"), THREE_PM_45(64, "03:45 PM"),
    FOUR_PM_00(65, "04:00 PM"), FOUR_PM_15(66, "04:15 PM"), FOUR_PM_30(67, "04:30 PM"), FOUR_PM_45(68, "04:45 PM"),
    FIVE_PM_00(69, "05:00 PM"), FIVE_PM_15(70, "05:15 PM"), FIVE_PM_30(71, "05:30 PM"), FIVE_PM_45(72, "05:45 PM"),
    SIX_PM_00(73, "06:00 PM"), SIX_PM_15(74, "06:15 PM"), SIX_PM_30(75, "06:30 PM"), SIX_PM_45(76, "06:45 PM"),
    SEVEN_PM_00(77, "07:00 PM"), SEVEN_PM_15(78, "07:15 PM"), SEVEN_PM_30(79, "07:30 PM"), SEVEN_PM_45(80, "07:45 PM"),
    EIGHT_PM_00(81, "08:00 PM"), EIGHT_PM_15(82, "08:15 PM"), EIGHT_PM_30(83, "08:30 PM"), EIGHT_PM_45(84, "08:45 PM"),
    NINE_PM_00(85, "09:00 PM"), NINE_PM_15(86, "09:15 PM"), NINE_PM_30(87, "09:30 PM"), NINE_PM_45(88, "09:45 PM"),
    TEN_PM_00(89, "10:00 PM"), TEN_PM_15(90, "10:15 PM"), TEN_PM_30(91, "10:30 PM"), TEN_PM_45(92, "10:45 PM"),
    ELEVEN_PM_00(93, "11:00 PM"), ELEVEN_PM_15(94, "11:15 PM"), ELEVEN_PM_30(95, "11:30 PM"), ELEVEN_PM_45(96, "11:45 PM");

    private int code;
    private String desc;

    private Time(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescriptionByCode(Integer code) {

        if (!CommonUtil.isValidNonNegativeInteger(code)) {
            return "";
        }

        for (Time s : values()) {
            if (s.getCode() == code) {
                return s.getDesc();
            }
        }
        return "";
    }

    public static List<CommonObject> getAll() {
        return Stream.of(values()).map(s -> new CommonObject(s.getCode(), s.getDesc())).collect(Collectors.toList());
    }
}

package com.dental.pos.util.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dental.pos.util.common.CommonObject;
import com.dental.pos.util.common.CommonUtil;

import lombok.Getter;

@Getter
public enum Doctor {

	ZAW(1, "Dr. Zaw Myo Htet"), YE(2, "Dr. Ye Min Maung"), THI(3, "Dr. Ye Thiha"), MYO(4, "Dr. Myo Wai Yan");
	private int code;
	private String desc;

	private Doctor(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescriptionByCode(Integer code) {

		if (!CommonUtil.isValidNonNegativeInteger(code)) {
			return "";
		}

		for (Doctor s : values()) {
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

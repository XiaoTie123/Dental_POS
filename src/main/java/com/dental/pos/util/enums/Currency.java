package com.dental.pos.util.enums;

import com.dental.pos.util.common.CommonObject;
import com.dental.pos.util.common.CommonUtil;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Currency {

	MMK(1, "MMK"), BAHT(2, "Baht");
	private int code;
	private String desc;

	private Currency(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescriptionByCode(Integer code) {

		if (!CommonUtil.isValidNonNegativeInteger(code)) {
			return "";
		}

		for (Currency s : values()) {
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

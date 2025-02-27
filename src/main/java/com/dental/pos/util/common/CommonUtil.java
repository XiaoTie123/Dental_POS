package com.dental.pos.util.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtil {

	private static final Logger LOG = LogManager.getLogger();

	private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

	public static String formatNumberDouble(double amount) {
		return decimalFormat.format(amount);
	}

	public static String dateToString(String format, Date date) {
		if (date == null) {
			return "";
		}
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		return new SimpleDateFormat(format).format(date);
	}


	public static boolean isValidNonNegativeInteger(Integer val) {
		return val != null && val.compareTo(0) >= 0;
	}


	public static Date stringToDate(String format, String dateString) {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstants.STD_DATE_TIME_FORMAT;
		}
		try {
			return new SimpleDateFormat(format).parse(dateString);
		} catch (Exception e) {
			LOG.error(">>> Exception occurs while converting string into date >>> " + e.getMessage());
		}
		return null;
	}

}

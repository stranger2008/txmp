package com.xingfugo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidatorUtils {
	private static final Pattern PATTERN_MOBILE = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",
			Pattern.CASE_INSENSITIVE
	);
	
	public static boolean isMobile(String mobile){
		if ( mobile == null || mobile.length() == 0 ) {
			return false;
		}
		
		Matcher m = PATTERN_MOBILE.matcher( mobile );
		return m.matches();
	}
}

package com.zettamine.mpa.scm.util;

public class SurveyUtility {
	
	public static String safeUpperCaseTrim(String str) {
		if(str != null) {
			return str.toUpperCase().trim();
		}
		
			return null;
		
		
	}

}

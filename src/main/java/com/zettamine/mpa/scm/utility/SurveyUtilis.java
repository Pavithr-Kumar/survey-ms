package com.zettamine.mpa.scm.utility;

public class SurveyUtilis {
	
	public static String safeUpperCaseTrim(String str) {
		if(str != null) {
			return str.toUpperCase().trim();
		}
		
			return null;
		
		
	}

}

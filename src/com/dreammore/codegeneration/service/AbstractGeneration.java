package com.dreammore.codegeneration.service;

public abstract class AbstractGeneration implements Generation{
	
	protected static String BR = "\n";
	
	protected String firstLetterLower(String field){
		return field.substring(0, 1).toLowerCase().concat(field.substring(1));
	}
	
	protected String firstLetterUpper(String field){
		return field.substring(0, 1).toUpperCase().concat(field.substring(1));
	}
	
	protected String getBlanks(int number){
		String blank = "";
		for(int i = 0; i < number; i++){
			blank = blank.concat("   ");
		}
		
		return blank;
	}

}

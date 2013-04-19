package com.dreammore.codegeneration.test;

import com.dreammore.codegeneration.service.Generation;
import com.dreammore.codegeneration.service.HtmlDivGeneration;
import com.dreammore.codegeneration.service.HtmlListGeneration;
import com.dreammore.codegeneration.service.ServiceImplGeneration;
import com.dreammore.codegeneration.service.ServiceIntefaceGeneration;
import com.dreammore.codegeneration.service.SpringmvcControllerGeneration;
import com.dreammore.codegeneration.service.ValidateJsGeneration;

public class Test {

	private static String path = "h:/logs/";
	
	public static void main(String[] args) throws Exception {
		Class<?> clazz =  Person.class;
		
		Generation generation = new HtmlDivGeneration(path);
//        System.out.println(generation.generate(clazz));
        generation.write(clazz);
        
        System.out.println("----------------------------------------");
//		
        generation = new ValidateJsGeneration(path);
//        System.out.println(generation.generate(clazz));
        generation.write(clazz);
        
		generation = new SpringmvcControllerGeneration(path);
//        System.out.println(generation.generate(clazz));
		generation.write(clazz);
        
        generation = new HtmlListGeneration(path);
//        System.err.println(generation.generate(clazz));
        generation.write(clazz);
        
        generation = new ServiceImplGeneration(path);
//        System.out.println(generation.generate(clazz));
        generation.write(clazz);
        
        generation = new ServiceIntefaceGeneration(path);
//        System.out.println(generation.generate(clazz));
        generation.write(clazz);
		
	}


	
}

package com.dreammore.codegeneration.test;

import com.dreammore.codegeneration.service.Generation;
import com.dreammore.codegeneration.service.HtmlDivGeneration;
import com.dreammore.codegeneration.service.HtmlListGeneration;
import com.dreammore.codegeneration.service.ServiceImplGeneration;
import com.dreammore.codegeneration.service.ServiceIntefaceGeneration;
import com.dreammore.codegeneration.service.SpringmvcControllerGeneration;
import com.dreammore.codegeneration.service.ValidateJsGeneration;

public class Test {

	
	public static void main(String[] args) {
		Class<?> clazz =  Person.class;
		
		Generation generation = new HtmlDivGeneration();
        System.out.println(generation.generate(clazz));
        
        System.out.println("----------------------------------------");
//		
//        generation = new ValidateJsGeneration();
//        System.out.println(generation.generate(clazz));
//        
//		generation = new SpringmvcControllerGeneration();
//        System.out.println(generation.generate(clazz));
//        
//        generation = new HtmlListGeneration();
//        System.err.println(generation.generate(clazz));
        
        generation = new ServiceImplGeneration();
        System.out.println(generation.generate(clazz));
        
        generation = new ServiceIntefaceGeneration();
        System.out.println(generation.generate(clazz));
		
	}


	
}

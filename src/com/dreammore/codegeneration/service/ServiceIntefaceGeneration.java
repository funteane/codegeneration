package com.dreammore.codegeneration.service;


public class ServiceIntefaceGeneration extends AbstractGeneration{

	public ServiceIntefaceGeneration(String filePath) {
		super(filePath);
	}

	@Override
	public StringBuffer generate(Class<?> clazz) {
		StringBuffer sb = new StringBuffer();
		sb.append(getBlanks(0)).append("public interface I").append(clazz.getSimpleName()).append("Service{").append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("public PageBean<").append(clazz.getSimpleName()).append("> getAll").append(clazz.getSimpleName());
		sb.append(getBlanks(0)).append("s(PageBean<").append(clazz.getSimpleName()).append("> pageBean, ").append(clazz.getSimpleName()).append(" ");
		sb.append(getBlanks(0)).append(firstLetterLower(clazz.getSimpleName())).append(", Long... ids ) ;").append(BR);
		sb.append(getBlanks(1)).append("}");
		return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat("I").concat(clazz.getSimpleName()).concat("Service.java");
	}

}

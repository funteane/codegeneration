package com.dreammore.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.codegeneration.model.Comment;

public class ServiceImplGeneration extends AbstractGeneration {

	@Override
	public StringBuffer generate(Class<?> clazz) {
			StringBuffer sb = new StringBuffer();
			sb.append(getBlanks(0)).append("@Service").append(BR);
			sb.append(getBlanks(0)).append("public class").append(clazz.getSimpleName()).append("Service implements I");
			sb.append(getBlanks(0)).append(clazz.getSimpleName()).append("Service{").append(BR);
			sb.append(BR);
			sb.append(getBlanks(1)).append("@Autowired").append(BR);
			sb.append(getBlanks(1)).append("private ICommonDAO commonDAO;").append(BR);
			sb.append(BR);
			sb.append(getBlanks(1)).append("@Override").append(BR);
			sb.append(getBlanks(1)).append("public PageBean<").append(clazz.getSimpleName()).append("> getAll").append(clazz.getSimpleName());
			sb.append(getBlanks(0)).append("s(PageBean<").append(clazz.getSimpleName()).append("> pageBean, ").append(clazz.getSimpleName());
			sb.append(getBlanks(0)).append(firstLetterLower(clazz.getSimpleName())).append(", Long... ids) {").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("List<String> paramNames = new ArrayList<String>();").append(BR);
			sb.append(getBlanks(2)).append("List<Object> paramValues = new ArrayList<Object>();").append(BR);
			sb.append(getBlanks(2)).append("StringBuffer hql = new StringBuffer(\"SELECT e FROM ").append(clazz.getSimpleName()).append(" WHERE 1 = 1\");").append(BR);
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields){
				Comment comment = field.getAnnotation(Comment.class);
				if (comment.searchable()) {
					sb.append(BR);
					sb.append(getBlanks(2)).append("if(!Tools.empty(").append(firstLetterLower(clazz.getSimpleName()));
					sb.append(".get").append(firstLetterUpper(field.getName())).append("()){").append(BR);
					sb.append(getBlanks(3)).append("hql.append(\"AND ").append(field.getName()).append(" ");
					sb.append(field.getName()).append(" = :").append(field.getName()).append(" \");").append(BR);
					sb.append(getBlanks(3)).append("paramNames.add(\"").append(field.getName()).append("\");").append(BR);
					sb.append(getBlanks(3)).append("paramValues.add(").append(firstLetterLower(clazz.getSimpleName()));
					sb.append(".get").append(firstLetterUpper(field.getName())).append("());").append(BR);
					sb.append(getBlanks(2)).append("}").append(BR);	
				}
			}
			
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("if(!Tools.empty(ids)){").append(BR);
			sb.append(getBlanks(3)).append("hql.append(\"AND id IN (:ids));").append(BR);
			sb.append(getBlanks(3)).append("paramNames.add(\"ids\");").append(BR);
			sb.append(getBlanks(3)).append("paramValues.add(ids);").append(BR);
			sb.append(getBlanks(2)).append("}").append(BR);	
			
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("hql.append(\"ORDER BY id DESC\");").append(BR);
			sb.append("").append(BR);
			sb.append(getBlanks(2)).append("String[] names = new String[paramNames.size()];").append(BR);
			sb.append(getBlanks(2)).append("Object[] values = new Object[paramValues.size()];").append(BR);
			sb.append(getBlanks(2)).append("Tools.list2Array(paramNames, names);").append(BR);
			sb.append(getBlanks(2)).append("Tools.list2Array(paramValues, values);").append(BR);
			sb.append(getBlanks(2)).append("commonDAO.find(hql.toString(), pageBean, names, values);").append(BR);
			sb.append(getBlanks(2)).append("return pageBean;	").append(BR);
			sb.append(getBlanks(1)).append("}");
			
			return sb;
	}

}

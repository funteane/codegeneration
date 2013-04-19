package com.dreammore.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.codegeneration.model.Validate;

public class SpringmvcControllerGeneration extends AbstractGeneration{

	@Override
	public StringBuffer generate(Class<?> clazz) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(getBlanks(0)).append("@Controller").append(BR);
		sb.append(getBlanks(0)).append("public class ").append(clazz.getSimpleName());
		sb.append("Controller extends BaseController{").append(BR);
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@Autowired").append(BR);
		sb.append(getBlanks(1)).append("private ICommonService commonService;").append(BR);
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append(BR);
		
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView to").append(clazz.getSimpleName());
		sb.append("List(int pageNo, ").append(clazz.getSimpleName()).append(" ");
		sb.append(firstLetterLower(clazz.getSimpleName())).append("){").append(BR);
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView();").append(BR);
		sb.append(getBlanks(2)).append("//处理查询信息").append(BR);
		sb.append(getBlanks(2)).append("List<SearchParameter> searchParameters = buildSearchParameters(");
		sb.append(firstLetterLower(clazz.getSimpleName())).append(");").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(Constants.SEARCH_PARAMS_LIST, searchParameters);").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(Constants.CURRENT_PAGE_NO, pageNo);").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"searchParams\", JSONSimpler.toJson(searchParameters));").append(BR);
		sb.append(getBlanks(2)).append("//处理通知信息").append(BR);
		sb.append(getBlanks(2)).append("Boolean show = getFlashAttribute(\"show\", Boolean.class);").append(BR);
		sb.append(getBlanks(2)).append("Boolean success = getFlashAttribute(\"success\", Boolean.class);").append(BR);
		sb.append(getBlanks(2)).append("List<String> messages = getFlashAttribute(\"messages\", List.class);").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"messages\", messages);").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"show\", Tools.empty(show) ? false : show );").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"success\", Tools.empty(success) ? false : success);").append(BR);		
		sb.append(BR);
		sb.append(getBlanks(2)).append("return modelAndView;").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);
		
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("private List<SearchParameter> buildSearchParameters(");
		sb.append(clazz.getSimpleName()).append(" ").append(firstLetterLower(clazz.getSimpleName())).append("){").append(BR);
		sb.append(getBlanks(2)).append("List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();").append(BR);
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("return searchParameters;").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);
	
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView to").append(clazz.getSimpleName()).append("(Long id){");
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView();").append(BR);
		sb.append(getBlanks(2)).append("return modelAndView;").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);
		
	
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView delete").append(clazz.getSimpleName()).append("(Long id){");
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView();").append(BR);
		sb.append(getBlanks(2)).append("return createStateModelAndView(\"").append(clazz.getSimpleName().toLowerCase());
		sb.append("/to").append(clazz.getSimpleName()).append("List.do\");").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);		
		
		
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView save").append(clazz.getSimpleName()).append("(Long id){");
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView();").append(BR);
		sb.append(getBlanks(2)).append("return createStateModelAndView(\"").append(clazz.getSimpleName().toLowerCase());
		sb.append("/to").append(clazz.getSimpleName()).append("List.do\");").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);		
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Validate validate = field.getAnnotation(Validate.class);
			if (validate.unique()) {
				sb.append(BR);
				sb.append(BR);
				sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
				sb.append(getBlanks(1)).append("public @ResponseBody String exist").append(firstLetterUpper(field.getName())).append("(Long id, String ").append(field.getName()).append("){").append(BR);
				sb.append(getBlanks(2)).append(clazz.getSimpleName()).append(" ").append(firstLetterLower(clazz.getSimpleName()));
				sb.append(getBlanks(0)).append(" = ").append("commonService.get(").append(clazz.getSimpleName()).append(".class, id)").append(BR);
				sb.append(getBlanks(2)).append("if(").append(firstLetterLower(clazz.getSimpleName())).append(".get").append(firstLetterUpper(field.getName()));
				sb.append(getBlanks(0)).append(".equals(").append(firstLetterLower(field.getName())).append(")){").append(BR);
				sb.append(getBlanks(3)).append("return \"false\";").append(BR);
				sb.append(getBlanks(2)).append("}").append(BR);
				sb.append(getBlanks(2)).append("return \"true\";").append(BR);
				
				sb.append(getBlanks(1)).append("}").append(BR);	
			}
		}
		
		sb.append(getBlanks(0)).append("}");
		return sb;
	}

}

package com.dreammore.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.codegeneration.model.Comment;
import com.dreammore.codegeneration.model.Validate;

public class SpringmvcControllerGeneration extends AbstractGeneration{

	public SpringmvcControllerGeneration(String filePath) {
		super(filePath);
	}

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
		sb.append(getBlanks(1)).append("@Autowired").append(BR);
		sb.append(getBlanks(1)).append("private I").append(clazz.getSimpleName()).append("Service ").append(firstLetterLower(clazz.getSimpleName())).append("Service ;").append(BR);
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append(BR);
		
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView to").append(clazz.getSimpleName());
		sb.append("List(int pageNo, ").append(clazz.getSimpleName()).append(" ");
		sb.append(firstLetterLower(clazz.getSimpleName())).append("){").append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("PageBean<").append(clazz.getSimpleName()).append("> pageBean = new PageBean<").append(clazz.getSimpleName()).append(">();").append(BR);
		sb.append(getBlanks(2)).append("pageBean.setCurrentPage(pageNo);").append(BR);
		sb.append(getBlanks(2)).append("pageBean.setLength(Constants.DEFAULT_PAGE_SIZE);").append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView(\"").append(firstLetterLower(clazz.getSimpleName()));
		sb.append("/").append(firstLetterLower(clazz.getSimpleName())).append("List\");").append(BR);
		sb.append(getBlanks(2)).append("pageBean = ").append(firstLetterLower(clazz.getSimpleName())).append("Service.getAll").append(clazz.getSimpleName());
		sb.append(getBlanks(0)).append("s(pageBean, ").append(firstLetterLower(clazz.getSimpleName())).append(", ids ) ;").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"pageBean\", pageBean)").append(BR);
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
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Comment comment = field.getAnnotation(Comment.class);
			if (comment != null && comment.searchable()) {
				sb.append(getBlanks(2)).append("searchParameters.add(new SearchParameter(\"").append(field.getName()).append("\", ");
				sb.append(firstLetterLower(clazz.getSimpleName())).append(".get").append(firstLetterUpper(field.getName())).append("()));").append(BR);
			}
		}
		sb.append(getBlanks(2)).append("return searchParameters;").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);
	
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView to").append(clazz.getSimpleName()).append("(Long id){");
		sb.append(BR);
		sb.append(getBlanks(2)).append("if(Tools.empty(id){").append(BR);
		sb.append(getBlanks(3)).append(firstLetterLower(clazz.getSimpleName())).append(" = ").append("new ").append(clazz.getSimpleName()).append("()").append(BR);
		sb.append(getBlanks(2)).append("}else{").append(BR);
		sb.append(getBlanks(3)).append(firstLetterLower(clazz.getSimpleName())).append(" = commonService.get(").append(clazz.getSimpleName()).append(".class, id)").append(BR);
		sb.append(getBlanks(2)).append("}").append(BR);
		sb.append(getBlanks(2)).append("ModelAndView modelAndView = new ModelAndView(\"").append(firstLetterLower(clazz.getSimpleName()));
		sb.append(getBlanks(0)).append("/").append(firstLetterLower(clazz.getSimpleName())).append("\");").append(BR);
		sb.append(getBlanks(2)).append("modelAndView.addObject(\"").append(firstLetterLower(clazz.getSimpleName())).append("\", ");
		sb.append(firstLetterLower(clazz.getSimpleName())).append(");").append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("return modelAndView;").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);
		
	
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView delete").append(clazz.getSimpleName()).append("(Long id){");
		sb.append(BR);
		sb.append(getBlanks(2)).append(clazz.getSimpleName()).append(" ").append(firstLetterLower(clazz.getSimpleName())).append(" = ");
		sb.append(getBlanks(0)).append("new ").append(clazz.getSimpleName()).append("()").append(BR);
		sb.append(getBlanks(2)).append(firstLetterLower(clazz.getSimpleName())).append(".setId(id);").append(BR);
		sb.append(getBlanks(2)).append("commonService.delete(").append(firstLetterLower(clazz.getSimpleName())).append(");").append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("List<String> messages = new ArrayList<String>();").append(BR);
		sb.append(getBlanks(2)).append("messages.add(\"保存成功！\");").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"messages\", messages);").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"success\", true);").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"show\", true);").append(BR);   
		sb.append(BR);			
		sb.append(getBlanks(2)).append("return createStateModelAndView(\"").append(clazz.getSimpleName().toLowerCase());
		sb.append("/to").append(clazz.getSimpleName()).append("List.do\");").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);		
		
		
		sb.append(BR);
		sb.append(BR);
		sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
		sb.append(getBlanks(1)).append("public ModelAndView save").append(clazz.getSimpleName()).append("(");
		sb.append(clazz.getSimpleName()).append(" ").append(firstLetterLower(clazz.getSimpleName())).append("){");
		sb.append(BR);
		sb.append(getBlanks(2)).append("commonService.saveOrupdate(").append(firstLetterLower(clazz.getSimpleName())).append(")");
		sb.append(BR);
		sb.append(getBlanks(2)).append("List<String> messages = new ArrayList<String>();").append(BR);
		sb.append(getBlanks(2)).append("messages.add(\"保存成功！\");").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"messages\", messages);").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"success\", true);").append(BR);
		sb.append(getBlanks(2)).append("setAttribute(\"show\", true);").append(BR);   
		sb.append(BR);		
		sb.append(getBlanks(2)).append("return createStateModelAndView(\"").append(clazz.getSimpleName().toLowerCase());
		sb.append("/to").append(clazz.getSimpleName()).append("List.do\");").append(BR);
		sb.append(getBlanks(1)).append("}").append(BR);		
		
//		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			Validate validate = field.getAnnotation(Validate.class);
			if (validate != null && validate.unique()) {
				sb.append(BR);
				sb.append(BR);
				sb.append(getBlanks(1)).append("@RequestMapping").append(BR);
				sb.append(getBlanks(1)).append("public @ResponseBody String exist").append(firstLetterUpper(field.getName())).append("(Long id, String ").append(field.getName()).append("){").append(BR);
				sb.append(getBlanks(2)).append(clazz.getSimpleName()).append(" ").append(firstLetterLower(clazz.getSimpleName()));
				sb.append(getBlanks(0)).append(" = ").append(firstLetterLower(clazz.getSimpleName())).append("Service.get").append(clazz.getSimpleName()).append("By").append(firstLetterUpper(field.getName()));
				sb.append("(String ").append(field.getName()).append(" , id); ").append(BR);
				sb.append(getBlanks(2)).append("if (Tools.empty(").append(firstLetterLower(clazz.getSimpleName())).append(")) {").append(BR);
				sb.append(getBlanks(3)).append("return \"true\";").append(BR);
				sb.append(getBlanks(2)).append("}else if(Tools.empty(id)){").append(BR);
				sb.append(getBlanks(3)).append("return \"false\";").append(BR);
				sb.append(getBlanks(2)).append("}else if (").append(firstLetterLower(clazz.getSimpleName())).append(".getId() - id == 0) {").append(BR);
				sb.append(getBlanks(3)).append("return \"true\";").append(BR);
				sb.append(getBlanks(2)).append("}else {").append(BR);
				sb.append(getBlanks(3)).append("return \"false\";").append(BR);
				sb.append(getBlanks(2)).append("}").append(BR);
				sb.append(getBlanks(1)).append("}").append(BR);	
			}
		}
		
		sb.append(getBlanks(0)).append("}");
		return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat(clazz.getSimpleName()).concat("Controller.java");
	}

}

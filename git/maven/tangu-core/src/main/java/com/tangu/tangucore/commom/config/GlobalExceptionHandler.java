package com.tangu.tangucore.commom.config;

import com.tangu.common.exception.TanguException;
import com.tangu.common.util.entity.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fenglei on 8/29/17.
 * 全局异常handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
		logger.error(e.getMessage(),e);
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		if(e instanceof TanguException){
			view.setAttributesMap(new ResponseModel().attr(ResponseModel.KEY_ERROR, e.getMessage()));
		}else{
			view.setAttributesMap(new ResponseModel().attr(ResponseModel.KEY_ERROR, "后台异常！" + e));
		}
		ModelAndView mav = new ModelAndView();
		mav.setView(view);
		logger.info("报错请求的url:{}",req.getRequestURL());
		return mav;
	}
}

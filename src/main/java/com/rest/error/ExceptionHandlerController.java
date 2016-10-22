package com.rest.error;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StreamUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rest.error.constant.KyaratenErrorConstant;
import com.rest.error.exception.KyaratenBindingException;
import com.rest.util.json.JsonUtils;
import com.rest.util.log.KyaratenLogConstant;
import com.rest.util.log.KyaratenLogManager;
import com.rest.util.property.PropertiesResource;
import com.rest.vo.ErrorVO;
import com.rest.vo.KyaratenRequestDataVO;

@ControllerAdvice
public class ExceptionHandlerController {

	/**
	 * @apiDefine MyError
	 * @apiError UserNotFound The <code>id</code> of the User was not found.
	 */
	
	/**
	 * @apiDefine admin User access only
	 * This optional description belong to to the group admin.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	@Autowired
	private KyaratenRequestDataVO requestDataVO;
	
	@Autowired
	private PropertiesResource propertiesResource;
	
	@ExceptionHandler
	@ResponseBody
	public ErrorVO handlerException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
		ErrorVO errorVO = new ErrorVO();
		if (exception instanceof KyaratenBindingException) {
			KyaratenBindingException dnbbBindingException = (KyaratenBindingException) exception;
			errorVO.setCode(dnbbBindingException.errorCode());
			errorVO.setMessage(dnbbBindingException.errorMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
//		} else if (exception instanceof DnbbParamException) {
//			DnbbParamException dnbbParamException = (DnbbParamException) exception;
//			errorVO.setCode(dnbbParamException.errorCode());
//			errorVO.setMessage(dnbbParamException.errorMessage());
//			response.setStatus(HttpStatus.BAD_REQUEST.value());
//		} else if (exception instanceof DnbbProcessException) {
//			DnbbProcessException dnbbProcessException = (DnbbProcessException) exception;
//			errorVO.setCode(dnbbProcessException.errorCode());
//			errorVO.setMessage(dnbbProcessException.errorMessage());
//			response.setStatus(HttpStatus.BAD_REQUEST.value());
//		} else if (exception instanceof DnbbAuthenticationException) {
//			DnbbAuthenticationException dnbbAuthenticationException = (DnbbAuthenticationException) exception;
//			errorVO.setCode(dnbbAuthenticationException.errorCode());
//			errorVO.setMessage(dnbbAuthenticationException.errorMessage());
//			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof MissingServletRequestParameterException) {
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.MISSING_PARAMETER_ERROR_KEY, ((MissingServletRequestParameterException) exception).getParameterName());
			errorVO.setMessage((String.format(errorVO.getMessage(), ((MissingServletRequestParameterException) exception).getParameterName())));
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof MethodArgumentTypeMismatchException) {
			errorVO.setCode(10400);
			errorVO.setMessage(exception.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof ServletRequestBindingException) {
			ServletRequestBindingException servletRequestBindingException = (ServletRequestBindingException) exception;
			errorVO.setCode(10400);
			errorVO.setMessage(servletRequestBindingException.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof HttpRequestMethodNotSupportedException) { 
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.NOT_SUPPORT_ERROR_KEY);
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof HttpMessageNotReadableException) { 
			logger.warn("{} | Can't read body error={}", requestDataVO.getTid(), exception.getMessage());
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.CAN_NOT_READABLE_ERROR_KEY);
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof HttpMediaTypeNotSupportedException) { 
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.UNSUPPORT_MEDIA_TYPE_ERROR_KEY);
			errorVO.setMessage(String.format(errorVO.getMessage(), ((HttpMediaTypeNotSupportedException) exception).getContentType()));
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else if (exception instanceof HttpMessageNotReadableException) { 
			logger.error("{} | request body data={}", requestDataVO.getTid(), JsonUtils.toPrettyJson(convertStreamToString(request)));
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.NOT_ACCEPTABLE_ERROR_KEY);
			errorVO.setMessage(String.format(errorVO.getMessage(), ((HttpMessageNotReadableException) exception).getMessage()));
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		} else {
			exception.printStackTrace();
			logger.error("{}| error={}", requestDataVO.getTid(), KyaratenLogManager.makeLog(exception.getMessage(), exception));
			errorVO = propertiesResource.getErrorValue(KyaratenErrorConstant.UNKNOWN_ERROR_KEY);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//			String uri = requestDataVO.getMethod() + " : " + requestDataVO.getRequestURI();
//			dnbbErrorNotificateSlack.send(exception, uri, requestDataVO.getParameterMaps(), requestDataVO.getTid(), requestDataVO.getRequest(), convertStreamToString(request));
		}
		
		long elapsed = (System.currentTimeMillis() - requestDataVO.getApiStartTime());
		String time = elapsed > 5000 ? elapsed + "ms, TOOLONG" :  elapsed + "ms";
		
		logger.error(KyaratenLogManager.makeLog(4 ,KyaratenLogConstant.MODULE_NAME ,requestDataVO.getTid() ,KyaratenLogConstant.IP_ADDRESS ,KyaratenLogConstant.RES
				 ,KyaratenLogConstant.ELAPSED_TIME, time
			     ,KyaratenLogConstant.RETURN_ERROR_CODE ,errorVO.getCode()
			     ,KyaratenLogConstant.RETURN_ERROR_MSG ,errorVO.getMessage()
			     ));
		
		return errorVO;
	}
	
	private String convertStreamToString(HttpServletRequest request){
		String value = "";
		try {
			value = StreamUtils.copyToString(request.getInputStream(), Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
}

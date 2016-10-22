package com.rest.util.property;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.rest.util.log.KyaratenLogManager;
import com.rest.vo.ErrorVO;

@Component
public class PropertiesResource {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesResource.class);

	private static final String MESSAGE_DELEMETER = "#";

	@Autowired
	private MessageSource message;

	private static final Locale locale = Locale.getDefault();

	public String getValue(String key){
		String value = null;
		try {
			value = message.getMessage(key, null, locale);
		} catch (Exception e) {
			logger.warn(KyaratenLogManager.makeLog(value, e));
		}
		return value;
	}

	public ErrorVO getErrorValue(String key){
		ErrorVO errorVO = new ErrorVO();
		String value = null;
		try {
			value = message.getMessage(key, null, locale);
			errorVO.setCode((Integer.valueOf(value.split(MESSAGE_DELEMETER)[0])));
			errorVO.setMessage(value.split(MESSAGE_DELEMETER)[1]);
		} catch (Exception e) {
			logger.warn(KyaratenLogManager.makeLog(value, e));
		}
		return errorVO;
	}

	public ErrorVO getErrorValue(String key, String paramName) {
		ErrorVO errorVO = new ErrorVO();
		String value = null;
		try {
			value = message.getMessage(key, null, locale);
			errorVO.setCode((Integer.valueOf(value.split(MESSAGE_DELEMETER)[0])));
			errorVO.setMessage(String.format(value.split(MESSAGE_DELEMETER)[1], paramName));
		} catch (Exception e) {
			logger.warn(KyaratenLogManager.makeLog(value, e));
		}
		return errorVO;
	}
}

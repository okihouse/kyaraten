package com.rest.error.constant;

public class KyaratenErrorConstant {

	// 4xx error message
	// validation error
	public static final String MISSING_PARAMETER_ERROR_KEY = "validation.notnull.message";
	public static final String NOT_SUPPORT_ERROR_KEY = "validation.not.support";
	public static final String CAN_NOT_READABLE_ERROR_KEY = "validation.can.not.readable";
	public static final String NOT_FOUND_ERROR_KEY = "validation.not.found";
	public static final String UNKNOWN_ERROR_KEY = "validation.internal.error";
	public static final String UNSUPPORT_MEDIA_TYPE_ERROR_KEY = "validation.unsupport.media.type";
	public static final String NOT_ACCEPTABLE_ERROR_KEY = "validation.not.acceptable";
	
	public static final String VALIDATION_TYPE_MESSAGE = "validation.type.message";
	
	public static final String VALIDATION_NOT_VALID_PARAM_MESSAGE = "validation.not.valid.param.message";
	
	public static final String NOT_VALID_NUMBER = "validation.number.message";
	public static final String NOT_VALID_ZERO_NUMBER = "validation.not.zero.number.message";
	public static final String NOT_VALID_EMAIL = "validation.email.message";
	
	// AUTHORIZATION
	public static final String USER_NOT_AUTHORIZATION = "user_not_authorization";
	public static final String USER_NOT_AUTHENTICATION = "user_not_authentication";
	public static final String USER_NOT_EXIST = "user_not_exist";
	public static final String USER_PERMISSION_DENIED = "user_permission_denied";
	
}

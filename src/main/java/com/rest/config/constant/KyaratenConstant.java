package com.rest.config.constant;

public class KyaratenConstant {
	
	public static final String PRD = "prd";
	
	public static final String MEDIA_HOST = "http://cdn.dnbb.co.kr/";
	public static final String WEBVIEW_USER_AUTHORIZATION_URL = "http://dnbb.co.kr/service/authenticCenter/"; // TODO: 주소 값 확인해야 함

	public static final String REGEXR_EMAIL = "^([A-Z|a-z|0-9](\\-.|_){0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$";
	public static final String NOT_ZERO_NUMBER_PATTERN = "^[1-9]\\d*$";
	public static final String NUMBER_PATTERN = "^[0-9]*$";
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9@\\#$%&*()_+\\]\\[';:?.,!^-]+$";
	public static final String NICKNAME_PATTERN = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]+$";
	public static final String PHONE_PATTERN_WITHOUT_DASH = "^\\d{3}\\d{3,4}\\d{4}$";
	public static final String PHONE_PATTERN = "^\\d{3}-\\d{3,4}-\\d{4}$";
	public static final String SPECIAL_CHARACTER_PATTERN = "[^A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣_]";
	
	public static final String TID_HEADER_NAME = "tid";
	public static final String TID_DEFAULT_VALUE = "TID-NOT-EXIST";

	public static final String USER_AGENT_HEADER_NAME = "User-Agent";

	public static final String SLACK_API_URL = "slack_api_url";

	public static final String ENCODING = "UTF-8";
	
	public static final String APP_CODE = "appCode";
	public static final String ACCESS_TOKEN = "accessToken";
	public static final String REGION = "regionCode";
	
	public static final String ALL_USER_REGISTER_QUEUE_NAME = "all_user_register";
	public static final String MAGAZINE_QUEUE_NAME = "magazine_push";
	public static final String MARKET_QUEUE_NAME = "market_push";
	public static final String QUESTION_QUEUE_NAME = "question_push";
	
	public static final String CATEGORY = "category";

	public static final int TOKEN_LENGTH = 30;

	public static final long EXPIRE_DAYS = 30;
	public static final long SMS_EXPIRE_SECOND = 180;
	
	public static final int CONTENT_PAGING_OFFSET = 30;
	public static final int CONTENT_GROUP_PAGING_OFFSET = 5;
	public static final int CONTENT_COMMENT_PAGING_OFFSET = 30;
	public static final int CONTENT_REPLY_PAGING_OFFSET = 30;
	
	public static final Long CONTENT_CATEGORY_PARENT_NO = 1010000000L;
	public static final int CONTENT_CATEGORY_CHILD = 2;

	public static final int QNA_PAGING_OFFSET = 10;
	public static final int QNA_ANSWER_PAGING_OFFSET = 10;
	public static final Long QNA_TAG_MEASURE_VALUE = 2L;
	
	public static final int QNA_AUTOMATION_TAG_SIZE = 20;
	public static final int QNA_AUTOMATION_TAG_STANDARD_VALUE = 1000;
	
	public static final int SURVEY_COMMENT_PAGING_OFFSET = 30;
	public static final int SURVEY_REPLY_PAGING_OFFSET = 30;
	
	public static final int MARKET_LIST_PAGING_OFFSET = 30;

	public static final Long MARKET_CATEGORY_PARENT_NO = 1020000000L;
	public static final int MARKET_CATEGORY_CHILD = 2;

	public static final int MARKET_COMMENT_PAGING_OFFSET = 30;
	public static final int MARKET_REPLY_PAGING_OFFSET = 30;

	public static final String WELCOME = "동네바보에 오신 것을 환영합니다! ";
	public static final String EMAIl_CHANGE = "동네바보에서 이메일이 변경되었습니다.";
	public static final String PASSWORD_CHANGE = "동네바보 비밀번호 변경 메일입니다.";
	public static final String RESEND_EMAIL_AUTHENTICATION = "동네바보 회원가입 인증 메일입니다.";

}

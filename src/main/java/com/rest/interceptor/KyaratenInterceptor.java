package com.rest.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rest.config.constant.KyaratenConstant;
import com.rest.util.log.KyaratenLogConstant;
import com.rest.util.log.KyaratenLogManager;
import com.rest.vo.KyaratenRequestDataVO;

public class KyaratenInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(KyaratenInterceptor.class);
	
	@Autowired
	private KyaratenRequestDataVO kyaratenRequestDataVO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String method = request.getMethod();
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();
		String contentType = request.getContentType();
		String userAgent = request.getHeader(KyaratenConstant.USER_AGENT_HEADER_NAME);
		int contentLength = request.getContentLength();
		String remoteIp = request.getRemoteAddr();
		Map<String, String[]> parameterMaps = request.getParameterMap();

		String regionNo = request.getHeader(KyaratenConstant.REGION);
		String accessToken = request.getHeader(KyaratenConstant.ACCESS_TOKEN);
		String appCode = request.getHeader(KyaratenConstant.APP_CODE);
		
		String tid = request.getHeader(KyaratenConstant.TID_HEADER_NAME);
		if (tid == null || tid.isEmpty()) {
			tid = generateTransactionID(regionNo, accessToken, appCode);
		}
		
		kyaratenRequestDataVO.put(tid, method, requestURI, queryString, contentType, userAgent, contentLength, parameterMaps);
		kyaratenRequestDataVO.setApiStartTime(System.currentTimeMillis());
		kyaratenRequestDataVO.setRequest(request);
		kyaratenRequestDataVO.setResponse(response);
		kyaratenRequestDataVO.setRegionNo(regionNo);
		kyaratenRequestDataVO.setAccessToken(accessToken);
		kyaratenRequestDataVO.setAppCode(appCode);
		kyaratenRequestDataVO.setRemoteIp(remoteIp);
		
		logger.info(KyaratenLogManager.makeLog(4, KyaratenLogConstant.MODULE_NAME, KyaratenLogConstant.IP_ADDRESS, KyaratenLogConstant.REQ, tid,
										KyaratenLogConstant.HEADER_USER_AGENT, userAgent,
										KyaratenLogConstant.HEADER_CONTENT_TYPE, contentType,
										KyaratenLogConstant.HEADER_CONTENT_LENGTH, contentLength,
										KyaratenLogConstant.METHOD_NAME , method,
										KyaratenLogConstant.URI_NAME, requestURI, "?", queryString
									   ));
		
		return true;
	}
	
	private String generateTransactionID(String regionNo, String accessToken, String appCode) {
		String tid = regionNo + "-" + accessToken + "-" + appCode;
		return tid;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		if (response.getStatus() != HttpStatus.OK.value()) return;
		
		long rcode = 0;
		long apiStartTime = kyaratenRequestDataVO.getApiStartTime();
		long apiEndTime = System.currentTimeMillis();
		long elapsed = (apiEndTime - apiStartTime);
		String time = elapsed > 5000 ? elapsed + "ms, TOOLONG" :  elapsed + "ms";
		
		logger.info(KyaratenLogManager.makeLog(4, KyaratenLogConstant.MODULE_NAME, KyaratenLogConstant.IP_ADDRESS, KyaratenLogConstant.RES, kyaratenRequestDataVO.getTid(),
				KyaratenLogConstant.HTTP_STATUS, response.getStatus(),
				KyaratenLogConstant.RETURN_CODE , rcode,
				KyaratenLogConstant.ELAPSED_TIME, time
				));
	}

//	private long getCurrentTimestamp(String tid) {
//		long timestamp = 0L;
//		try {
//			Pattern pattern = Pattern.compile(DnbbConstant.TIMESTAMP_PATTERN);
//			Matcher matcher = pattern.matcher(tid);
//			if (matcher.find() == true) {
//				timestamp = (long) Integer.parseInt(matcher.group());
//			}
//		} catch (Exception e) {
//			logger.warn(DnbbLogManager.makeLog(e.getMessage(), e));
//		}
//		return timestamp;
//	}
}

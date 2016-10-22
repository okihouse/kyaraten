package com.rest.api.weather.api.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rest.api.weather.api.vo.req.WeatherRequestVO.WeatherRequest;
import com.rest.api.weather.api.vo.res.WeatherResponseVO.WeatherResponse;
import com.rest.util.json.JsonUtils;
import com.rest.vo.KyaratenRequestDataVO;

@Service
public class KyaratenWeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(KyaratenWeatherService.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private KyaratenRequestDataVO requestDataVO;
	
	public WeatherResponse weather(WeatherRequest weatherRequest) {
		String url = "https://api.forecast.io/forecast/e01bb97461d6a03e19ffad4ae2ebbded/";
		String query = String.format("%s,%s?lang=%s&units=%s", 
				weatherRequest.getLatitude(), weatherRequest.getLongitude(), weatherRequest.getLanguage(), weatherRequest.getUnit());
		ResponseEntity<String> responseEntity = restTemplate.exchange(url + query, HttpMethod.GET, null, String.class);
		String body = responseEntity.getBody();
		Map<String, Object> map = JsonUtils.fromJson(body, new TypeReference<Map<String, Object>>(){});
		
		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setMap(map);
		System.out.println(JsonUtils.toPrettyJson(body));
		logger.info("{} | Weather is succeed.", requestDataVO.getTid());
		return weatherResponse;
	}

}

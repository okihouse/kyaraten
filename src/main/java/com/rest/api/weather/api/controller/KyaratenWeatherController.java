package com.rest.api.weather.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.weather.api.service.KyaratenWeatherService;
import com.rest.api.weather.api.vo.req.WeatherRequestVO.WeatherRequest;
import com.rest.api.weather.api.vo.res.WeatherResponseVO.WeatherResponse;
import com.rest.error.exception.KyaratenBindingException;

@RestController
@RequestMapping(value = "/{version}/weather")
public class KyaratenWeatherController {

	@Autowired
	private KyaratenWeatherService weatherService;
	
	/**
	 * 
	 * @apiGroup Weather
	 * @api {post} /weather 날씨정보
	 * 
	 * @apiSampleRequest off
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public WeatherResponse weather(@RequestHeader HttpHeaders httpHeaders, @RequestBody @Valid WeatherRequest weatherRequest, BindingResult bindingResult) throws KyaratenBindingException {
		if (bindingResult.hasErrors()) {
			throw new KyaratenBindingException(bindingResult);
		}
		return weatherService.weather(weatherRequest);
	}
	
}

package com.rest.api.weather.api.vo.res;

import java.util.Map;

import com.rest.vo.SuccessVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class WeatherResponseVO {

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class WeatherResponse extends SuccessVO {
		
		private Map<String, Object> map;
	}
	
}

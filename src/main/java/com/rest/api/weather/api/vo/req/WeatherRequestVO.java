package com.rest.api.weather.api.vo.req;

import javax.validation.constraints.NotNull;

import lombok.Data;

public class WeatherRequestVO {

	@Data
	public static class WeatherRequest {
		
		@NotNull
		private String latitude;
		
		@NotNull
		private String longitude;
		
		@NotNull
		private String language;
		
		@NotNull
		private String unit;
		
	}
	
}

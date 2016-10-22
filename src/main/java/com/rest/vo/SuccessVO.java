package com.rest.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
public class SuccessVO {

	private int code = 0;
	private String message = "success";
	
	@Data
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	public static class SuccessWithToken extends SuccessVO {
		
		public SuccessWithToken(String accessToken){
			this.accessToken = accessToken;
//			super.code = dnbbRequestDataVO.isReissuance() ? 200 : 0;
		}
		
		private String accessToken;
		
	}
	
	@Data
	@EqualsAndHashCode(callSuper = true)
	public static class SuccessWithTokenOrNot extends SuccessVO {
		
		protected String accessToken = "";
		
	}
	
}

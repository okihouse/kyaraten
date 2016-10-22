package com.rest.vo;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class DnbbVersionVO {

	@NotEmpty(message = "{validation.notnull.message}")
	private String version;
	
}

package com.app.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserOutDto user;
}

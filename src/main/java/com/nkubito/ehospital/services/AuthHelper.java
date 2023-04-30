package com.nkubito.ehospital.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.utils.Response;

public enum AuthHelper {

	INSTANCE;
	
	public ResponseEntity<Response> login(UserService userService,  Map<String, Object> data) {
		var token = userService.login(data);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Login successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .obj(Map.of("token", token, "message", "login successfully"))
                        .build()
        );
	}
}

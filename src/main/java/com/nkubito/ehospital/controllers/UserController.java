package com.nkubito.ehospital.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkubito.ehospital.store.Users;
import com.nkubito.ehospital.utils.Response;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@RestController
public class UserController {
	@GetMapping(path = "/api/users")
	public ResponseEntity<Response> message() {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.message("Welcome to Nkubito's backend")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.data(Users.INSTANCE.retreiveUser())
						.build()
				);
	}
}

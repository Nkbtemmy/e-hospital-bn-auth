package com.nkubito.ehospital.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nkubito.ehospital.utils.Response;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;



@RestController
public class DefaultController {
	@GetMapping(path = "/api/")
	public ResponseEntity<Response> message() {
		return ResponseEntity.ok(
				Response.builder()
						.timeStamp(LocalDateTime.now())
						.message("Welcome to Nkubito's backend")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
				);
	}
}

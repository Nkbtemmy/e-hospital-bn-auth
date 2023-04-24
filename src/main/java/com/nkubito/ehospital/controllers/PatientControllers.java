package com.nkubito.ehospital.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nkubito.ehospital.interfaces.*;
import com.nkubito.ehospital.models.*;

@RestController
@RequestMapping(value="/patients")
public class PatientControllers implements Auth {
	Patient patient = new Patient();
	@PostMapping(path="/create")
	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}
	@GetMapping(path="/")
	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

}

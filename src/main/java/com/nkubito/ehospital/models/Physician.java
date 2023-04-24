package com.nkubito.ehospital.models;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Physician extends User{
	private String email;
	private  String password;
}

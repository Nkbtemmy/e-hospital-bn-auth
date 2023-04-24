package com.nkubito.ehospital.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User {
	private String name;
	private  String gender;
	private Integer age;
}

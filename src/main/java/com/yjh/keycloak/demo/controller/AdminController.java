package com.yjh.keycloak.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	
	@GetMapping("/getSample")
	public String getSample() {
		
		return "admin sample";
	}
}

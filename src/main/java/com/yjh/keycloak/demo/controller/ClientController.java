package com.yjh.keycloak.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

	
	@GetMapping("/getSample")
	public String getSample() {
		System.out.println("client  진입");
		return "client  sample";
	}
}

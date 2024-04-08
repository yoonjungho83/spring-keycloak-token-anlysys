package com.yjh.keycloak.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/open")
public class OpenController {

	
	@GetMapping("/getSample")
	public String getSample() {
		log.info("모두 접근 가능 데이터");
		return "open sample";
	}
}

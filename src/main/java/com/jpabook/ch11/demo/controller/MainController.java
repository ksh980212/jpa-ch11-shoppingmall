package com.jpabook.ch11.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/ping")
	public String pingTest() {
		return "pong";
	}

}

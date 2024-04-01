package com.mono.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
	
	@GetMapping("/show")
	public ResponseEntity<String> show(){
		
		System.out.println("show method from StudentRestController");
		
		return new ResponseEntity<String>("called student show method",HttpStatus.OK );
	}
}

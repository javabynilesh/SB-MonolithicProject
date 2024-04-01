package com.mono.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

	@GetMapping("/show")
	public ResponseEntity<Void> show(){
		
		System.out.println("show method from employee");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

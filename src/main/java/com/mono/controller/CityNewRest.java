package com.mono.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mono.entity.CityNew;
import com.mono.service.ICityNewService;

@RestController
@RequestMapping("/api/city")
public class CityNewRest {
	////http://localhost:8080/city/all?stateId={stateId}
	
	@Autowired
	private ICityNewService repo;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCityByStateId(@RequestParam Long stateId){
		ResponseEntity<?> response = null;
		try {
			List<CityNew> citiesByStateId = repo.getAllCityByStateId(stateId);
			response = new ResponseEntity<List<CityNew>>(citiesByStateId,HttpStatus.OK);
		}catch (Exception e) {
			response = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}

package com.mono.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mono.entity.StateNew;
import com.mono.service.IStateNewService;

@RestController
@RequestMapping("/api/state")
public class StateNewRest {
	//http://localhost:8080/country/all
	//http://localhost:8080/state/all?countryId={countryId}
	//http://localhost:8080/city/all?stateId={stateId}
	//or
	//http://localhost:8080/api/city/all?countryId=1&stateId=5
	
	@Autowired
	private IStateNewService stateNewService;
	
	@GetMapping("all")
	ResponseEntity<?> getAllStateByCountryId(@RequestParam Long countryId){
		ResponseEntity<?> response = null;
		try {
			List<StateNew> stateList = stateNewService.getAllStateByCountryId(countryId);
			response = new ResponseEntity<List<StateNew>>(stateList,HttpStatus.OK);
		}catch (Exception e) {
			response = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}

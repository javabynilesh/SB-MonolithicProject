package com.mono.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mono.binding.CountryResponse;
import com.mono.entity.CountryNew;
import com.mono.service.ICountryNewService;

@RestController
@RequestMapping("/api")
public class CountryNewRest {
	
		@Autowired
		private ICountryNewService countryNewService;
		
		@GetMapping("/country")
		public ResponseEntity<?> getAllCountry(){
			ResponseEntity<?> response = null;
			try {
				Map<Long, String> countries = countryNewService.getAllCountry();
				response = new ResponseEntity<Map<Long,String>>(countries,HttpStatus.OK);
			} catch (Exception e) {
				response = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		}
		
		@GetMapping("/countrylist")
		public ResponseEntity<?> getAllCountryList(){
			ResponseEntity<?> response = null;
			try {
				List<CountryResponse> countries = countryNewService.getAllCountryList();
				response = new ResponseEntity<List<CountryResponse>>(countries,HttpStatus.OK);
			} catch (Exception e) {
				response = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		}
		
		
		
}

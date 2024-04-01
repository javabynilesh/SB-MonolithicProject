package com.mono.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.binding.CountryResponse;
import com.mono.entity.CountryNew;
import com.mono.repo.CountryNewRepository;
import com.mono.service.ICountryNewService;

@Service
public class CountryNewServiceImpl implements ICountryNewService{

	@Autowired
	private CountryNewRepository countryNewRepository;
		
	@Override
	public Map<Long, String> getAllCountry() {
		Map<Long, String> countriesMap = new HashMap<>();
		try {
			List<CountryNew> countries = countryNewRepository.findAll();
			countriesMap = countries.stream()
					.collect(Collectors.toMap(CountryNew::getCountryId, CountryNew::getCountryName));
			
		}catch (Exception e) {
			throw e;
		}
			
		return countriesMap;
	}

	@Override
	public List<CountryResponse> getAllCountryList() {
		List<CountryNew> countriesEntities = countryNewRepository.findAll();
		List<CountryResponse> countries = new ArrayList<>();
		for(CountryNew countryEntity: countriesEntities) {
			CountryResponse countryResponse = new CountryResponse();
			BeanUtils.copyProperties(countryEntity, countryResponse);
			countries.add(countryResponse);
		}
		return countries;
	}

}

package com.mono.service;

import java.util.List;
import java.util.Map;

import com.mono.binding.CountryResponse;
import com.mono.entity.CountryNew;

public interface ICountryNewService {
	public Map<Long, String> getAllCountry();
	public List<CountryResponse> getAllCountryList();
}

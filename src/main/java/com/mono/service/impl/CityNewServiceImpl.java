package com.mono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.entity.CityNew;
import com.mono.repo.CityNewRepository;
import com.mono.service.ICityNewService;

@Service
public class CityNewServiceImpl implements ICityNewService {
	
	@Autowired
	private CityNewRepository cityNewRepository;
	
	@Override
	public List<CityNew> getAllCityByStateId(Long stateId) {
		List<CityNew>  citiesByStateId = cityNewRepository.getAllCityByStateId(stateId);
		return citiesByStateId;
	}

}

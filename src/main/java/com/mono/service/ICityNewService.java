package com.mono.service;

import java.util.List;

import com.mono.entity.CityNew;

public interface ICityNewService {
	public List<CityNew> getAllCityByStateId(Long stateId);
}

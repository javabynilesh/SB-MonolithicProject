package com.mono.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.entity.StateNew;
import com.mono.repo.StateNewRepository;
import com.mono.service.IStateNewService;

@Service
public class StateNewServiceImpl implements IStateNewService {
	
	@Autowired
	private StateNewRepository stateNewRepository;
	
	@Override
	public List<StateNew> getAllStateByCountryId(Long countryId) {
		List<StateNew> stateList = stateNewRepository.getAllStateByCountryId(countryId);
		return stateList;
	}

}

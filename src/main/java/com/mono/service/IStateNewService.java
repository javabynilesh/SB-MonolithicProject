package com.mono.service;

import java.util.List;

import com.mono.entity.StateNew;

public interface IStateNewService {
	List<StateNew> getAllStateByCountryId(Long countryId);
}

package com.mono.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mono.entity.CityNew;

public interface CityNewRepository extends JpaRepository<CityNew, Long>{
	
	@Query(value=" from CityNew where state_id=:stateId")
	public List<CityNew> getAllCityByStateId(Long stateId);
	
	//try with
	// List<CityNew> findByStateId(@Param("id") Integer id);
}

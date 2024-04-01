package com.mono.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mono.entity.StateNew;

public interface StateNewRepository extends JpaRepository<StateNew, Long>{

	@Query(value="from StateNew where country_id=:countryId")
	public List<StateNew> getAllStateByCountryId(Long countryId);
}

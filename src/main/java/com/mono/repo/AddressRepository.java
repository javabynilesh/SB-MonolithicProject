package com.mono.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mono.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}

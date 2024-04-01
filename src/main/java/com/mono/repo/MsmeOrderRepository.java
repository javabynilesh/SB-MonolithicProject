package com.mono.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mono.entity.MsmeOrder;

public interface MsmeOrderRepository extends JpaRepository<MsmeOrder, Long>{

}

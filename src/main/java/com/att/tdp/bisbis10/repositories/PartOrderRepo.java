package com.att.tdp.bisbis10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.att.tdp.bisbis10.entities.PartOrder;

public interface PartOrderRepo extends JpaRepository<PartOrder, Long> {

}

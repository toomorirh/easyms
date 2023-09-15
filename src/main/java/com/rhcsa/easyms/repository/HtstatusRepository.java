package com.rhcsa.easyms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhcsa.easyms.model.HtStatus;


public interface HtstatusRepository extends JpaRepository<HtStatus, Long> {
  List<HtStatus> findByCodeContaining(String code);
}
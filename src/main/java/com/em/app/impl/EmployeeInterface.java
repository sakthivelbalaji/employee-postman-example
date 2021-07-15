package com.em.app.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.em.app.dto.EmployeeDto;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeInterface extends JpaRepository<EmployeeDto, Long> {



}
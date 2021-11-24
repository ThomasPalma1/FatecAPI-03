package com.ionx.ionx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ionx.ionx.domain.Status;


public interface StatusRepository extends JpaRepository <Status, Long> {

}

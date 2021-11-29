package com.ionx.ionx.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ionx.ionx.domain.Prospect;

@Repository
public interface ReadFileRepository extends CrudRepository<Prospect, Long> {

}

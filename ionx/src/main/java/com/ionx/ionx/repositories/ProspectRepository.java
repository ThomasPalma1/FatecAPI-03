package com.ionx.ionx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ionx.ionx.domain.Prospect;


public interface ProspectRepository extends JpaRepository<Prospect, Long>{

	@Query("select u from usuario u where u.nome = ?1")
	Prospect findByNome(String nome);

}

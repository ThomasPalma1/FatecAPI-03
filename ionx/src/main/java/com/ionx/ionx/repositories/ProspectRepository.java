package com.ionx.ionx.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ionx.ionx.domain.Prospect;


public interface ProspectRepository extends JpaRepository<Prospect, Long>{

	@Query("select u from usuario u where u.nome = ?1")
	Prospect findByNome(String nome);
	
	@Query("select p from Prospect p where p.produtEscolhido = ?1")
	List <Prospect> findInterestedProspects(String nome);
}

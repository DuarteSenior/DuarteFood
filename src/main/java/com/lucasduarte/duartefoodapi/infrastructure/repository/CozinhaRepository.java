package com.lucasduarte.duartefoodapi.infrastructure.repository;


import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}

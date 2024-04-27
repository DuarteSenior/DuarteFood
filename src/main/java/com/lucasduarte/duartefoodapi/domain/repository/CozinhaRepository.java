package com.lucasduarte.duartefoodapi.domain.repository;


import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByNome(String nome);

}

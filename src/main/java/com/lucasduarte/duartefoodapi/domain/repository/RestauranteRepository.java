package com.lucasduarte.duartefoodapi.domain.repository;

import com.lucasduarte.duartefoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}

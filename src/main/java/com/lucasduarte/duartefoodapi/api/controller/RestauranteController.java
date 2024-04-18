package com.lucasduarte.duartefoodapi.api.controller;

import com.lucasduarte.duartefoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.lucasduarte.duartefoodapi.domain.model.Restaurante;
import com.lucasduarte.duartefoodapi.domain.repository.RestauranteRepository;
import com.lucasduarte.duartefoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Optional<Restaurante>> buscar(@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(restaurante);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteBody = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restauranteBody);
        } catch (EntidadeNaoEcontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

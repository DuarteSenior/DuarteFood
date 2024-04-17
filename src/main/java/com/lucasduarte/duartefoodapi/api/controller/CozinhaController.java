package com.lucasduarte.duartefoodapi.api.controller;

import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import com.lucasduarte.duartefoodapi.domain.service.CadastroCozinhaService;
import com.lucasduarte.duartefoodapi.infrastructure.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;
    
    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Optional<Cozinha>> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        if (cozinha.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaBody = cozinhaService.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaBody);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Optional<Cozinha>> atualizar(@PathVariable Long cozinhaId,
                                                       @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtualOptional = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtualOptional.isPresent()) {
            Cozinha cozinhaAtual = cozinhaAtualOptional.get();
            cozinhaAtual.setNome(cozinha.getNome());

            Cozinha cozinhaAtualizada = cozinhaRepository.save(cozinhaAtual);
            return ResponseEntity.ok(Optional.of(cozinhaAtualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinhaId);

        if (cozinhaOptional.isPresent()) {
            Cozinha cozinha = cozinhaOptional.get();
            cozinhaRepository.delete(cozinha);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

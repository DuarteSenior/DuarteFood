package com.lucasduarte.duartefoodapi.domain.service;

import com.lucasduarte.duartefoodapi.domain.exception.EndidadeNaoEcontradaException;
import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtualOptional = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtualOptional.isPresent()) {
            Cozinha cozinhaAtual = cozinhaAtualOptional.get();
            cozinhaAtual.setNome(cozinha.getNome());

            return cozinhaRepository.save(cozinhaAtual);
        } else {
            throw new EndidadeNaoEcontradaException("Cozinha não encontrada!");
        }
    }


    public void excluir(Long cozinhaId) {
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinhaId);

        if (cozinhaOptional.isPresent()) {
            cozinhaRepository.deleteById(cozinhaId);
        } else {
            throw new EndidadeNaoEcontradaException("Cozinha não encontrada!");
        }


    }
}

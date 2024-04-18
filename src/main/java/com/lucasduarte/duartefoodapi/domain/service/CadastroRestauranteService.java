package com.lucasduarte.duartefoodapi.domain.service;


import com.lucasduarte.duartefoodapi.domain.exception.EntidadeNaoEcontradaException;
import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import com.lucasduarte.duartefoodapi.domain.model.Restaurante;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import com.lucasduarte.duartefoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEcontradaException("Não existe cadastro de cozinha com o ID " + cozinhaId));



        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

}

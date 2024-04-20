package com.lucasduarte.duartefoodapi.repository;

import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import org.junit.jupiter.api.BeforeEach;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CozinhaRepositoryTests {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Cozinha cozinha;
    @BeforeEach
    public void setup() {
        cozinha = Cozinha.builder()
                .nome("tailandesa")
                .build();
    }

    @Test
    public void giveCozinhaObject_whenSave_thenReturnsSavedCozinha() {

        Cozinha cozinhaSalva = cozinhaRepository.save(cozinha);

        assertThat(cozinhaSalva).isNotNull();
        assertThat(cozinhaSalva.getId()).isNotNull();

    }

    @Test
    public void givenCozinhasList_whenFindAll_thenReturnsCozinhasList() {
        Cozinha cozinha1 = Cozinha.builder()
                .nome("Brasileira")
                .build();
        cozinhaRepository.save(cozinha);
        cozinhaRepository.save(cozinha1);

        List<Cozinha> cozinhaList = cozinhaRepository.findAll();

        assertThat(cozinhaList).isNotNull();
        assertThat(cozinhaList.size()).isEqualTo(2);
    }

    @Test
    public void givenCozinhaObject_whenFindById_thenReturnCozinhaObject() {
        Cozinha cozinha = Cozinha.builder()
                .nome("Indiana")
                .build();
        cozinhaRepository.save(cozinha);

        Cozinha cozinhaDB = cozinhaRepository.findById(cozinha.getId()).get();

        assertThat(cozinhaDB).isNotNull();
    }


    @Test
    public void givenCozinhaObject_whenUpdateCozinha_thenReturnUpdated() {
        cozinhaRepository.save(cozinha);

        Cozinha savedCozinha = cozinhaRepository.findById(cozinha.getId()).get();
        savedCozinha.setNome("Coreana");
        Cozinha updatedCozinha = cozinhaRepository.save(savedCozinha);

        assertThat(updatedCozinha.getNome()).isEqualTo("Coreana");
    }

    @Test
    public void givenCozinhaObject_whenDelete_thenRemoveCozinha() {
        cozinhaRepository.save(cozinha);

        cozinhaRepository.delete(cozinha);
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinha.getId());

        assertThat(cozinhaOptional).isEmpty();
    }

}

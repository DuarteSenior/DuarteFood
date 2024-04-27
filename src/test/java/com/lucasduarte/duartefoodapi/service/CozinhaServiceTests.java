package com.lucasduarte.duartefoodapi.service;

import com.lucasduarte.duartefoodapi.domain.exception.EntidadeJaExiste;
import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import com.lucasduarte.duartefoodapi.domain.service.CadastroCozinhaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.Optional;


public class CozinhaServiceTests {

    @Mock
    private CozinhaRepository cozinhaRepository;

    @InjectMocks
    private CadastroCozinhaService cozinhaService;

    private Cozinha cozinha;

    @BeforeEach
    public void setup() {
        cozinhaRepository = Mockito.mock(CozinhaRepository.class);
        cozinhaService = new CadastroCozinhaService(cozinhaRepository);

        cozinha = Cozinha.builder()
                .id(3L)
                .nome("Brasileira")
                .build();
    }

    @Test
    public void givenCozinhaObject_whenSaveCozinha_thenReturnsCozinhaObject() {
        given(cozinhaRepository.findByNome(cozinha.getNome()))
                .willReturn(Optional.empty());
        given(cozinhaRepository.save(cozinha)).willReturn(cozinha);

        Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);

        Assertions.assertThat(cozinhaSalva).isNotNull();
    }

    @Test
    public void givenExistingCozinhaName_whenSaveCozinha_thenThrowsException() {
        given(cozinhaRepository.findByNome(cozinha.getNome()))
                .willReturn(Optional.of(cozinha));

        org.junit.jupiter.api.Assertions.assertThrows(EntidadeJaExiste.class, () -> {
            cozinhaService.salvar(cozinha);
        });
        verify(cozinhaRepository, never()).save(any(Cozinha.class));

    }

    @Test
    public void givenCozinhaId_whenFindCozinhaId_thenReturnUpdatedCozinhaObject() {
        given(cozinhaRepository.findById(cozinha.getId())).willReturn(Optional.of(cozinha));
        given(cozinhaRepository.save(cozinha)).willReturn(cozinha);

        cozinha.setNome("Eliomar");

        Cozinha cozinhaSalva = cozinhaService.atualizar(3L, cozinha);

        Assertions.assertThat(cozinhaSalva).isNotNull();
        Assertions.assertThat(cozinhaSalva.getNome()).isEqualTo("Eliomar");
    }

    @Test
    public void givenCozinhaId_whenDeleteCozinha_thenReturnNothing() {
        given(cozinhaRepository.findById(cozinha.getId())).willReturn(Optional.of(cozinha));

        willDoNothing().given(cozinhaRepository).deleteById(cozinha.getId());

        cozinhaService.excluir(cozinha.getId());

        verify(cozinhaRepository, times(1)).deleteById(cozinha.getId());
    }

}

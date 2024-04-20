package com.lucasduarte.duartefoodapi.repository;

import com.lucasduarte.duartefoodapi.domain.model.Cozinha;
import com.lucasduarte.duartefoodapi.domain.model.Restaurante;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import org.junit.jupiter.api.BeforeEach;
import com.lucasduarte.duartefoodapi.domain.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RestauranteRepositoryTests {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Restaurante restaurante;
    @BeforeEach
    public void setup() {
        Cozinha cozinha = Cozinha.builder().nome("chinesa").build();
        Cozinha cozinhaSalva = cozinhaRepository.save(cozinha);

        restaurante = Restaurante.builder()
                .nome("Restaurante Thai")
                .taxaFrete(new BigDecimal("20.00"))
                .cozinha(cozinhaSalva)
                .build();
    }

    @Test
    public void giveRestauranteObject_whenSave_thenReturnsSavedRestaurante() {

        Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

        assertThat(restauranteSalvo).isNotNull();
        assertThat(restauranteSalvo.getId()).isNotNull();

    }

    @Test
    public void givenRestaurantesList_whenFindAll_thenReturnsRestaurantesList() {
        Cozinha cozinha = Cozinha.builder().nome("chinesa").build();
        Cozinha cozinhaSalva = cozinhaRepository.save(cozinha);

        Restaurante restaurante1 = Restaurante.builder()
                .nome("Restaurante Brasileiro")
                .taxaFrete(new BigDecimal("10.00"))
                .cozinha(cozinhaSalva)
                .build();
        restauranteRepository.save(restaurante);
        restauranteRepository.save(restaurante1);

        List<Restaurante> restauranteList = restauranteRepository.findAll();

        assertThat(restauranteList).isNotNull();
        assertThat(restauranteList.size()).isEqualTo(2);
    }

    @Test
    public void givenRestauranteObject_whenFindById_thenReturnRestauranteObject() {
        restauranteRepository.save(restaurante);

        Restaurante restauranteDB = restauranteRepository.findById(restaurante.getId()).get(); // Altere o tipo para Restaurante

        assertThat(restauranteDB).isNotNull();
    }


    @Test
    public void givenRestauranteObject_whenUpdateRestaurante_thenReturnUpdated() {
        restauranteRepository.save(restaurante);

        Restaurante savedRestaurante = restauranteRepository.findById(restaurante.getId()).get();
        savedRestaurante.setNome("Restaurante Coreano");
        Restaurante updatedRestaurante = restauranteRepository.save(savedRestaurante);

        assertThat(updatedRestaurante.getNome()).isEqualTo("Restaurante Coreano");
    }

    @Test
    public void givenRestauranteObject_whenDelete_thenRemoveRestaurante() {
        restauranteRepository.save(restaurante);

        restauranteRepository.delete(restaurante);
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(restaurante.getId());

        assertThat(restauranteOptional).isEmpty();
    }

}

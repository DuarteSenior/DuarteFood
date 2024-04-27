package com.lucasduarte.duartefoodapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasduarte.duartefoodapi.domain.repository.CozinhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CozinhaControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        cozinhaRepository.deleteAll();

    }
}

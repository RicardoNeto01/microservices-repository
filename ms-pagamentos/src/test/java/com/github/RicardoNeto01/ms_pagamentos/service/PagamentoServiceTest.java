package com.github.RicardoNeto01.ms_pagamentos.service;

import com.github.RicardoNeto01.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.RicardoNeto01.ms_pagamentos.repository.PagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTest {

    /*
    Referenciando PagamentoService
    @Autowired = sem injeção
    Mock = injentando.
    */
    @InjectMocks
    private PagamentoService service;
    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 10L;

        // Precisa simular o comportamento do objeto mockado
        // Delete = quando id existe
        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        // Delete = quando id não existe
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        // Delete = primeiro caso, deleta.
        // não execute quando:
        Mockito.doNothing().when(repository).deleteById(existingId);
    }

    @Test
    @DisplayName("Delete deveria não fazer nada quando id existe")
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(
                () -> {
                    service.deletePagamento(existingId);
                }
        );
    }
    @Test
    @DisplayName("delete deveria lançar exceção quando id não existe")
    public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    service.deletePagamento(nonExistingId);
                }
                );
    }
}

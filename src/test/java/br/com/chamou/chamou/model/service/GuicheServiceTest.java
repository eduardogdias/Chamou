package br.com.chamou.chamou.model.service;

import br.com.chamou.chamou.model.entity.Guiche;
import br.com.chamou.chamou.model.repository.GuicheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GuicheServiceTest {

    @Mock // cria uma dependência fake com métodos vazios e eu digo o que deve ser retornado
    private GuicheRepository guicheRepository;

    @InjectMocks // injeta automaticamente os mocks criados nas dependências dela
    private GuicheService guicheService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this); // inicia os mocks dessa classe
    }

    @Test
    @DisplayName("Deve retornar guiche quando ID existir")
    void findByIdCase1() {
        // Arrange
        Guiche guiche = new Guiche(1L, "01", "Juliano Silva", true);
        when(guicheRepository.findById(1L)).thenReturn(Optional.of(guiche));


        // Act
        Guiche result =  guicheService.findById(1L);

        // Assert
        assertEquals(guiche, result);
        verify(guicheRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve lancar excecao quando guiche nao existir")
    void findByIdCase2() {

        when(guicheRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> guicheService.findById(99L));

    }

    @Test
    @DisplayName("Deve deletar guiche existente")
    void deleteCase1(){
        Guiche guiche = new Guiche(1L, "01", "Juliano Silva", true);
        when(guicheRepository.findById(1L)).thenReturn(Optional.of(guiche));

        Guiche result = guicheService.delete(1L);

        assertEquals(guiche, result);
    }

    @Test
    @DisplayName("Deve lancar excecao ao tentar deletar guiche inexistente")
    void deleteCase2(){
        when(guicheRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> guicheService.delete(99L));
    }

}
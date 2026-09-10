package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.service.AlunoService;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository; 

    @InjectMocks
    private AlunoService alunoService;


    @Test
    void deveSalvarAlunoQuandoNomeForValido() {
        AlunoEntity aluno = new AlunoEntity("rodrigo");
        alunoService.salvarAluno(aluno);

        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void naoDeveSalvarAlunoQuandoNomeForNulo() {
        AlunoEntity aluno = new AlunoEntity(null);
        alunoService.salvarAluno(aluno);
        verify(alunoRepository, never()).save(any(AlunoEntity.class));
    }


    @Test
    void deveRetornarListaDeAlunosQuandoExistirem() {
        List<AlunoEntity> alunos = Arrays.asList(
                new AlunoEntity("Rafael"),
                new AlunoEntity("Maria")
        );
        when(alunoRepository.findAll()).thenReturn(alunos);

        List<AlunoEntity> resultado = alunoService.retornaList();

        assertEquals(2, resultado.size());
        assertEquals(alunos, resultado);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremAlunos() {
        when(alunoRepository.findAll()).thenReturn(List.of());

        List<AlunoEntity> resultado = alunoService.retornaList();

        assertTrue(resultado.isEmpty());
    }


    @Test
    void deveRetornarAlunoQuandoIdExistir() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Optional<AlunoEntity> resultado = alunoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Rafael", resultado.get().getNome());
    }

    @Test
    void deveRetornarOptionalVazioQuandoIdNaoExistir() {
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<AlunoEntity> resultado = alunoService.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }
}
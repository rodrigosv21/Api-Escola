/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */

package com.example;


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
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.repository.NotaAlunoRepository;
import com.example.apiteste.service.NotaAlunoService;
import com.example.apiteste.status.AlunoStatus;

@ExtendWith(MockitoExtension.class)
class NotaAlunoServiceTest {

    @Mock
    private NotaAlunoRepository notaAlunoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private NotaAlunoService notaAlunoService;


    @Test
    void deveSalvarNotaQuandoAlunoExistir() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        NotaAlunoEntity nota = new NotaAlunoEntity(8.5, null);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(notaAlunoRepository.save(nota)).thenReturn(nota);

        Optional<NotaAlunoEntity> resultado = notaAlunoService.saveNotas(1L, nota);

        assertTrue(resultado.isPresent());
        assertEquals(aluno, nota.getAlunoEntity());
        verify(notaAlunoRepository, times(1)).save(nota);
    }

    @Test
    void naoDeveSalvarNotaQuandoAlunoNaoExistir() {
        NotaAlunoEntity nota = new NotaAlunoEntity(8.5, null);
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

    
        Optional<NotaAlunoEntity> resultado = notaAlunoService.saveNotas(99L, nota);

        assertTrue(resultado.isEmpty());
        verify(notaAlunoRepository, never()).save(any(NotaAlunoEntity.class));
    }


    @Test
    void deveRetornarNotasDoAluno() {
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(7.0, null),
                new NotaAlunoEntity(8.0, null)
        );
        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        List<NotaAlunoEntity> resultado = notaAlunoService.retornaNotasPorId(1L);

        assertEquals(2, resultado.size());
    }

    
    @Test
    void naoDeveCalcularMediaQuandoTiverMenosDeQuatroNotas() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(7.0, aluno),
                new NotaAlunoEntity(8.0, aluno)
        );
        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        notaAlunoService.retornaMediaAluno(1L);

        verify(alunoRepository, never()).save(any(AlunoEntity.class));
    }

    @Test
    void deveDefinirStatusReprovadoQuandoMediaMenorQue6() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(4.0, aluno),
                new NotaAlunoEntity(5.0, aluno),
                new NotaAlunoEntity(4.0, aluno),
                new NotaAlunoEntity(3.0, aluno)
        ); 
        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        notaAlunoService.retornaMediaAluno(1L);

        assertEquals(AlunoStatus.REPROVADO, aluno.getAlunoStatus());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void deveDefinirStatusRecuperacaoQuandoMediaEntre6E7() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(6.0, aluno),
                new NotaAlunoEntity(6.0, aluno),
                new NotaAlunoEntity(6.5, aluno),
                new NotaAlunoEntity(6.5, aluno)
        );
        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        notaAlunoService.retornaMediaAluno(1L);

        assertEquals(AlunoStatus.RECUPERACAO, aluno.getAlunoStatus());
    }

    @Test
    void deveDefinirStatusAprovadoQuandoMediaMaiorOuIgualA7() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(8.0, aluno),
                new NotaAlunoEntity(9.0, aluno),
                new NotaAlunoEntity(7.0, aluno),
                new NotaAlunoEntity(8.0, aluno)
        );
        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        notaAlunoService.retornaMediaAluno(1L);

        assertEquals(AlunoStatus.APROVADO, aluno.getAlunoStatus());
    }

    @Test
    void deveDefinirStatusAprovadoQuandoMediaForExatamente10() {
        AlunoEntity aluno = new AlunoEntity("Rafael");
        List<NotaAlunoEntity> notas = List.of(
                new NotaAlunoEntity(10.0, aluno),
                new NotaAlunoEntity(10.0, aluno),
                new NotaAlunoEntity(10.0, aluno),
                new NotaAlunoEntity(10.0, aluno)
        ); 

        when(notaAlunoRepository.findByAlunoEntityId(1L)).thenReturn(notas);

        notaAlunoService.retornaMediaAluno(1L);

        assertEquals(AlunoStatus.APROVADO, aluno.getAlunoStatus());
    }
}
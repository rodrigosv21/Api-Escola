/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5Suite.java to edit this template
 */

package com.example;


import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.repository.NotaAlunoRepository;
import com.example.apiteste.service.NotaAlunoService;
import com.example.apiteste.status.AlunoStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotaAlunoServiceTest {

    @Mock
    private NotaAlunoRepository notaAlunoRepository;

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private NotaAlunoService notaAlunoService;


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
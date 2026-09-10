/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.repository.NotaAlunoRepository;
import com.example.apiteste.status.AlunoStatus;

/**
 *
 * @author sddro
 */
@Service 
public class NotaAlunoService {

    private final NotaAlunoRepository notaAlunoRepository;
    private final AlunoRepository alunoRepository;

    public NotaAlunoService(NotaAlunoRepository notaAlunoRepository, AlunoRepository alunoRepository) {
        this.notaAlunoRepository = notaAlunoRepository;
        this.alunoRepository = alunoRepository;
    }

    public void saveNotas(Long id, NotaAlunoEntity notaAlunoEntity){
        Optional<AlunoEntity> check = alunoRepository.findById(id);

        if(check.isPresent()){
            AlunoEntity aluno = check.get();
            notaAlunoEntity.setAlunoEntity(aluno);
            notaAlunoRepository.save(notaAlunoEntity);
        }else{
            return;
        }
    }

    public List<NotaAlunoEntity> retornaNotasPorId(Long id){
        List<NotaAlunoEntity> byAlunoEntityId = notaAlunoRepository.findByAlunoEntityId(id);
        return byAlunoEntityId;
    }

    private void atualizarStatus(NotaAlunoEntity notaAlunoEntity, Double media) {
        if(media < 6){
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.REPROVADO);
            alunoRepository.save(aluno);
        }else if (media >= 6 && media < 7) {
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.RECUPERACAO);
            alunoRepository.save(aluno);
        }
        else if(media >= 7 && media <= 10){
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.APROVADO);
            alunoRepository.save(aluno);
        }
    }

    public void retornaMediaAluno(Long id){
        List<NotaAlunoEntity> notaAlunoEntity = retornaNotasPorId(id);

        Double media = 0.0; //valor atualizado a cada giro no lopping

        AlunoEntity aluno;

        for (NotaAlunoEntity notaAluno : notaAlunoEntity) {
            media = media + notaAluno.getNotas(); // vai somar o utimo valor + o proximo valor da lista
        }

        media = media / 4; // pegar a soma final e divir pelo total de periodo e retorna a media

        atualizarStatus(notaAlunoEntity.getLast(), media);
    }

    

    

}

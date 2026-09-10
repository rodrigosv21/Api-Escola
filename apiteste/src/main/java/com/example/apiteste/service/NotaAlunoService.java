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

    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.service;

import org.springframework.stereotype.Service;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.repository.AlunoRepository;

/**
 *
 * @author sddro
 */

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    //metodo de salvar Aluno
    public void salvarAluno(AlunoEntity aluno){
        if(aluno.getNome() == null){
            return;
        }
        alunoRepository.save(aluno);
    }

    

}

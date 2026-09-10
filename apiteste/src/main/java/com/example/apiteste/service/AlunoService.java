/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.service;

import java.util.List;
import java.util.Optional;

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

    public List<AlunoEntity> retornaList(){
        List<AlunoEntity> retornaListaDeAlunos = alunoRepository.findAll();
        return retornaListaDeAlunos;
    }

    public AlunoEntity buscarUSerPorId(Long id){
        Optional<AlunoEntity> idAluno = alunoRepository.findById(id);

        AlunoEntity alunoEntity;
        
        if(idAluno.isPresent()){
            alunoEntity = idAluno.get();
        }else{
            return null;
        }

        return alunoEntity;
    }

    

}

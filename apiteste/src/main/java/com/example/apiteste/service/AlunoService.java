/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.service;

import java.util.List;
import java.util.Optional;

import com.example.apiteste.exception.ExceptionValidation;
import org.springframework.stereotype.Service;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.repository.AlunoRepository;

/**
 *
 * @author sddro
 * class responsavel por salvar aluno(nome)
 * -- retorna todos os alunos[
 * notas, status
 * ]
 * buscar aluno por id
 */

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    //metodo de salvar Aluno
    public void salvarAluno(AlunoEntity aluno) {
        if (aluno.getNome().isBlank()) {
            throw new ExceptionValidation("Digite um nome valido");
        }
        alunoRepository.save(aluno);
    }

    public List<AlunoEntity> retornaList() {
        return alunoRepository.findAll();
    }

    public Optional<AlunoEntity> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.service;

import com.example.apiteste.DTO.AlunoRequestDTO;
import com.example.apiteste.DTO.AlunoResponseDTO;
import com.example.apiteste.exception.ExceptionValidation;
import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.status.AlunoStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public AlunoResponseDTO salvarAl(AlunoRequestDTO al) {

        //check validar de nenhum campo null
        if (al.getNome() == null || al.getNome().isBlank() || al.getSerie() == null) {
            throw new ExceptionValidation("Digite campos nome e serie");
        }

        //criar aluno
        AlunoEntity alunoEntity = new AlunoEntity();

        //settagem
        alunoEntity.setNome(al.getNome());
        alunoEntity.setSerie(al.getSerie());
        alunoEntity.setAlunoStatus(AlunoStatus.PENDENTE);
        alunoEntity.setNotaAlunoEntity(new ArrayList<>());

        //salvo
        alunoRepository.save(alunoEntity);

        return AlunoResponseDTO.from(alunoEntity);

    }

    public List<AlunoResponseDTO> retornaList() {
        List<AlunoEntity> all = alunoRepository.findAll();
        return all.stream().map(AlunoResponseDTO::from).toList();
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Optional<AlunoEntity> byId = alunoRepository.findById(id);
        return byId.map(AlunoResponseDTO::from).orElseThrow(() -> new ExceptionValidation("Aluno não existe"));
    }
}

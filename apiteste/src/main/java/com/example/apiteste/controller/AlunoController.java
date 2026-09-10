/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.service.AlunoService;

/**
 *
 * @author sddro
 * class que ira receber requisiçoes de alunos
 */

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public void salvarAluno(@RequestBody AlunoEntity aluno){
        alunoService.salvarAluno(aluno);
    }

    @GetMapping
    public List<AlunoEntity> retornaList(){
        return alunoService.retornaList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    

}

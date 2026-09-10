/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.controller;

import java.util.List;

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
 */

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/saveALuno")
    public void salvarAluno(@RequestBody AlunoEntity aluno){
        alunoService.salvarAluno(aluno);
    }

    @GetMapping("/getAll")
    public List<AlunoEntity> retornaList(){
        return alunoService.retornaList();
    }

    @GetMapping("/find/{id}")
    public AlunoEntity buscarAlunoPorId(@PathVariable Long id){
        return alunoService.buscarUSerPorId(id);
    }
    

}

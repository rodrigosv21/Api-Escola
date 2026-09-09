/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.controller;

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
@RequestMapping("/salvarAluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("")
    public void salvarAluno(@RequestBody AlunoEntity aluno){
        alunoService.salvarAluno(aluno);
    }

    

}

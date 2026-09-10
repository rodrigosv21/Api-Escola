/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.service.NotaAlunoService;

/**
 *
 * @author sddro
 *  * class que ira receber requisiçoes de notas
 */

@RestController 
@RequestMapping("/alunos/{id}/notas")
public class NotaAlunoController {

    private final NotaAlunoService notaAlunoService;

    public NotaAlunoController(NotaAlunoService notaAlunoService) {
        this.notaAlunoService = notaAlunoService;
    }

    @PostMapping
    public ResponseEntity<NotaAlunoEntity> salvar(@PathVariable Long id, @RequestBody NotaAlunoEntity nota){
        return notaAlunoService.saveNotas(id, nota)
        .map(n -> ResponseEntity.status(HttpStatus.CREATED).body(n))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<NotaAlunoEntity> retornaNotasPorId(@PathVariable Long id){
        List<NotaAlunoEntity> byAlunoEntityId = notaAlunoService.retornaNotasPorId(id);
        return byAlunoEntityId;
    }

    @PostMapping("/media")
    public void calcularMedia(@PathVariable Long id) {
    notaAlunoService.retornaMediaAluno(id);
    }
}

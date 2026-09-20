/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.controller;

import com.example.apiteste.DTO.AlunoRequestDTO;
import com.example.apiteste.DTO.AlunoResponseDTO;
import com.example.apiteste.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AlunoResponseDTO> salvarAluno(@RequestBody AlunoRequestDTO aluno){
        return ResponseEntity.ok(alunoService.salvarAl(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> retornaList(){
        return ResponseEntity.ok(alunoService.retornaList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarAlunoPorId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }
    

}

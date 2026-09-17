package com.example.apiteste.controller;

import java.util.List;

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
 */

@RestController 
@RequestMapping("/nota")
public class NotaAlunoController {

    private final NotaAlunoService notaAlunoService;

    public NotaAlunoController(NotaAlunoService notaAlunoService) {
        this.notaAlunoService = notaAlunoService;
    }

    @PostMapping("/saveNotas/{id}")
    public void salvarNotas(@PathVariable Long id , @RequestBody NotaAlunoEntity notaAlunoEntity){
        notaAlunoService.saveNotas(id, notaAlunoEntity);
    }

    @GetMapping("/buscarNotas/{id}")
    public List<NotaAlunoEntity> retornaNotasPorId(@PathVariable Long id){
        return notaAlunoService.retornaNotasPorId(id);
    }

    @GetMapping("/media/{id}")
    public void calcularMedia(@PathVariable Long id) {
    notaAlunoService.retornaMediaAluno(id);
    }


}

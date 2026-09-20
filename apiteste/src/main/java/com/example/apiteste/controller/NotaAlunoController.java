package com.example.apiteste.controller;

import com.example.apiteste.DTO.NotaAlunoRequestDTO;
import com.example.apiteste.DTO.NotaAlunoResponseDTO;
import com.example.apiteste.service.NotaAlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void salvarNotas(@PathVariable Long id, @Valid @RequestBody NotaAlunoRequestDTO notaAl){
        notaAlunoService.saveNotas(id, notaAl);
    }

    @GetMapping("/buscarNotas/{id}")
    public ResponseEntity<List<NotaAlunoResponseDTO>> retornaNotasPorId(@PathVariable Long id){
        return ResponseEntity.ok(notaAlunoService.retornaNotasPorId(id));
    }

    @GetMapping("/media/{id}")
    public void calcularMedia(@PathVariable Long id) {
        notaAlunoService.retornaMediaAluno(id);
    }


}

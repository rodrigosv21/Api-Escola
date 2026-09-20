package com.example.apiteste.DTO;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.status.AlunoStatus;
import lombok.Data;

import java.util.List;

@Data
public class AlunoResponseDTO {

    private Long id;
    private String nome;

    private Integer serie;

    private List<Double> notas;

    private AlunoStatus alunoStatus;

    public AlunoResponseDTO(Long id,String nome, Integer serie, List<Double> notas, AlunoStatus alunoStatus) {
        this.id = id;
        this.nome = nome;
        this.serie = serie;
        this.notas = notas;
        this.alunoStatus = alunoStatus;
    }

    public static AlunoResponseDTO from(AlunoEntity al) {
        List<Double> notas = al.getNotaAlunoEntity().stream()
                .map(NotaAlunoEntity::getNota)
                .toList();

        return new AlunoResponseDTO(al.getId(),al.getNome(), al.getSerie(), notas, al.getAlunoStatus());
    }
}
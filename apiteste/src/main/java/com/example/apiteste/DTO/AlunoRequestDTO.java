package com.example.apiteste.DTO;


import lombok.Data;

@Data
public class AlunoRequestDTO {
    private String nome;
    private Integer serie;

    public AlunoRequestDTO(String nome, Integer serie) {
        this.nome = nome;
        this.serie = serie;
    }
}

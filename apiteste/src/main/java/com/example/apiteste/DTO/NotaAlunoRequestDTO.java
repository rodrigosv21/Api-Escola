package com.example.apiteste.DTO;

import lombok.Data;
import lombok.Getter;

@Data
public class NotaAlunoRequestDTO {
    private Double nota;

    public NotaAlunoRequestDTO(Double nota) {
        this.nota = nota;
    }
}

package com.example.apiteste.DTO;

import com.example.apiteste.model.NotaAlunoEntity;
import lombok.Data;

@Data
public class NotaAlunoResponseDTO {

    private Double nota;

    private String nomeAluno;

    public NotaAlunoResponseDTO(Double nota, String nomeAluno ) {
        this.nota = nota;
        this.nomeAluno = nomeAluno;
    }

    public static NotaAlunoResponseDTO from(NotaAlunoEntity al) {
        return new NotaAlunoResponseDTO(al.getNota(), al.getAlunoEntity().getNome());
    }


}

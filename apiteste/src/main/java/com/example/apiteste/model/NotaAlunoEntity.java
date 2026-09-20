package com.example.apiteste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 *
 * modelagem de camada das notas
 */
@Data
@Entity
public class NotaAlunoEntity {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Max(value = 10, message = "valor maximo ")
    @Min(value = 0, message = "menor nota")
    private Double nota;

    @JsonBackReference
    @ManyToOne
    private AlunoEntity alunoEntity;


    public NotaAlunoEntity() {
    }

    public NotaAlunoEntity(Double notas, AlunoEntity alunoEntity) {
        this.nota = notas;
        this.alunoEntity = alunoEntity;
    }

}

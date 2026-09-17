package com.example.apiteste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    private Double notas;

    @JsonBackReference
    @ManyToOne
    private AlunoEntity alunoEntity;

    //tentativa caso user não informe passa zerada
    public NotaAlunoEntity() {
        notas = 0.0;
    }

    public NotaAlunoEntity(Double notas, AlunoEntity alunoEntity) {
        this.notas = notas;
        this.alunoEntity = alunoEntity;
    }

}

package com.example.apiteste.model;

import com.example.apiteste.status.AlunoStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 *
 * camada de modelagem do aluno
 */
@Data
@Entity
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private Integer serie;

    @JsonManagedReference
    @OneToMany(mappedBy = "alunoEntity")
    private List<NotaAlunoEntity> notaAlunoEntity;

    @Enumerated(value = EnumType.STRING)
    private AlunoStatus alunoStatus;

    public AlunoEntity() {
    }

    public AlunoEntity(String nome) {
        this.nome = nome;
    }

}

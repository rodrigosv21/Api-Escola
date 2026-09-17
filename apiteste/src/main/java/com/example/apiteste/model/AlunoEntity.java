package com.example.apiteste.model;

import com.example.apiteste.status.AlunoStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

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

    private String nome;

    @Column(unique = true, length = 8)
    private String nMatricula;

    @JsonManagedReference
    @OneToMany(mappedBy = "alunoEntity")
    private List<NotaAlunoEntity> notaAlunoEntity;

    @Enumerated(value = EnumType.STRING)
    private AlunoStatus alunoStatus;

    public AlunoEntity() {
    }

    public AlunoEntity(String nome) {
        this.nome = nome;
        this.alunoStatus = AlunoStatus.PENDENTE;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.model;

import java.util.List;

import com.example.apiteste.status.AlunoStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * camada de modelagem do aluno
 */
@Data
@Entity
public class AlunoEntity {

    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy="alunoEntity")
    private List<NotaAlunoEntity> notaAlunoEntity;

    @Enumerated(EnumType.STRING)
    @Setter
    private AlunoStatus alunoStatus;

    @Override
    public String toString() {
        return "AlunoEntity [id=" + id + ", nome=" + nome + ", notaAlunoEntity=" + notaAlunoEntity + ", alunoStatus="
                + alunoStatus + "]";
    }

    public AlunoEntity() {
    }

    public AlunoEntity(String nome) {
        this.nome = nome;
        this.alunoStatus = AlunoStatus.PENDENTE;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }


}

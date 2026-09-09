/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.model;

import java.util.List;

import com.example.apiteste.status.AlunoStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 *
 * camada de modelagem do aluno
 */
@Entity 
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy="alunoEntity")
    private List<NotaAlunoEntity> notaAlunoEntity;

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
        this.alunoStatus = alunoStatus.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<NotaAlunoEntity> getNotaAlunoEntity() {
        return notaAlunoEntity;
    }

    public void setNotaAlunoEntity(List<NotaAlunoEntity> notaAlunoEntity) {
        this.notaAlunoEntity = notaAlunoEntity;
    }

    public AlunoStatus getAlunoStatus() {
        return alunoStatus;
    }

    public void setAlunoStatus(AlunoStatus alunoStatus) {
        this.alunoStatus = alunoStatus;
    }

}

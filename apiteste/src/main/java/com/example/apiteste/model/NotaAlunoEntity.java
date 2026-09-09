/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 *
 * modelagem de camada das notas
 */
@Entity
public class NotaAlunoEntity {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Double notas;

    @ManyToOne
    private AlunoEntity alunoEntity;

    public NotaAlunoEntity() {
    }

    public NotaAlunoEntity(Double notas, AlunoEntity alunoEntity) {
        this.notas = notas;
        this.alunoEntity = alunoEntity;
    }

    public Long getId() {
        return id;
    }

    public Double getNotas() {
        return notas;
    }

    public void setNotas(Double notas) {
        this.notas = notas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlunoEntity getAlunoEntity() {
        return alunoEntity;
    }

    public void setAlunoEntity(AlunoEntity alunoEntity) {
        this.alunoEntity = alunoEntity;
    }
}

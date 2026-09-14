/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apiteste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public NotaAlunoEntity() {
    }

    public NotaAlunoEntity(Double notas, AlunoEntity alunoEntity) {
        this.notas = notas;
        this.alunoEntity = alunoEntity;
    }

}

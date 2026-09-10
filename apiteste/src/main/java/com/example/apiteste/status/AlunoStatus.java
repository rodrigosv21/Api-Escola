/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

package com.example.apiteste.status;

/**
 *
 * @author sddro
 */
public enum AlunoStatus {
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    RECUPERACAO("Recuperação"),
    REPROVADO("Reprovado");

    private final String nome;

    AlunoStatus(String nome) {
        this.nome = nome;
    }

}

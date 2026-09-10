/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.apiteste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiteste.model.NotaAlunoEntity;

/**
 *
 * @author sddro
 */
public interface NotaAlunoRepository extends JpaRepository<NotaAlunoEntity, Long> {

    List<NotaAlunoEntity> findByAlunoEntityId(Long alunoId);
}

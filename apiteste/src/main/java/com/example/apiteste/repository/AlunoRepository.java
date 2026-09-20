/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.apiteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiteste.model.AlunoEntity;

import java.util.Optional;

/**
 *
 * @author sddro
 */
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}

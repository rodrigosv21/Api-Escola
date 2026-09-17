package com.example.apiteste.validador;

import com.example.apiteste.exception.ExceptionValidation;
import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotasValidation {


    public void validarNotas(AlunoEntity alunoEntity){
        if (alunoEntity.getNotaAlunoEntity().size() == 4){
            throw new ExceptionValidation("os 4 campos da media ja foram cadastrado");
        }
    }

    public void validarNotas(List<NotaAlunoEntity> notaAluno){
        if (notaAluno.isEmpty()){
            throw new ExceptionValidation("Usuario sem notas lançadas");
        }
    }

}

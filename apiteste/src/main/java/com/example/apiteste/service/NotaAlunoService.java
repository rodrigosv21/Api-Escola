package com.example.apiteste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.repository.NotaAlunoRepository;
import com.example.apiteste.status.AlunoStatus;

/**
 *
 * @author sddro
 * class responsavel pela regra de notas onde 
 * -salva notas
 * -retorna a nota pelo id
 * - faz a logica da soma do aluno e atualiza o status do aluno de acordo com a nota
 * se menor que 6 -> reprovado
 * se for 6 -> recuperação
 * se maior ou igual a 7 e menor ou igual a 10 -> aprovado
 */

@Service 
public class NotaAlunoService {

    private final NotaAlunoRepository notaAlunoRepository;
    private final AlunoRepository alunoRepository;

    public NotaAlunoService(NotaAlunoRepository notaAlunoRepository, AlunoRepository alunoRepository) {
        this.notaAlunoRepository = notaAlunoRepository;
        this.alunoRepository = alunoRepository;
    }

    public Optional<NotaAlunoEntity> saveNotas(Long id, NotaAlunoEntity notaAlunoEntity){
        Optional<AlunoEntity> check = alunoRepository.findById(id);
        
        if(check.isPresent()){
            AlunoEntity aluno = check.get();
            notaAlunoEntity.setAlunoEntity(aluno);
            return Optional.of(notaAlunoRepository.save(notaAlunoEntity));
        }
        return Optional.empty();
    }

    public List<NotaAlunoEntity> retornaNotasPorId(Long id){
        List<NotaAlunoEntity> byAlunoEntityId = notaAlunoRepository.findByAlunoEntityId(id);
        return byAlunoEntityId;
    }

    public void retornaMediaAluno(Long id){
        List<NotaAlunoEntity> notas = retornaNotasPorId(id);
        if (notas.size() < 4) {
            return; //caso o numero de notas seja menor que 4 nem execulta
        }
        Double media = 0.0;
        for (NotaAlunoEntity nota : notas) {
            media += nota.getNotas();
        }
        media = media / notas.size();
        atualizarStatus(notas.getLast(), media);
    }


    private void atualizarStatus(NotaAlunoEntity notaAlunoEntity, Double media) {
        if(media < 6){
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.REPROVADO);
            alunoRepository.save(aluno);
        }else if (media >= 6 && media < 7) {
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.RECUPERACAO);
            alunoRepository.save(aluno);
        }
        else if(media >= 7 && media <= 10){
            AlunoEntity aluno = notaAlunoEntity.getAlunoEntity();
            aluno.setAlunoStatus(AlunoStatus.APROVADO);
            alunoRepository.save(aluno);
        }
    }

    

    

}

package com.example.apiteste.service;

import com.example.apiteste.exception.ExceptionValidation;
import com.example.apiteste.model.AlunoEntity;
import com.example.apiteste.model.NotaAlunoEntity;
import com.example.apiteste.repository.AlunoRepository;
import com.example.apiteste.repository.NotaAlunoRepository;
import com.example.apiteste.status.AlunoStatus;
import com.example.apiteste.validador.NotasValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author sddro
 */
@Service 
public class NotaAlunoService {

    private final NotaAlunoRepository notaAlunoRepository;
    private final AlunoRepository alunoRepository;
    private final NotasValidation notasValidation;

    public NotaAlunoService(NotaAlunoRepository notaAlunoRepository, AlunoRepository alunoRepository, NotasValidation notasValidation) {
        this.notaAlunoRepository = notaAlunoRepository;
        this.alunoRepository = alunoRepository;
        this.notasValidation = notasValidation;
    }

    public void saveNotas(Long id, NotaAlunoEntity notaAlunoEntity){
        Optional<AlunoEntity> check = alunoRepository.findById(id); //idependente ja traz se tem ou não se tiver vai pra proxima linha
        AlunoEntity aluno = check.orElseThrow(() -> new ExceptionValidation("Aluno não existe")); //se tiver pegar o valor ou retona uma exception
        // ao salvar a nota tem que verificar se ja ultrpassou as 4
        notasValidation.validarNotas(aluno);

        notaAlunoEntity.setAlunoEntity(aluno);
        notaAlunoRepository.save(notaAlunoEntity);
    }

    public List<NotaAlunoEntity> retornaNotasPorId(Long id){
        return notaAlunoRepository.findByAlunoEntityId(id);
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

    public void retornaMediaAluno(Long id){
        List<NotaAlunoEntity> notaAlunoEntity = retornaNotasPorId(id);

        notasValidation.validarNotas(notaAlunoEntity);

        double media = 0.0; //valor atualizado a cada giro no lopping

        for (NotaAlunoEntity notaAluno : notaAlunoEntity) {

            media = media + notaAluno.getNotas(); // vai somar o utimo valor + o proximo valor da lista
        }

        media = media / 4; // pegar a soma final e divir pelo total de periodo e retorna a media

        atualizarStatus(notaAlunoEntity.getLast(), media);
    }

    

    

}

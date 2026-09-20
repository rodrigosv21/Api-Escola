package com.example.apiteste.service;

import com.example.apiteste.DTO.NotaAlunoRequestDTO;
import com.example.apiteste.DTO.NotaAlunoResponseDTO;
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

    public NotaAlunoResponseDTO saveNotas(Long id, NotaAlunoRequestDTO notaAl) {
        Optional<AlunoEntity> check = alunoRepository.findById(id); //idependente ja traz se tem ou não se tiver vai pra proxima linha
        AlunoEntity aluno = check.orElseThrow(() -> new ExceptionValidation("Aluno não existe")); //se tiver pega o valor ou retona uma exception

        // ao salvar a nota tem que verificar se ja ultrpassou as 4
        notasValidation.validarNotas(aluno);

        NotaAlunoEntity notaAluno = new NotaAlunoEntity();

        notaAluno.setNota(notaAl.getNota());
        notaAluno.setAlunoEntity(aluno);

        notaAlunoRepository.save(notaAluno);

        return new NotaAlunoResponseDTO(notaAluno.getNota(), notaAluno.getAlunoEntity().getNome());
    }

    public List<NotaAlunoResponseDTO> retornaNotasPorId(Long id) {
        List<NotaAlunoEntity> byAlunoEntityId = notaAlunoRepository.findByAlunoEntityId(id);
        return byAlunoEntityId.stream().map(NotaAlunoResponseDTO::from).toList();
    }

    private void atualizarStatus(Long alunoId, Double media) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ExceptionValidation("Aluno não existe"));

        if (media < 6) {
            aluno.setAlunoStatus(AlunoStatus.REPROVADO);
        } else if (media >= 6 && media < 7) {
            aluno.setAlunoStatus(AlunoStatus.RECUPERACAO);
        } else if (media >= 7 && media <= 10) {
            aluno.setAlunoStatus(AlunoStatus.APROVADO);
        }
        alunoRepository.save(aluno);
    }

    public void retornaMediaAluno(Long id) {
        Optional<NotaAlunoEntity> byId = notaAlunoRepository.findById(id);
        NotaAlunoEntity notaAluno = byId.orElseThrow(() -> new ExceptionValidation("Nota não existe"));

        Long alunoId = notaAluno.getAlunoEntity().getId();

        List<NotaAlunoResponseDTO> notaAlunoResponseDTOS = retornaNotasPorId(alunoId);

        if (notaAlunoResponseDTOS.size() <= 4) {
            throw new ExceptionValidation("o aluno precisa ter exatamente 4 notas para calcular a média");
        }

        double media = 0.0;

        for (NotaAlunoResponseDTO notaAluno2 : notaAlunoResponseDTOS) {
            media = media + notaAluno2.getNota();
        }

        media = media / 4;

        atualizarStatus(alunoId, media);
    }
}

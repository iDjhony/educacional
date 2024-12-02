package br.grupointegrado.educacional.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.dto.BoletimResponseDTO;
import br.grupointegrado.educacional.dto.DisciplinaResponseDTO;
import br.grupointegrado.educacional.dto.NotaResponseDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Nota;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.repository.AlunoRepository;
//import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    //@Autowired
    //private MatriculaRepository matriculaRepository;
    
    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
    //    return this.repository.findAll();
    
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        //return this.repository.findById(id)
        //        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Aluno aluno = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setData_nascimento(dto.data_nascimento());
        aluno.setMatricula(dto.matricula());
        
        return this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setData_nascimento(dto.data_nascimento());
        aluno.setMatricula(dto.matricula());

        return this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{aluno_id/matricula}")
    public ResponseEntity<Aluno> addMatricula(@PathVariable Integer aluno_id, @RequestBody Integer turma_id){
        Aluno aluno = this.repository.findById(aluno_id)
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        Turma turma = this.turmaRepository.findById(turma_id)
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        boolean matriculaExiste = aluno.getMatriculas().stream()
        .anyMatch(m -> m.getTurma().getId().equals(turma_id));

        if(!matriculaExiste) {
            aluno.addMatricula(matricula);
        } else {
            throw new IllegalArgumentException("Aluno ja cadastrado nesta turma.");
        }

        Aluno alunoNota = this.repository.save(aluno);

        return ResponseEntity.ok(alunoNota);
    }

    @GetMapping("/{aluno_id}/boletim")
    public ResponseEntity<BoletimResponseDTO> getNotas(@PathVariable Integer aluno_id){
        Aluno aluno = this.repository.findById(aluno_id)
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        List<NotaResponseDTO> notas = new ArrayList<>();

        if(!aluno.getMatriculas().isEmpty()) {
            for (Matricula matricula : aluno.getMatriculas()) {
                for (Nota nota : matricula.getNotas()) {
                    notas.add(
                        new NotaResponseDTO(
                            nota.getNota(),
                            nota.getData_lancamento(),
                            new DisciplinaResponseDTO(
                                nota.getDisciplina().getNome(),
                                nota.getDisciplina().getCodigo()
                            )
                        )
                    );
                }
            }
        }

        return ResponseEntity.ok(new BoletimResponseDTO(notas));
    }

}

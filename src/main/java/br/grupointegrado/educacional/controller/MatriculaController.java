package br.grupointegrado.educacional.controller;

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

import br.grupointegrado.educacional.repository.AlunoRepository;
import br.grupointegrado.educacional.repository.MatriculaRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import br.grupointegrado.educacional.dto.MatriculaRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.model.Matricula;
import br.grupointegrado.educacional.model.Turma;


@RestController
@RequestMapping("api/matricula")
public class MatriculaController {
    

    @Autowired
    MatriculaRepository repository;

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada."));

        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody MatriculaRequestDTO dto){
        Matricula matricula = new Matricula();

        Aluno aluno = this.alunoRepository.findById(dto.aluno_id())
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        matricula.setAluno(aluno);

        Turma turma = this.turmaRepository.findById(dto.turma_id())
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        matricula.setTurma(turma);

        Matricula savedMatricula = this.repository.save(matricula);

        return ResponseEntity.ok(savedMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Integer id, @RequestBody MatriculaRequestDTO dto){
        Matricula matricula = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada."));

        Aluno aluno = this.alunoRepository.findById(dto.aluno_id())
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        matricula.setAluno(aluno);

        Turma turma = this.turmaRepository.findById(dto.turma_id())
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        matricula.setTurma(turma);

        Matricula savedMatricula = this.repository.save(matricula);

        return ResponseEntity.ok(savedMatricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada."));

        this.repository.delete(matricula);

        return ResponseEntity.noContent().build();
    }

}

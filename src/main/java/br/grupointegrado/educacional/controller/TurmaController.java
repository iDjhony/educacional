package br.grupointegrado.educacional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.TurmaRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import br.grupointegrado.educacional.model.Turma;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.dto.TurmaRequestDTO;

@RestController
@RequestMapping("api/turma")
public class TurmaController {
    
    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public Turma save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrad"));

        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
        
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        
        Curso curso = this.cursoRepository.findById(dto.curso_id())
        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        turma.setCurso(curso);

        Turma savedTurma = this.repository.save(turma);

        return ResponseEntity.ok(savedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));

        this.repository.delete(turma);

        return ResponseEntity.noContent().build();
    }
}

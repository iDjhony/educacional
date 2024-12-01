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

import br.grupointegrado.educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Professor;
import br.grupointegrado.educacional.model.Curso;
import br.grupointegrado.educacional.repository.CursoRepository;
import br.grupointegrado.educacional.repository.DisciplinaRepository;
import br.grupointegrado.educacional.repository.ProfessorRepository;

@RestController
@RequestMapping("api/disciplina")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public Disciplina save(@RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        disciplina.setCurso(curso);

        Professor professor = this.professorRepository.findById(dto.professor_id())
        .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        disciplina.setCurso(curso);

        Professor professor = this.professorRepository.findById(dto.professor_id())
        .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = this.repository.save(disciplina);

        return ResponseEntity.ok(savedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        this.repository.delete(disciplina);

        return ResponseEntity.noContent().build();
    }

}

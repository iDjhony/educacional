package br.grupointegrado.educacional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.grupointegrado.educacional.dto.AlunoRequestDTO;
import br.grupointegrado.educacional.model.Aluno;
import br.grupointegrado.educacional.repository.AlunoRepository;

@RestController
@RequestMapping("/api/aluno")
public class ClienteController {

    @Autowired
    private AlunoRepository repository;
    
    @GetMapping
    public List<Aluno>findAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
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
    public void delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(aluno);
    }

}

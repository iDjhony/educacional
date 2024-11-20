package br.grupointegrado.educacional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

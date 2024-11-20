package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    
}

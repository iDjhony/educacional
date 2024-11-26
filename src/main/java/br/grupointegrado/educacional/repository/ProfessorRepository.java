package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    
}

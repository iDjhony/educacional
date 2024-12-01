package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Integer>{
    
}

package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
    
}

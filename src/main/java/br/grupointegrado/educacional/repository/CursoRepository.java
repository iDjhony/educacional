package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    
}

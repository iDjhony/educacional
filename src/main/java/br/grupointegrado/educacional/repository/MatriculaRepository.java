package br.grupointegrado.educacional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
    
}

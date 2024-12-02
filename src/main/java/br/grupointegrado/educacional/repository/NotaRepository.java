package br.grupointegrado.educacional.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.grupointegrado.educacional.model.Disciplina;
import br.grupointegrado.educacional.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByDisciplina(Disciplina disciplina);
}

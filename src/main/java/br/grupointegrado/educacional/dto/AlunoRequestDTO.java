package br.grupointegrado.educacional.dto;

import java.time.LocalDate;

public record AlunoRequestDTO(String nome, String email, LocalDate data_nascimento, String matricula) {
    
}

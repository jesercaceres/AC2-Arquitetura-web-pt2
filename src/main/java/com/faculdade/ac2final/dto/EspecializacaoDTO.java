package com.faculdade.ac2final.dto;

import lombok.Data;

@Data
public class EspecializacaoDTO {
    private Long id;
    private Long cursoId;
    private Long professorId;
    private String nomeCurso;
    private String nomeProfessor;
}
package com.faculdade.ac2final.dto;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String descricao;
    private double cargaHoraria;
    private String objetivos;
    private String ementa;
}

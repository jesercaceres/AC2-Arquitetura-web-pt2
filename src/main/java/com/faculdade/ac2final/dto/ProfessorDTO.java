package com.faculdade.ac2final.dto;

import lombok.Data;

@Data
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;
}
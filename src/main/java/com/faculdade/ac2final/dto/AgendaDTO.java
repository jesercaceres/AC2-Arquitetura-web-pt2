package com.faculdade.ac2final.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AgendaDTO {
    private Long id;
    private Long cursoId;
    private String cursoNome;
    private String descricaoCurso;
    private Long professorId;
    private String professorNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private String cidade;
    private String estado;
    private String cep;

    @Override
    public String toString() {
        return "AgendaDTO{" +
               
                ", professorNome='" + professorNome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", horarioInicio=" + horarioInicio +
                ", horarioFim=" + horarioFim +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

}
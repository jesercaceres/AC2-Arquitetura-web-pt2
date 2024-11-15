package com.faculdade.ac2final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double cargaHoraria;
    private String objetivos;
    private String ementa;
 
    @OneToMany(mappedBy = "curso")
    private List<Especializacao> especializacoes;

    @OneToMany(mappedBy = "curso")
    private List<Agenda> agendas;

}
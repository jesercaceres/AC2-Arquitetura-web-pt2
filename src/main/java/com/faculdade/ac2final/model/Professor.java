package com.faculdade.ac2final.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;

    @OneToMany(mappedBy = "professor")
    private List<Especializacao> especializacoes;

    @OneToMany(mappedBy = "professor")
    private List<Agenda> agendas;

    @OneToMany(mappedBy = "professor")
    private List<Resumo> resumos;
}

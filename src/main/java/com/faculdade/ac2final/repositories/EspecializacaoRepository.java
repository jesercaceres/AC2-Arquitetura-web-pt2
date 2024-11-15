package com.faculdade.ac2final.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.ac2final.model.Especializacao;


public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {
    List<Especializacao> findByCursoId(Long cursoId);
}

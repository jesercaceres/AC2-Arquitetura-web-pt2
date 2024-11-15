package com.faculdade.ac2final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.ac2final.model.Resumo;

public interface ResumoRepository extends JpaRepository<Resumo, Long> {
    
}
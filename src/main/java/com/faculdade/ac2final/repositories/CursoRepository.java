package com.faculdade.ac2final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.ac2final.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}

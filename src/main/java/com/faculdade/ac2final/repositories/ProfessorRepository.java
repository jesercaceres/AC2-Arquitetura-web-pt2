package com.faculdade.ac2final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.ac2final.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
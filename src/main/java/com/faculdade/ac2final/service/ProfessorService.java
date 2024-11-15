package com.faculdade.ac2final.service;

import java.util.List;

import com.faculdade.ac2final.dto.ProfessorDTO;

public interface ProfessorService {
    List<ProfessorDTO> listarProfessores();
    ProfessorDTO updateProfessor(Long id, ProfessorDTO professorDTO);
    ProfessorDTO cadastrarProfessor(ProfessorDTO ProfessorDTO);
    void deleteProfessor(Long id);
}

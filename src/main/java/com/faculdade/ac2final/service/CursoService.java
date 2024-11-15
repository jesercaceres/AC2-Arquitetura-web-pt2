package com.faculdade.ac2final.service;

import java.util.List;

import com.faculdade.ac2final.dto.CursoDTO;

public interface CursoService {
    List<CursoDTO> listarCursos();
    CursoDTO createCurso(CursoDTO cursoDTO);
    CursoDTO updateCurso(Long id, CursoDTO cursoDTO);
    void deleteCurso(Long id);
}

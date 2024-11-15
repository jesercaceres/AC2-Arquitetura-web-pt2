package com.faculdade.ac2final.service;

import java.util.List;

import com.faculdade.ac2final.dto.ResumoDTO;

public interface ResumoService {
    List<ResumoDTO> listarResumos();
    ResumoDTO createResumo(ResumoDTO resumoDTO);
    ResumoDTO updateResumo(Long id, ResumoDTO resumoDTO);
    void deleteResumo(Long id);
}

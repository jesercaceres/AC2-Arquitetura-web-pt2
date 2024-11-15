package com.faculdade.ac2final.service;

import java.util.List;

import com.faculdade.ac2final.dto.EspecializacaoDTO;

public interface EspecializacaoService {
    List<EspecializacaoDTO> listarEspecializacoes();
    EspecializacaoDTO createEspecializacao(EspecializacaoDTO especializacaoDTO);
    EspecializacaoDTO updateEspecializacao(Long id, EspecializacaoDTO especializacaoDTO);
    void deleteEspecializacao(Long id);
}

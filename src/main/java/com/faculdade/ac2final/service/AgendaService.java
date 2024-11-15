package com.faculdade.ac2final.service;

import java.util.List;

import com.faculdade.ac2final.dto.AgendaDTO;

import java.time.LocalDate;

import com.faculdade.ac2final.dto.ProfessorDTO;

public interface AgendaService {
    List<AgendaDTO> listarAgendas();
    AgendaDTO createAgenda(AgendaDTO agendaDTO);
    AgendaDTO updateAgenda(Long id, AgendaDTO agendaDTO);
    void deleteAgenda(Long id);

    List<ProfessorDTO> listarProfessoresDisponiveis(Long cursoId, LocalDate dataInicio, LocalDate dataFim);
}
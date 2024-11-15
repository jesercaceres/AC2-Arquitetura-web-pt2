package com.faculdade.ac2final.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.ac2final.model.Agenda;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    boolean existsByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
            Long professorId, LocalDate dataFim, LocalDate dataInicio);

    List<Agenda> findByProfessorId(Long professorId);
}
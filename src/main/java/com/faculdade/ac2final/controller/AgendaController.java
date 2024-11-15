package com.faculdade.ac2final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculdade.ac2final.dto.AgendaDTO;
import com.faculdade.ac2final.dto.ProfessorDTO;
import com.faculdade.ac2final.service.AgendaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<AgendaDTO> listarAgendas() {
        return agendaService.listarAgendas();
    }

    @PostMapping
    public ResponseEntity<AgendaDTO> criarAgenda(@RequestBody AgendaDTO agendaDTO) {
        AgendaDTO novaAgenda = agendaService.createAgenda(agendaDTO);
        return ResponseEntity.ok(novaAgenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> atualizarAgenda(@PathVariable Long id, @RequestBody AgendaDTO agendaDTO) {
        AgendaDTO atualizadaAgenda = agendaService.updateAgenda(id, agendaDTO);
        return ResponseEntity.ok(atualizadaAgenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgenda(@PathVariable Long id) {
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/professores-disponiveis")
    public List<ProfessorDTO> listarProfessoresDisponiveis(
            @RequestParam Long cursoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return agendaService.listarProfessoresDisponiveis(cursoId, dataInicio, dataFim);
    }
}

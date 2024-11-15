package com.faculdade.ac2final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculdade.ac2final.dto.ProfessorDTO;
import com.faculdade.ac2final.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<ProfessorDTO> listarProfessores() {
        return professorService.listarProfessores();
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO novoProfessor = professorService.cadastrarProfessor(professorDTO);
        return ResponseEntity.ok(novoProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO atualizadoProfessor = professorService.updateProfessor(id, professorDTO);
        return ResponseEntity.ok(atualizadoProfessor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }
}

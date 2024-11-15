package com.faculdade.ac2final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculdade.ac2final.dto.EspecializacaoDTO;
import com.faculdade.ac2final.service.EspecializacaoService;

import java.util.List;

@RestController
@RequestMapping("/especializacoes")
public class EspecializacaoController {

    @Autowired
    private EspecializacaoService especializacaoService;

    @GetMapping
    public List<EspecializacaoDTO> listarEspecializacoes() {
        return especializacaoService.listarEspecializacoes();
    }

    @PostMapping
    public ResponseEntity<EspecializacaoDTO> criarEspecializacao(@RequestBody EspecializacaoDTO especializacaoDTO) {
        EspecializacaoDTO novaEspecializacao = especializacaoService.createEspecializacao(especializacaoDTO);
        return ResponseEntity.ok(novaEspecializacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecializacaoDTO> atualizarEspecializacao(@PathVariable Long id, @RequestBody EspecializacaoDTO especializacaoDTO) {
        EspecializacaoDTO atualizadaEspecializacao = especializacaoService.updateEspecializacao(id, especializacaoDTO);
        return ResponseEntity.ok(atualizadaEspecializacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEspecializacao(@PathVariable Long id) {
        especializacaoService.deleteEspecializacao(id);
        return ResponseEntity.noContent().build();
    }
}

package com.faculdade.ac2final.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.ac2final.dto.ProfessorDTO;
import com.faculdade.ac2final.model.Professor;
import com.faculdade.ac2final.repositories.EspecializacaoRepository;
import com.faculdade.ac2final.repositories.ProfessorRepository;
import com.faculdade.ac2final.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfessorDTO> listarProfessores() {
        return professorRepository.findAll().stream()
                .map(professor -> {
                    ProfessorDTO dto = new ProfessorDTO();
                    dto.setId(professor.getId());
                    dto.setNome(professor.getNome());
                    dto.setCpf(professor.getCpf());
                    dto.setRg(professor.getRg());
                    dto.setEndereco(professor.getEndereco());
                    dto.setCelular(professor.getCelular());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProfessorDTO cadastrarProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setRg(professorDTO.getRg());
        professor.setEndereco(professorDTO.getEndereco());
        professor.setCelular(professorDTO.getCelular());

        Professor savedProfessor = professorRepository.save(professor);

        ProfessorDTO savedDTO = new ProfessorDTO();
        savedDTO.setId(savedProfessor.getId());
        savedDTO.setNome(savedProfessor.getNome());
        savedDTO.setCpf(savedProfessor.getCpf());
        savedDTO.setRg(savedProfessor.getRg());
        savedDTO.setEndereco(savedProfessor.getEndereco());
        savedDTO.setCelular(savedProfessor.getCelular());

        return savedDTO;
    }

    @Override
    @Transactional
    public ProfessorDTO updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setRg(professorDTO.getRg());
        professor.setEndereco(professorDTO.getEndereco());
        professor.setCelular(professorDTO.getCelular());

        Professor updatedProfessor = professorRepository.save(professor);

        ProfessorDTO updatedDTO = new ProfessorDTO();
        updatedDTO.setId(updatedProfessor.getId());
        updatedDTO.setNome(updatedProfessor.getNome());
        updatedDTO.setCpf(updatedProfessor.getCpf());
        updatedDTO.setRg(updatedProfessor.getRg());
        updatedDTO.setEndereco(updatedProfessor.getEndereco());
        updatedDTO.setCelular(updatedProfessor.getCelular());

        return updatedDTO;
    }

    @Override
    @Transactional
    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}

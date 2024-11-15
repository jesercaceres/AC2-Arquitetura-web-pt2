package com.faculdade.ac2final.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.ac2final.dto.EspecializacaoDTO;
import com.faculdade.ac2final.model.Curso;
import com.faculdade.ac2final.model.Especializacao;
import com.faculdade.ac2final.model.Professor;
import com.faculdade.ac2final.repositories.CursoRepository;
import com.faculdade.ac2final.repositories.EspecializacaoRepository;
import com.faculdade.ac2final.repositories.ProfessorRepository;
import com.faculdade.ac2final.service.EspecializacaoService;

@Service
public class EspecializacaoServiceImpl implements EspecializacaoService {

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EspecializacaoDTO> listarEspecializacoes() {
        return especializacaoRepository.findAll().stream()
                .map(especializacao -> {
                    EspecializacaoDTO dto = new EspecializacaoDTO();
                    dto.setId(especializacao.getId());
                    dto.setCursoId(especializacao.getCurso().getId());
                    dto.setProfessorId(especializacao.getProfessor().getId());
                    dto.setNomeCurso(especializacao.getCurso().getDescricao());
                    dto.setNomeProfessor(especializacao.getProfessor().getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EspecializacaoDTO createEspecializacao(EspecializacaoDTO especializacaoDTO) {
        Curso curso = cursoRepository.findById(especializacaoDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso not found"));
        Professor professor = professorRepository.findById(especializacaoDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Especializacao especializacao = new Especializacao();
        especializacao.setCurso(curso);
        especializacao.setProfessor(professor);

        Especializacao savedEspecializacao = especializacaoRepository.save(especializacao);

        EspecializacaoDTO savedDTO = new EspecializacaoDTO();
        savedDTO.setId(savedEspecializacao.getId());
        savedDTO.setCursoId(savedEspecializacao.getCurso().getId());
        savedDTO.setProfessorId(savedEspecializacao.getProfessor().getId());

        return savedDTO;
    }

    @Override
    @Transactional
    public EspecializacaoDTO updateEspecializacao(Long id, EspecializacaoDTO especializacaoDTO) {
        Especializacao especializacao = especializacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especializacao not found"));

        Curso curso = cursoRepository.findById(especializacaoDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso not found"));
        Professor professor = professorRepository.findById(especializacaoDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        especializacao.setCurso(curso);
        especializacao.setProfessor(professor);

        Especializacao updatedEspecializacao = especializacaoRepository.save(especializacao);

        EspecializacaoDTO updatedDTO = new EspecializacaoDTO();
        updatedDTO.setId(updatedEspecializacao.getId());
        updatedDTO.setCursoId(updatedEspecializacao.getCurso().getId());
        updatedDTO.setProfessorId(updatedEspecializacao.getProfessor().getId());

        return updatedDTO;
    }

    @Override
    @Transactional
    public void deleteEspecializacao(Long id) {
        if (especializacaoRepository.existsById(id)) {
            especializacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Especializacao not found");
        }
    }

}

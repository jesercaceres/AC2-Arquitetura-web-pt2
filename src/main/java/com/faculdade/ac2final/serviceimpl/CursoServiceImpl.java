package com.faculdade.ac2final.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.ac2final.dto.CursoDTO;
import com.faculdade.ac2final.model.Curso;
import com.faculdade.ac2final.repositories.CursoRepository;
import com.faculdade.ac2final.service.CursoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(curso -> {
                    CursoDTO dto = new CursoDTO();
                    dto.setId(curso.getId());
                    dto.setDescricao(curso.getDescricao());
                    dto.setCargaHoraria(curso.getCargaHoraria());
                    dto.setObjetivos(curso.getObjetivos());
                    dto.setEmenta(curso.getEmenta());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CursoDTO createCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setDescricao(cursoDTO.getDescricao());
        curso.setCargaHoraria(cursoDTO.getCargaHoraria());
        curso.setObjetivos(cursoDTO.getObjetivos());
        curso.setEmenta(cursoDTO.getEmenta());

        Curso savedCurso = cursoRepository.save(curso);

        CursoDTO savedDTO = new CursoDTO();
        savedDTO.setId(savedCurso.getId());
        savedDTO.setDescricao(savedCurso.getDescricao());
        savedDTO.setCargaHoraria(savedCurso.getCargaHoraria());
        savedDTO.setObjetivos(savedCurso.getObjetivos());
        savedDTO.setEmenta(savedCurso.getEmenta());

        return savedDTO;
    }

    @Override
    @Transactional
    public CursoDTO updateCurso(Long id, CursoDTO cursoDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso not found"));

        curso.setDescricao(cursoDTO.getDescricao());
        curso.setCargaHoraria(cursoDTO.getCargaHoraria());
        curso.setObjetivos(cursoDTO.getObjetivos());
        curso.setEmenta(cursoDTO.getEmenta());

        Curso updatedCurso = cursoRepository.save(curso);

        CursoDTO updatedDTO = new CursoDTO();
        updatedDTO.setId(updatedCurso.getId());
        updatedDTO.setDescricao(updatedCurso.getDescricao());
        updatedDTO.setCargaHoraria(updatedCurso.getCargaHoraria());
        updatedDTO.setObjetivos(updatedCurso.getObjetivos());
        updatedDTO.setEmenta(updatedCurso.getEmenta());

        return updatedDTO;
    }

    @Override
    @Transactional
    public void deleteCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Curso not found");
        }
    }

}

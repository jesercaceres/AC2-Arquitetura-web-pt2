package com.faculdade.ac2final.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.ac2final.dto.AgendaDTO;
import com.faculdade.ac2final.dto.ProfessorDTO;
import com.faculdade.ac2final.model.Agenda;
import com.faculdade.ac2final.model.Curso;
import com.faculdade.ac2final.model.Especializacao;
import com.faculdade.ac2final.model.Professor;
import com.faculdade.ac2final.repositories.AgendaRepository;
import com.faculdade.ac2final.repositories.CursoRepository;
import com.faculdade.ac2final.repositories.EspecializacaoRepository;
import com.faculdade.ac2final.repositories.ProfessorRepository;
import com.faculdade.ac2final.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AgendaDTO> listarAgendas() {
        return agendaRepository.findAll().stream()
                .map(agenda -> {
                    AgendaDTO dto = new AgendaDTO();
                    dto.setId(agenda.getId());
                    dto.setCursoId(agenda.getCurso().getId());
                    dto.setCursoNome(agenda.getCurso().getDescricao());
                    dto.setDescricaoCurso(agenda.getCurso().getEmenta());
                    dto.setProfessorId(agenda.getProfessor().getId());
                    dto.setProfessorNome(agenda.getProfessor().getNome());
                    dto.setDataInicio(agenda.getDataInicio());
                    dto.setDataFim(agenda.getDataFim());
                    dto.setHorarioInicio(agenda.getHorarioInicio());
                    dto.setHorarioFim(agenda.getHorarioFim());
                    dto.setCidade(agenda.getCidade());
                    dto.setEstado(agenda.getEstado());
                    dto.setCep(agenda.getCep());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AgendaDTO createAgenda(AgendaDTO agendaDTO) {
        Curso curso = cursoRepository.findById(agendaDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso not found"));

        List<Especializacao> especializacoes = especializacaoRepository.findByCursoId(agendaDTO.getCursoId());
        List<Long> professoresIds = especializacoes.stream()
                .map(especializacao -> especializacao.getProfessor().getId())
                .collect(Collectors.toList());

        if (!professoresIds.contains(agendaDTO.getProfessorId())) {
            throw new RuntimeException("Professor não possui especialização necessária para este curso");
        }

        Professor professor = professorRepository.findById(agendaDTO.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        if (agendaRepository.existsByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
                agendaDTO.getProfessorId(), agendaDTO.getDataFim(), agendaDTO.getDataInicio())) {
            throw new RuntimeException("Professor já está ocupado nesse horário");
        }

        Agenda agenda = new Agenda();
        agenda.setCurso(curso);
        agenda.setProfessor(professor);
        agenda.setDataInicio(agendaDTO.getDataInicio());
        agenda.setDataFim(agendaDTO.getDataFim());
        agenda.setHorarioInicio(agendaDTO.getHorarioInicio());
        agenda.setHorarioFim(agendaDTO.getHorarioFim());
        agenda.setCidade(agendaDTO.getCidade());
        agenda.setEstado(agendaDTO.getEstado());
        agenda.setCep(agendaDTO.getCep());

        Agenda savedAgenda = agendaRepository.save(agenda);

        AgendaDTO savedDTO = new AgendaDTO();
        savedDTO.setId(savedAgenda.getId());
        savedDTO.setCursoId(savedAgenda.getCurso().getId());
        savedDTO.setCursoNome(savedAgenda.getCurso().getDescricao());
        savedDTO.setDescricaoCurso(savedAgenda.getCurso().getEmenta());
        savedDTO.setProfessorId(savedAgenda.getProfessor().getId());
        savedDTO.setProfessorNome(savedAgenda.getProfessor().getNome());
        savedDTO.setDataInicio(savedAgenda.getDataInicio());
        savedDTO.setDataFim(savedAgenda.getDataFim());
        savedDTO.setHorarioInicio(savedAgenda.getHorarioInicio());
        savedDTO.setHorarioFim(savedAgenda.getHorarioFim());
        savedDTO.setCidade(savedAgenda.getCidade());
        savedDTO.setEstado(savedAgenda.getEstado());
        savedDTO.setCep(savedAgenda.getCep());

        return savedDTO;
    }

    @Override
    public AgendaDTO updateAgenda(Long id, AgendaDTO agendaDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAgenda'");
    }

    @Override
    public void deleteAgenda(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAgenda'");
    }
    @Override
    public List<ProfessorDTO> listarProfessoresDisponiveis(Long cursoId, LocalDate dataInicio, LocalDate dataFim) {
        List<Especializacao> especializacoes = especializacaoRepository.findByCursoId(cursoId);

        return especializacoes.stream()
                .map(Especializacao::getProfessor)
                .filter(professor -> !agendaRepository
                        .existsByProfessorIdAndDataInicioLessThanEqualAndDataFimGreaterThanEqual(
                                professor.getId(), dataFim, dataInicio))
                .map(professor -> {
                    ProfessorDTO dto = new ProfessorDTO();
                    dto.setId(professor.getId());
                    dto.setNome(professor.getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}

package com.faculdade.ac2final;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.faculdade.ac2final.dto.AgendaDTO;
import com.faculdade.ac2final.dto.CursoDTO;
import com.faculdade.ac2final.dto.EspecializacaoDTO;
import com.faculdade.ac2final.dto.ProfessorDTO;
import com.faculdade.ac2final.service.AgendaService;
import com.faculdade.ac2final.service.CursoService;
import com.faculdade.ac2final.service.EspecializacaoService;
import com.faculdade.ac2final.service.ProfessorService;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Ac2finalApplication {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CursoService cursoService;

	@Autowired
	private EspecializacaoService especializacaoService;

	@Autowired
	private AgendaService agendaService;

	public static void main(String[] args) {
		SpringApplication.run(Ac2finalApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			ProfessorDTO professorDTO = new ProfessorDTO();
			professorDTO.setNome("João Silva");
			professorDTO.setCpf("123.456.789-00");
			professorDTO.setRg("MG-12.345.678");
			professorDTO.setEndereco("Rua Exemplo, 123");
			professorDTO.setCelular("(31) 91234-5678");

			System.out.println("Prof. cadastrado: " + professorService.cadastrarProfessor(professorDTO));

			ProfessorDTO professor2 = new ProfessorDTO();
			professor2.setNome("Carlos");
			professor2.setCpf("463.542.988.21");
			professor2.setRg("57.336.973-8");
			professor2.setEndereco("Rua Silvio figueiredo, 19");
			professor2.setCelular("15086161");

			System.out.println("Prof. Cadastrado: " + professorService.cadastrarProfessor(professor2)); 

			

			System.out.println("Listar professores:" + professorService.listarProfessores());

			CursoDTO cursoDTO = new CursoDTO();
			cursoDTO.setCargaHoraria(10.0);
			cursoDTO.setDescricao("Programação Orientada a Objetos");
			cursoDTO.setEmenta("Principais conceitos sobre POO");
			cursoDTO.setObjetivos("Aprimorar conhecimentos");

			System.out.println("Curso:" + cursoService.createCurso(cursoDTO));
			
			CursoDTO cursoDTO2 = new CursoDTO();
			cursoDTO2.setCargaHoraria(30.0);
			cursoDTO2.setDescricao("Inglês");
			cursoDTO2.setEmenta("Estudando a lingua inglesa");
			cursoDTO2.setObjetivos("Aprimorar conversação");

			System.out.println("Curso:" + cursoService.createCurso(cursoDTO2));
			
			System.out.println("listar cursos" + cursoService.listarCursos());

			EspecializacaoDTO especializacaoDTO = new EspecializacaoDTO();
			especializacaoDTO.setCursoId(1L);
			especializacaoDTO.setProfessorId(1L);
			
			System.out.println("Especializacao criada:" + especializacaoService.createEspecializacao(especializacaoDTO));

			EspecializacaoDTO especializacaoDTO2 = new EspecializacaoDTO();
			especializacaoDTO2.setCursoId(2L);
			especializacaoDTO2.setProfessorId(2L);
			System.out.println("Especializacao criada:" + especializacaoService.createEspecializacao(especializacaoDTO2));
			

			System.out.println("Listar especializações:" + especializacaoService.listarEspecializacoes()); 
			AgendaDTO agendaDTO = new AgendaDTO();
			agendaDTO.setCep("18116488");
			agendaDTO.setCidade("Sorocaba");
			agendaDTO.setCursoId(1L);
			agendaDTO.setProfessorId(1L);
			agendaDTO.setDataInicio(LocalDate.parse("2024-11-28"));
			agendaDTO.setDataFim(LocalDate.parse("2024-12-05")); // Corrigido
			agendaDTO.setEstado("SP");
			agendaDTO.setHorarioInicio(LocalTime.parse("14:30"));
			agendaDTO.setHorarioFim(LocalTime.parse("17:30"));
			
			System.out.println("Agenda cadastrada: " + agendaService.createAgenda(agendaDTO));
		};
	}
}
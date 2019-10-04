package com.lunch.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.lunch.model.Colaborador;
import com.lunch.model.Restaurante;
import com.lunch.repository.ColaboradorRepository;
import com.lunch.repository.RestauranteRepository;
import com.lunch.service.VotacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VotacaoTests {
	
	private final Long ID_COLABORADOR_MICHAEL_JACKSON = 1L;
	private final Long ID_RESTAURANTE_MC_DONALDS = 1L;
	
	private Colaborador colaborador;
	private Restaurante restaurante;
	
	@Autowired
	private VotacaoService votacaoService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	/*
	 * Teste para validação da estória 01:
	 * Um profissional só pode votar em um restaurante por dia.
	 */
	@Test 
	@Sql(scripts = "/db/tests/truncarVotacao.sql")
	public void validarVotacaoColaboradorDia() throws Exception {
		
	    exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage(VotacaoService.COLABORADOR_JA_VOTOU_HOJE);
		
		colaborador = colaboradorRepository.findById(ID_COLABORADOR_MICHAEL_JACKSON).get();
		restaurante = restauranteRepository.findById(ID_RESTAURANTE_MC_DONALDS).get();
		
		votacaoService.votarNoDiaDeHoje(colaborador, restaurante);
		
		votacaoService.findByCurrentDate();
		
		votacaoService.votarNoDiaDeHoje(colaborador, restaurante);
	}
	
	/*
	 * Teste para validação da estória 02:
	 * O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana.
	 */
	@Test 
	@Sql(scripts = "/db/tests/truncarVotacao.sql")
	public void validarEscolhaRestauranteSemana() throws Exception {
		
	    exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage(VotacaoService.RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA);
	    
		colaborador = colaboradorRepository.findById(ID_COLABORADOR_MICHAEL_JACKSON).get();
		restaurante = restauranteRepository.findById(ID_RESTAURANTE_MC_DONALDS).get();	    
	    
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(2019,0, 1);
	    Date dataOntem = calendar.getTime();
	    
	    votacaoService.votar(colaborador, restaurante, dataOntem);
	    
	    calendar.set(2019,0, 2);
	    Date data = calendar.getTime();
	    votacaoService.votar(colaborador, restaurante, data);
	}

	@Test
	@Sql(scripts = "/db/tests/truncarVotacao.sql")
	public void votar() throws Exception {
		
		colaborador = colaboradorRepository.findById(ID_COLABORADOR_MICHAEL_JACKSON).get();
		restaurante = restauranteRepository.findById(ID_RESTAURANTE_MC_DONALDS).get();
		
		votacaoService.votarNoDiaDeHoje(colaborador, restaurante);
	}

}


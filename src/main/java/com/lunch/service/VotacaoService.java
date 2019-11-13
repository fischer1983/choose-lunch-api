package com.lunch.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunch.dto.ParcialVotacaoDto;
import com.lunch.model.Colaborador;
import com.lunch.model.Restaurante;
import com.lunch.model.Votacao;
import com.lunch.model.VotacaoId;
import com.lunch.repository.IVotacaoRepository;

@Service
public class VotacaoService {
	
	public static final String COLABORADOR_JA_VOTOU_HOJE = "Colaborador_Ja_Votou_Hoje";
	public static final String RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA = "Restaurante_Ja_Escolhido_Na_Semana";
	
	@Autowired
	private IVotacaoRepository repository;
	
	public void votarNoDiaDeHoje(Colaborador colaborador, Restaurante restaurante) throws Exception {
		votar(colaborador, restaurante, new Date());
	}
	
	public void votar(Colaborador colaborador, Restaurante restaurante, Date data) throws Exception {
		
		validarVotacao(colaborador, restaurante, data);
		
		Votacao votacao = criarVoto(colaborador, restaurante, data);
		
		repository.save(votacao);
	}
	
	public void validarVotacao(Colaborador colaborador, Restaurante restaurante, Date data) throws Exception {
		boolean colaboradorJaVotouHoje = colaboradorJaVotouNaData(colaborador, data);
		boolean restauranteJaEscolhidoNaSemana = restauranteJaEscolhidoNaSemanaPorData(restaurante.getId(), data);  
		
		if (colaboradorJaVotouHoje) {
			throw new Exception(COLABORADOR_JA_VOTOU_HOJE);
		}
		
		if (restauranteJaEscolhidoNaSemana) {
			throw new Exception(RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA);
		}
	}
	
	private Votacao criarVoto(Colaborador colaborador, Restaurante restaurante, Date data) {
		Votacao votacao = new Votacao();
		VotacaoId votacaoId = new VotacaoId();
		
		votacaoId.setColaborador(colaborador);
		votacaoId.setRestaurante(restaurante);
		votacaoId.setData(data);
		
		votacao.setId(votacaoId);
		return votacao;
	}

	public boolean restauranteJaEscolhidoNaSemanaPorData(Long idRestaurante, Date data) {
		List<Votacao> votacoes = repository.restauranteJaEscolhidoNaSemanaPorData(idRestaurante, data);
		return !votacoes.isEmpty();
	}

	private boolean colaboradorJaVotouNaData(Colaborador colaborador, Date data) {
		return repository.findByColaboradorAndData(colaborador, data) != null;
	}

	public List<Votacao> findByCurrentDate() {
		return repository.findByData(new Date());
	}
	
	public List<ParcialVotacaoDto> getParcialResultToday() {
		return repository.getParcialResultToday();
	}

}

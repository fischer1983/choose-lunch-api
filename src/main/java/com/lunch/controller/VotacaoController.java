package com.lunch.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunch.dto.ParcialVotacaoDto;
import com.lunch.dto.VotoDto;
import com.lunch.model.Votacao;
import com.lunch.service.VotacaoService;

@CrossOrigin
@RestController
@RequestMapping("/votacao")
public class VotacaoController {
	
	@Autowired
	private VotacaoService votacaoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void votar(@Valid @RequestBody VotoDto votoDto) throws Exception {
		votacaoService.votarNoDiaDeHoje(votoDto.getColaborador(), votoDto.getRestaurante());
	}
	
	@GetMapping("/corrente")
	public List<Votacao> findByCurrentDate() {
		return votacaoService.findByCurrentDate();		
	}
	
	@GetMapping("/restauranteJaEleitoNaSemana/{idRestaurante}")
	public Boolean restauranteJaEleitoNaSemana(@PathVariable("idRestaurante") Long idRestaurante) {
		return votacaoService.restauranteJaEscolhidoNaSemanaPorData(idRestaurante, new Date());		
	}
	
	@GetMapping("/parcialCorrente")
	public List<ParcialVotacaoDto> getParcialResultToday() {
		return votacaoService.getParcialResultToday();		
	}	

}

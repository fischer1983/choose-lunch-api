package com.lunch.dto;

public class ParcialVotacaoDto {
	
	private String nomeRestaurante;
	
	private Long qtdeVotos;
	

	public ParcialVotacaoDto(String nomeRestaurante, Long qtdeVotos) {
		super();
		this.nomeRestaurante = nomeRestaurante;
		this.qtdeVotos = qtdeVotos;
	}
	

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public Long getQtdeVotos() {
		return qtdeVotos;
	}

	public void setQtdeVotos(Long qtdeVotos) {
		this.qtdeVotos = qtdeVotos;
	}

}

package com.lunch.dto;

import com.lunch.model.Colaborador;
import com.lunch.model.Restaurante;

public class VotoDto {
	
	private Colaborador colaborador;
	
	private Restaurante restaurante;

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
}

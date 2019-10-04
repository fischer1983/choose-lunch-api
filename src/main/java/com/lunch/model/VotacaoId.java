package com.lunch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class VotacaoId  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@JoinColumn(name = "id_restaurante")
	@OneToOne(optional = false)
	private Restaurante restaurante;
	
	@NotEmpty
	@JoinColumn(name = "id_colaborador")
	@OneToOne(optional = false)
	private Colaborador colaborador;
	
	@NotEmpty
	private Date data;
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}	

}

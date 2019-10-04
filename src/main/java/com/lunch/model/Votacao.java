package com.lunch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Votacao {
	
	@Id
	private VotacaoId id;

	public VotacaoId getId() {
		return id;
	}

	public void setId(VotacaoId id) {
		this.id = id;
	}
	
}

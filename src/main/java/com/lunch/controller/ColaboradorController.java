package com.lunch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunch.model.Colaborador;
import com.lunch.repository.ColaboradorRepository;

@CrossOrigin
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@GetMapping
	public List<Colaborador> findAll() {
		return colaboradorRepository.findAll();
	}

}

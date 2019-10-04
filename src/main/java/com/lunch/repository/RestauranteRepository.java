package com.lunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lunch.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}

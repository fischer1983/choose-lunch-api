package com.lunch.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lunch.dto.ParcialVotacaoDto;
import com.lunch.model.Colaborador;
import com.lunch.model.Votacao;
import com.lunch.model.VotacaoId;

public interface IVotacaoRepository extends JpaRepository<Votacao, VotacaoId> {
	
	final String SQL_FINDBY_COLABORADOR_VOTOU_NA_DATA =
			  " select "
			+ "   v.id.colaborador "
			+ " from "
			+ "   Votacao v "
			+ "   join v.id.colaborador c "
			+ " where "
			+ "   v.id.colaborador = :colaborador "
			+ "   and v.id.data = trunc(:data) ";
	
	final String SQL_FINDBY_RESTAURANTE_ESCOLHIDO_SEMANA_POR_DATA = 
			  " select distinct " 
	        + "   id_colaborador"
	        + "   ,id_restaurante"
	        + "   ,data "
			+ " from "
			+ "  ( " 
			+ "   select " 
			+ "     row_number() over(partition by data order by qtde_votos desc) as rn "
			+ "     ,id_colaborador "
			+ "     ,id_restaurante " 
			+ "     ,qtde_votos " 
			+ "     ,data " 
			+ "   from " 
			+ "    ( " 
			+ "     select "
			+ "       id_colaborador " 
			+ "       ,id_restaurante " 
			+ "       ,count(1) as qtde_votos "
			+ "       ,data " 
			+ "     from " 
			+ "       votacao v " 
			+ "     where "
			+ "      iso_week(data) = iso_week(:data) " 
			+ "     group by " 
			+ "       id_colaborador "
			+ "       ,id_restaurante "
			+ "       ,data " 
			+ "    ) "
			+ "  ) "
			+ " where "
			+ "   rn = 1 " 
			+ "   and id_restaurante = :idRestaurante "
			+ "   and data < trunc(:data)";
	
	final String SQL_FINDBY_DATA = 
			  " select "
			+ "   v "
			+ " from "
			+ "   Votacao v "
			+ " where "
			+ "   v.id.data = trunc(:data) ";
	
	final String SQL_GET_PARTIAL_RESULT_TODAY =
			  "   select "
			+ "     new com.lunch.dto.ParcialVotacaoDto(v.id.restaurante.nome ,count(1)) "
			+ "   from "
			+ "     Votacao v "
			+ "   where "
			+ "     v.id.data = current_date "
			+ "   group by "
			+ "     v.id.restaurante.nome ";
	
	@Query(SQL_FINDBY_COLABORADOR_VOTOU_NA_DATA)
	Colaborador findByColaboradorAndData(@Param("colaborador") Colaborador colaborador, @Param("data") Date data);

	@Query(name = "restauranteJaEscolhidoNaSemanaPorData", value =  SQL_FINDBY_RESTAURANTE_ESCOLHIDO_SEMANA_POR_DATA, nativeQuery = true)
	List<Votacao> restauranteJaEscolhidoNaSemanaPorData(@Param("idRestaurante") Long idRestaurante, @Param("data") Date data);

	@Query(SQL_FINDBY_DATA)
	List<Votacao> findByData(@Param("data") Date data);
	
	@Query(SQL_GET_PARTIAL_RESULT_TODAY)
	List<ParcialVotacaoDto> getParcialResultToday();
	
}

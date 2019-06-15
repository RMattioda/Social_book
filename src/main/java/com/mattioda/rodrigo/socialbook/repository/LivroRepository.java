package com.mattioda.rodrigo.socialbook.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mattioda.rodrigo.socialbook.domain.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

	//Busca o nome do livro contendo a string informada pelo usuário, ignorando minúsculas ou maiúsculas
	List<Livro> findByNomeLivroContainingIgnoreCase(String nomeLivro);
	
	@Query("{ $and: [ {dataCriacao:{$gte: ?1} } , {dataCriacao:{$lte: ?2 } } , {$or: [ {'nomeLivro': {$regex: ?0, $options: 'i'} } , {'descricaoLivro': {$regex: ?0, $options: 'i'} } , {'comentarios.texto': {$regex: ?0, $options: 'i'} } ] } ] }")
	List<Livro> findFullSearch(String text, Date minDate, Date maxDate);
}

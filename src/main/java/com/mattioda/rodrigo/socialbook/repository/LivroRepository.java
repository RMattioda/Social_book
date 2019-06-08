package com.mattioda.rodrigo.socialbook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mattioda.rodrigo.socialbook.domain.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

	//Busca o nome do livro contendo a string informada pelo usuário, ignorando minúsculas ou maiúsculas
	List<Livro> findByNomeLivroContainingIgnoreCase(String nomeLivro);
}

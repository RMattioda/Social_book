package com.mattioda.rodrigo.socialbook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mattioda.rodrigo.socialbook.domain.Autor;

@Repository
public interface AutorRepository extends MongoRepository<Autor, String> {

	List<Autor> findByNomeAutorContainingIgnoreCase(String nomeAutor);
}

package com.mattioda.rodrigo.socialbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.Autor;
import com.mattioda.rodrigo.socialbook.repository.AutorRepository;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

@Service
public class AutorService {

	@Autowired
	public AutorRepository autorRepository;

	public List<Autor> findAll(){
		return autorRepository.findAll();
	}
	
	public Autor findById(String id){
		Optional<Autor> autor = autorRepository.findById(id);
		return autor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public List<Autor> findByNomeAutor(String nomeAutor){
		return autorRepository.findByNomeAutorContainingIgnoreCase(nomeAutor);
	}
}

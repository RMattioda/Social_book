package com.mattioda.rodrigo.socialbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.Livro;
import com.mattioda.rodrigo.socialbook.repository.LivroRepository;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	public LivroRepository livroRepository;

	public Livro findById(String id){
		Optional<Livro> livro = livroRepository.findById(id);
		return livro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public List<Livro> findByNomeLivro(String nomeLivro){
		return livroRepository.findByNomeLivroContainingIgnoreCase(nomeLivro);
	}
}

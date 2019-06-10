package com.mattioda.rodrigo.socialbook.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mattioda.rodrigo.socialbook.domain.Autor;
import com.mattioda.rodrigo.socialbook.resources.util.URL;
import com.mattioda.rodrigo.socialbook.services.AutorService;

@RestController
@RequestMapping(value="/autores")
public class AutorResource {

	@Autowired
	private AutorService autorService;
	
	//Não usamos Dto pois não existem informações que queremos selecionar, precisamos de tudo!
	@GetMapping
	public ResponseEntity <List<Autor>> findAll(){
		List<Autor>lista=autorService.findAll();
		return ResponseEntity.ok(lista);	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity <Autor> findById(@PathVariable String id){
		Autor autor = autorService.findById(id);
		return ResponseEntity.ok().body(autor);	
	}

	@GetMapping(value="/searchAutor")
	public ResponseEntity <List<Autor>> findNomeAutor(@RequestParam(value="nomeAutor", defaultValue="") String nomeAutor){
		
		//Decodificando Parâmetros
		nomeAutor=URL.decodeParametros(nomeAutor);		
		List<Autor> autor = autorService.findByNomeAutor(nomeAutor);
		return ResponseEntity.ok().body(autor);	
	}

	
}

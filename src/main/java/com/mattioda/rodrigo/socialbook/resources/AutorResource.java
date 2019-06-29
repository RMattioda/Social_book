package com.mattioda.rodrigo.socialbook.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Autor autor){
		autor= autorService.insertAutor(autor);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity <Void> delete(@PathVariable String id){
		autorService.deleteAutor(id);
		return ResponseEntity.noContent().build();	
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Autor autor, @PathVariable String id){
		autor.setId(id);
		autor= autorService.updateAutor(autor);
		return ResponseEntity.noContent().build();	
	}
}

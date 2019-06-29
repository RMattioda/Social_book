package com.mattioda.rodrigo.socialbook.resources;

import java.net.URI;
import java.util.Date;
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

import com.mattioda.rodrigo.socialbook.domain.Livro;
import com.mattioda.rodrigo.socialbook.resources.util.URL;
import com.mattioda.rodrigo.socialbook.services.LivroService;

@RestController
@RequestMapping(value="/livros")
public class LivroResource {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public ResponseEntity <List<Livro>> findAll(){
		List<Livro>lista=livroService.findAll();
		return ResponseEntity.ok(lista);	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity <Livro> findById(@PathVariable String id){
		Livro livro = livroService.findById(id);
		return ResponseEntity.ok().body(livro);	
	}

	//Come está vindo por uma busca usando parâmetros e não variaveis se adiciona o @RequestParam
	//Se não for informado, por padrão ele vem vazio
	@GetMapping(value="/searchBook")
	public ResponseEntity <List<Livro>> findNomeLivro(@RequestParam(value="nomeLivro", defaultValue="") String nomeLivro){
		
		//Decodificando Parâmetros
		nomeLivro=URL.decodeParametros(nomeLivro);		
		List<Livro> livro = livroService.findByNomeLivro(nomeLivro);
		return ResponseEntity.ok().body(livro);	
	}

	@GetMapping(value="/fullSearch")
	public ResponseEntity <List<Livro>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate){
		text=URL.decodeParametros(text);	
		Date min=URL.convertDate(minDate, new Date(0));
		Date max=URL.convertDate(maxDate, new Date());
		List<Livro> livro = livroService.findFullSearch(text, min, max);
		return ResponseEntity.ok().body(livro);	
	}
	
	//Por enquanto, somente administradores podem adicionar livros
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Livro livro){
		livro= livroService.insertLivro(livro);
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Livro livro, @PathVariable String id){
		livro.setId(id);
		livro= livroService.update(livro);
		return ResponseEntity.noContent().build();	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity <Void> delete(@PathVariable String id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();	
	}
}

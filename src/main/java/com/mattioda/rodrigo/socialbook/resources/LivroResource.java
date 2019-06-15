package com.mattioda.rodrigo.socialbook.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}

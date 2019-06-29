package com.mattioda.rodrigo.socialbook.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mattioda.rodrigo.socialbook.domain.Livro;
import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.dto.UserDto;
import com.mattioda.rodrigo.socialbook.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity <List<UserDto>> findAll(){
		List<User>lista=userService.findAll();
		//Passamos para dentro da nova listaDto
		//Usa o steam para pois é compatível com expressões lambda
		//O map vai pegar cada um dos elementos do objeto que roda na query e passa para o objeto DTO que por fim
		//o manda para o objeto Stram que volta para o formato de lista
		List<UserDto> listDto=lista.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
	return ResponseEntity.ok(listDto);
	}	
	
	@GetMapping(value="/{id}")
	public ResponseEntity <UserDto> findById(@PathVariable String id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDto(user));	
	}
	
	
	
	//Void pois não retorna os dados criados
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto userDto){
		User user = userService.fromDto(userDto);
		user= userService.insert(user);
		
		//Pega o novo endereço do Id que foi gerado e cria a nova uri
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		//Created traz o código 201 (que é o código de quando você cria um novo recurso)
		return ResponseEntity.created(uri).build();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity <Void> delete(@PathVariable String id){
		userService.delete(id);
		
		//Retorna um 204(noContent), que não precisa de retorno
		return ResponseEntity.noContent().build();	
	}
	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDto userDto, @PathVariable String id){
		User user = userService.fromDto(userDto);
		user.setId(id);
		user= userService.update(user);
		//Retorna um 204(noContent), que não precisa de retorno
		return ResponseEntity.noContent().build();	
	}
	
	
	
	@RequestMapping(value="/{id}/livros",method=RequestMethod.GET)
	public ResponseEntity <List<Livro>> findLivros(@PathVariable String id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getLivrosUsuario());	
	}
}

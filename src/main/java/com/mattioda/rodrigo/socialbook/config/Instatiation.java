package com.mattioda.rodrigo.socialbook.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mattioda.rodrigo.socialbook.domain.Autor;
import com.mattioda.rodrigo.socialbook.domain.Livro;
import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.domain.enums.TipoUser;
import com.mattioda.rodrigo.socialbook.dto.AutorPublicacaoDto;
import com.mattioda.rodrigo.socialbook.dto.ComentariosDto;
import com.mattioda.rodrigo.socialbook.repository.AutorRepository;
import com.mattioda.rodrigo.socialbook.repository.LivroRepository;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		livroRepository.deleteAll();
		autorRepository.deleteAll();
		
		List<String> listaDeCategorias= new ArrayList<>();
		listaDeCategorias.add("Terror");
		
		List<String> listaDeInteresses1= new ArrayList<>();
		listaDeInteresses1.add("Terror");
		listaDeInteresses1.add("Romance");
		
		List<String> listaDeInteresses2= new ArrayList<>();
		listaDeInteresses2.add("Terror");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User rodrigo= new User(null, "Rodrigo", "Mattioda", "rodrigomattioda@gmail.com", listaDeInteresses1, pe.encode("123"));
		rodrigo.addTipoUsuario(TipoUser.ADMIN);
		
		User julia= new User(null, "Júlia", "Gambirazio", "juju@gmail.com", listaDeInteresses2, pe.encode("12345"));
		julia.addTipoUsuario(TipoUser.USER);
		
		userRepository.saveAll(Arrays.asList(rodrigo, julia));
		
		Livro livro1= new Livro(null, "Joyland", "Stephen King", "Terror", "alamo", "Bem louco",sdf.parse("08/06/2019"), new AutorPublicacaoDto(rodrigo));
		Livro livro2= new Livro(null, "Juliette", "Desconhecido", "Romance", "cine vídeo", "nem sei",sdf.parse("07/05/2018"), new AutorPublicacaoDto(julia));
	
		Autor autor1= new Autor(null, "Stephen", "King", "Stephen King", sdf.parse("20/03/1959"), null, 60, "Eua", listaDeCategorias);
		
		ComentariosDto comentario1= new ComentariosDto("Amo este livro", sdf.parse("09/06/2019"), new AutorPublicacaoDto(rodrigo));
		ComentariosDto comentario2= new ComentariosDto("Não gosto de terror )=", sdf.parse("10/06/2019"), new AutorPublicacaoDto(julia));
		ComentariosDto comentario3= new ComentariosDto("O Melhor", sdf.parse("10/06/2019"), new AutorPublicacaoDto(julia));
		ComentariosDto comentario4= new ComentariosDto("SK, O Melhor", sdf.parse("10/06/2019"), new AutorPublicacaoDto(rodrigo));
		
		livro1.getComentarios().addAll(Arrays.asList(comentario1, comentario2));
		livro2.getComentarios().addAll(Arrays.asList(comentario3));
		
		autor1.getComentarios().addAll(Arrays.asList(comentario4));
		autor1.getListaLivroAutor().addAll(Arrays.asList(livro1));
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2));
		autorRepository.saveAll(Arrays.asList(autor1));
		
		rodrigo.getLivrosUsuario().addAll(Arrays.asList(livro1, livro2));
		julia.getLivrosUsuario().addAll(Arrays.asList(livro2));
		
		//rodrigo.getAutoresUsuario().addAll(Arrays.asList(autor1));
		
		userRepository.save(rodrigo);
		userRepository.save(julia);
		
	}
	
	
}

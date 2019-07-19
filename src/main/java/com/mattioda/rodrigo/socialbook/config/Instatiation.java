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
		Livro livro3= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro4= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro5= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro6= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro7= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro8= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro9= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro10= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro11= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro12= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro13= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro14= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro15= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro16= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro17= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro18= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro19= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro20= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro21= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro22= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro23= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro24= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro25= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro26= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro27= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro28= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro29= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro30= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro31= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro32= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro33= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro34= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro35= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro36= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro37= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro38= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro39= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro40= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro41= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		Livro livro42= new Livro(null, "teste", "teste", "teste", "teste", "teste teste teste", sdf.parse("07/07/2018"), new AutorPublicacaoDto(julia));
		
		
		
		
		Autor autor1= new Autor(null, "Stephen", "King", "Stephen King", sdf.parse("20/03/1959"), null, 60, "Eua", listaDeCategorias);
		
		ComentariosDto comentario1= new ComentariosDto("Amo este livro", sdf.parse("09/06/2019"), new AutorPublicacaoDto(rodrigo));
		ComentariosDto comentario2= new ComentariosDto("Não gosto de terror )=", sdf.parse("10/06/2019"), new AutorPublicacaoDto(julia));
		ComentariosDto comentario3= new ComentariosDto("O Melhor", sdf.parse("10/06/2019"), new AutorPublicacaoDto(julia));
		ComentariosDto comentario4= new ComentariosDto("SK, O Melhor", sdf.parse("10/06/2019"), new AutorPublicacaoDto(rodrigo));
		
		livro1.getComentarios().addAll(Arrays.asList(comentario1, comentario2));
		livro2.getComentarios().addAll(Arrays.asList(comentario3));
		
		autor1.getComentarios().addAll(Arrays.asList(comentario4));
		autor1.getListaLivroAutor().addAll(Arrays.asList(livro1,livro2,livro3,livro4,livro5,livro6,livro7,livro8,livro9,livro10,livro11,livro12,
				livro13,livro14,livro15,livro16,livro17,livro18,livro19,livro20,livro21,livro22,livro23,livro24,livro25,livro26,livro27,livro28,
				livro29,livro30,livro31,livro32,livro33,livro34,livro35,livro36,livro37,livro38,livro39,livro40,livro41,livro42));
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3,livro4,livro5,livro6,livro7,livro8,livro9,livro10,livro11,livro12,
				livro13,livro14,livro15,livro16,livro17,livro18,livro19,livro20,livro21,livro22,livro23,livro24,livro25,livro26,livro27,livro28,
				livro29,livro30,livro31,livro32,livro33,livro34,livro35,livro36,livro37,livro38,livro39,livro40,livro41,livro42));
		autorRepository.saveAll(Arrays.asList(autor1));
		
		rodrigo.getLivrosUsuario().addAll(Arrays.asList(livro1, livro2));
		julia.getLivrosUsuario().addAll(Arrays.asList(livro2));
		
		//rodrigo.getAutoresUsuario().addAll(Arrays.asList(autor1));
		
		userRepository.save(rodrigo);
		userRepository.save(julia);
		
	}
	
	
}

package com.mattioda.rodrigo.socialbook.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.domain.enums.TipoUser;
import com.mattioda.rodrigo.socialbook.dto.UserDto;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.security.UserSecurity;
import com.mattioda.rodrigo.socialbook.services.exception.AuthorizationException;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer tamanhoImagem;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		UserSecurity userSS = UserLogadoService.authenticated();
		if (userSS == null || !userSS.hasRole(TipoUser.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		// O Optional faz com que buscas inexistentes estourem um erro, se houve sucesso
		// ele returna o id do usuário
		Optional<User> user = userRepository.findById(id);
		// ObjectNotFoundException é uma classe de excessão criada e lançada caso não
		// seja encontrado o id
		// A excessão é lançada com o elseThrow numa expressão lambda
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User user) {
		// objeto original do banco
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}

	private void updateData(User newUser, User user) {
		newUser.setNome(user.getNome());
		newUser.setSobrenome(user.getSobrenome());
		newUser.setTelefone(user.getTelefone());
		newUser.setCidade(user.getCidade());
		newUser.setEstado(user.getEstado());
	}

	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getNome(), userDto.getSobrenome(), userDto.getEmail(),
				userDto.getInteresses(), pe.encode(userDto.getSenha()));
	}

	public URI uploadProfitePicture(MultipartFile multipartFile) {
		UserSecurity userSS = UserLogadoService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado!");
		}

		BufferedImage jpgImage = imageService.getJpgFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, tamanhoImagem);

		String nomeArquivo = prefix + userSS.getId() + ".jpg";
		return s3Service.uploadFile(nomeArquivo, imageService.getInputStream(jpgImage, "jpg"), "image");
	}

//	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
//		UserSecurity userSS= UserLogadoService.authenticated();
//		if(userSS==null) {
//			throw new AuthorizationException("Acesso negado");
//		}		
//		PageRequest pageRequest= PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
//		User user= this.findById(userSS.getId());
//		List<Livro> livroUsuario= user.getLivrosUsuario();
//		return userRepository.findByLivrosUsuario(livroUsuario ,pageRequest);
//	}
}

package com.mattioda.rodrigo.socialbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.dto.UserDto;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id){
		//O Optional faz com que buscas inexistentes estourem um erro, se houve sucesso ele returna o id do usuário
		Optional<User> user = userRepository.findById(id);
		//ObjectNotFoundException é uma classe de excessão criada e lançada caso não seja encontrado o id
		//A excessão é lançada com o elseThrow numa expressão lambda
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
		//objeto original do banco
		User newUser =findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	private User updateSenha(User user) {
		User newUser=findById(user.getId());
		updateDataSenha(newUser, user);
		return userRepository.save(newUser);
	}
	private void updateData(User newUser, User user) {
		newUser.setNome(user.getNome());
		newUser.setSobrenome(user.getSobrenome());
		newUser.setEmail(user.getEmail());
		newUser.setTelefone(user.getTelefone());
		newUser.setCidade(user.getCidade());
		newUser.setEstado(user.getEstado());
	}
	private void updateDataSenha(User newUser, User user) {
		newUser.setSenha(user.getSenha());
	}

	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(),userDto.getNome(),
				userDto.getSobrenome(), userDto.getEmail(),
				userDto.getInteresses(), pe.encode(userDto.getSenha()));
	}
}

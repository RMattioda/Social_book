package com.mattioda.rodrigo.socialbook.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mattioda.rodrigo.socialbook.domain.Livro;
import com.mattioda.rodrigo.socialbook.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Transactional(readOnly=true)
	User findByEmail(String email);
	
	@Transactional(readOnly=true)
	Page<User> findByLivrosUsuario(List<Livro> livros, Pageable pageRequest);
}

package com.mattioda.rodrigo.socialbook.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mattioda.rodrigo.socialbook.domain.enums.TipoUser;

public class UserSecurity implements UserDetails{

	private String id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSecurity() {
		super();
	}
	
	public UserSecurity(String id, String email, String senha, Set<TipoUser> tipoUser) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = tipoUser.stream().map(x->new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	public String getId() {
		return id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	//É o método que verifica se a conta pode expirar, uma lógica pode ser adicionada no futuro
	//por hora, não expira!
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//Método que verifica se uma conta está bloqueada
	//por hora, não está
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Por padrão, por enquanto, as credenciais não estão expiradas
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//Se o usuário está ativo, no futuro pode se aplicar uma lógica para verificação
	//por hora, está ativo
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(TipoUser perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}

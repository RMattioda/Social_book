package com.mattioda.rodrigo.socialbook.domain.enums;

public enum TipoUser {

	ADMIN(1, "ROLE_ADMIN"),
	USER(2, "ROLE_USER");
	
	
	private int cod;
	private String descricao;
	
	private TipoUser(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoUser toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(TipoUser x : TipoUser.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}

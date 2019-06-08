package com.mattioda.rodrigo.socialbook.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	//Faz a decodificação dos parâmetros que vem da url, se der erro retorna vazio
	public static String decodeParametros(String nomeLivro) {
		try {
			return URLDecoder.decode(nomeLivro, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}

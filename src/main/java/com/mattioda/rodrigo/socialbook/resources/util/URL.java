package com.mattioda.rodrigo.socialbook.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	//Faz a decodificação dos parâmetros que vem da url, se der erro retorna vazio
	public static String decodeParametros(String nomeLivro) {
		try {
			return URLDecoder.decode(nomeLivro, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	}
}

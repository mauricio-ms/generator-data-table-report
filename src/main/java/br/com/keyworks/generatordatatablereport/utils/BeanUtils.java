package br.com.keyworks.generatordatatablereport.utils;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Classe utilitária
 * para necessidades comuns
 * quando for necessário trabalhar
 * com Reflections
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public final class BeanUtils {

	private BeanUtils() {
	}

	/**
	 * Retorna o nome do método
	 * passado por parâmetro sem
	 * o get(se houver), e com
	 * a primeira letra não capitalizada
	
	 * @param methodName
	 *
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public static String getMethodNameWithouGet(final String methodName) {
		return WordUtils.uncapitalize(methodName.replaceAll("get", ""));
	}
}
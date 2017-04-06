package br.com.keyworks.generatordatatablereport.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * Classe para auxiliar em
 * funções com manipulação de String
 *
 * @author mauricio.scopel
 *
 * @since 20 de out de 2016
 */
public final class StringHelper {

	private StringHelper() {
	}

	/**
	 * Transforma a lista de valores
	 * em uma String separada pelo delimitador
	 *
	 * @param values
	 * @param delimiter
	 * 
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 20 de out de 2016
	 */
	public static <T> String joinWith(final List<T> values, final String delimiter) {
		return values.stream().map(String::valueOf)
						.collect(Collectors.joining(delimiter));
	}

	/**
	 * Retorna a String com espaços em branco,
	 * antes do caractere com letra maiúscula
	 * 
	 * Exemplo:
	 * testeTesteTeste -> teste Teste Teste
	 *
	 * @param value
	 *
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 2 de jan de 2017
	 */
	public static String addWhiteSpacesByCamelCase(final String value) {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(value), " ");
	}
}
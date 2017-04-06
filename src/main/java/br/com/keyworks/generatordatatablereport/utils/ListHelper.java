package br.com.keyworks.generatordatatablereport.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Classe utilitária para
 * operações sobre lista
 *
 * @author mauricio.scopel
 *
 * @since 3 de out de 2016
 */
public final class ListHelper {

	private ListHelper() {
	}

	/**
	 * Mapeia uma lista para um subtipo que o objeto contenha
	 *
	 * @param list
	 *            A lista original
	 * 
	 * @param mapper
	 *            O mapeamento desejado, um lambda
	 *
	 * @return List<I>
	 *
	 * @author mauricio.ms
	 *
	 * @date 3 de out de 2016
	 */
	public static <I, O> List<I> map(List<O> list, Function<O, I> mapper) {
		return list.stream().map(mapper).collect(Collectors.toList());
	}

	/**
	 * Factory method para criar
	 * uma lista a partir de um varargs
	 *
	 * @param values
	 *
	 * @return List<T>
	 *
	 * @author mauricio.ms
	 *
	 * @since 18 de out de 2016
	 */
	@SafeVarargs
	public static <T> List<T> of(T... values) {
		return Stream.of(values).collect(Collectors.toList());
	}

	/**
	 * Factory method para criar
	 * uma lista imutável a partir
	 * de um varargs
	 *
	 * @param values
	 *
	 * @return List<T>
	 *
	 * @author mauricio.ms
	 *
	 * @since 18 de out de 2016
	 */
	@SafeVarargs
	public static <T> List<T> immutableOf(T... values) {
		return Arrays.asList(values);
	}

	/**
	 * Factory method para criar
	 * uma lista imutável vazia
	 *
	 * @param values
	 *
	 * @return List<T>
	 *
	 * @author mauricio.ms
	 *
	 * @since 18 de out de 2016
	 */
	public static <T> List<T> immutableEmpty() {
		return Arrays.asList();
	}

	/**
	 * Factory method para criar
	 * uma lista vazia
	 *
	 * @return
	 *
	 * @return List<T>
	 *
	 * @author mauricio.ms
	 *
	 * @since 28 de nov de 2016
	 */
	public static <T> List<T> empty() {
		return new ArrayList<>();
	}

	/**
	 * Método para criar n objetos
	 *
	 * @param n
	 * 	Número de objetos a ser criado
	 * 
	 * @param creator
	 * 	Função que cria o objeto
	 *
	 * @return List<T>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public static <T> List<T> createN(final Long n, final LongFunction<T> creator) {
		return LongStream.range(0, n).mapToObj(creator).collect(Collectors.toList());
	}
}
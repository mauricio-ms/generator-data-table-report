package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Classe para efetuar o registro De {@link CustomExpressionForRegister}
 * que servem para sobreescrever o conteúdo exibido para algum
 * determinado tipo, por exemplo se eu quero que um Boolean
 * exiba 'YES' ou 'NO', posso criar uma implementação
 * que extende de {@link CustomExpressionAbstract}
 * e executar 
 * { @code RegisterCustomExpressions#getInstance()#put(Boolean.class, MyImplementaionOfCustomExpressionForBoolean) }
 *
 * @author mauricio.scopel
 *
 * @since 5 de jan de 2017
 */
public final class RegisterCustomExpressions {

	private Map<Class<?>, CustomExpressionAbstract> customExpressions;

	private static final RegisterCustomExpressions INSTANCE = new RegisterCustomExpressions();

	private RegisterCustomExpressions() {
		customExpressions = new HashMap<>();
	}

	public static RegisterCustomExpressions getInstance() {
		return INSTANCE;
	}

	public void put(final Class<?> classToCustomExpression,
					final CustomExpressionAbstract customExpressionAbstract) {
		customExpressions.put(classToCustomExpression, customExpressionAbstract);
	}

	public Optional<CustomExpressionAbstract> getIfIsAssignableFrom(
					final Class<?> classToCustomExpression) {
		return customExpressions.entrySet().stream().filter(
						value -> value.getKey().isAssignableFrom(classToCustomExpression))
						.map(Entry::getValue).findFirst();
	}
}
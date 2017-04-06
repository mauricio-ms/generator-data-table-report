package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import ar.com.fdvs.dj.domain.CustomExpression;

/**
 * Classe abstrata para definir uma custom expression 
 * que pode ser registrada via {@link RegisterCustomExpressions}
 *
 * @author mauricio.scopel
 *
 * @since 5 de jan de 2017
 */
public abstract class CustomExpressionAbstract implements CustomExpression {

	private static final long serialVersionUID = 7858347653086005416L;

	private String property;

	private String valueWhenNoData;

	public CustomExpressionAbstract() {
	}

	public CustomExpressionAbstract(final String property, final String valueWhenNoData) {
		Objects.requireNonNull(property, "property não deve ser null");
		Objects.requireNonNull(valueWhenNoData, "valueWhenNoData não deve ser null");
		this.valueWhenNoData = valueWhenNoData;
		this.property = property;
	}

	/**
	 * Retorna a property do campo que estã sendo avaliado
	 * 
	 * Para pegar o valor do campo, na implementação do 
	 * {@link #evaluate(java.util.Map, java.util.Map, java.util.Map)}
	 * utilizar:
	 * 	final MeuTipo val = (MeuTipo) fields.get(getProperty());
	 *
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 5 de jan de 2017
	 */
	protected String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		Objects.requireNonNull(property, "property não deve ser null");
		this.property = property;
	}

	public void setValueWhenNoData(String valueWhenNoData) {
		Objects.requireNonNull(valueWhenNoData, "valueWhenNoData não deve ser null");
		this.valueWhenNoData = valueWhenNoData;
	}

	/**
	 * Deve retornar o valor a ser exibido no relatório
	 * 
	 * Para pegar o valor do campo, na implementação do 
	 * {@link #evaluate(java.util.Map, java.util.Map, java.util.Map)}
	 * utilizar:
	 * 	final MeuTipo val = (MeuTipo) fields.get(getProperty());
	 *
	 * @param fields
	 * @param variables
	 * @param parameters
	 *
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 5 de jan de 2017
	 */
	@SuppressWarnings("rawtypes")
	public abstract String getValue(Map fields, Map variables, Map parameters);

	@SuppressWarnings("rawtypes")
	private String getValueConsideringWhenNoData(Map fields, Map variables,
					Map parameters) {
		final String value = getValue(fields, variables, parameters);
		return StringUtils.isNotBlank(value) ? value : valueWhenNoData;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object evaluate(Map fields, Map variables, Map parameters) {
		return getValueConsideringWhenNoData(fields, variables, parameters);
	}
}
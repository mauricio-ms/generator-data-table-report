package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.Map;

/**
 * Classe para sobreescrever
 * o valor gerado para colunas
 * do tipo {@link Boolean}
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class CustomExpressionDefault extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	public CustomExpressionDefault(final String property, final String valueWhenNoData) {
		super(property, valueWhenNoData);
	}

	@Override
	public String getClassName() {
		return String.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {
		return fields.get(getProperty()) != null
						? String.valueOf(fields.get(getProperty())) : "";
	}
}
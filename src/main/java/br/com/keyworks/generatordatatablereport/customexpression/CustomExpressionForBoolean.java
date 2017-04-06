package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;

/**
 * Classe para sobreescrever
 * o valor gerado para colunas
 * do tipo {@link Boolean}
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class CustomExpressionForBoolean extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	public CustomExpressionForBoolean(final String property,
					final String valueWhenNoData) {
		super(property, valueWhenNoData);
	}

	@Override
	public String getClassName() {
		return Boolean.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {

		final Boolean val = (Boolean) fields.get(getProperty());

		return BooleanUtils.isTrue(val) ? "Sim" : "Não";
	}
}
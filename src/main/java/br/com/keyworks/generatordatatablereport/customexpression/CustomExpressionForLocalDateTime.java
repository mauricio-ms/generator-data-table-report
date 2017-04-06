package br.com.keyworks.generatordatatablereport.customexpression;

import java.time.LocalDateTime;
import java.util.Map;
import br.com.keyworks.generatordatatablereport.utils.DateFormatterHelper;

/**
 * Classe para sobreescrever
 * o valor gerado para colunas
 * do tipo {@link LocalDateTime}
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class CustomExpressionForLocalDateTime extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	public CustomExpressionForLocalDateTime(final String property,
					final String valueWhenNoData) {
		super(property, valueWhenNoData);
	}

	@Override
	public String getClassName() {
		return LocalDateTime.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {

		final LocalDateTime val = (LocalDateTime) fields.get(getProperty());

		return DateFormatterHelper.formatDateTime(val);
	}
}
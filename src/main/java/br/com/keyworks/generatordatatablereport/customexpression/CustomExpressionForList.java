package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.List;
import java.util.Map;
import br.com.keyworks.generatordatatablereport.utils.StringHelper;

/**
 * Classe para sobreescrever
 * o valor gerado para colunas
 * do tipo {@link List}
 * 
 * Retorna o valor de todos os valores
 * da lista executando o toString
 * e concatenando-os com ', '
 *
 * @see StringHelper#joinWith(List, String)
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class CustomExpressionForList extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	public CustomExpressionForList(final String property, final String valueWhenNoData) {
		super(property, valueWhenNoData);
	}

	@Override
	public String getClassName() {
		return List.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {

		final List<?> val = (List<?>) fields.get(getProperty());

		return val != null ? StringHelper.joinWith(val, ", ") : "";
	}
}
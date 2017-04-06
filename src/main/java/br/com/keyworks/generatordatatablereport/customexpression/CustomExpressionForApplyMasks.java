package br.com.keyworks.generatordatatablereport.customexpression;

import java.util.Map;
import br.com.keyworks.generatordatatablereport.mask.ApplyMask;

/**
 * Classe para sobreescrever
 * o valor gerado para colunas
 * que têm máscaras para serem
 * aplicadas aos valores
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class CustomExpressionForApplyMasks extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	private final String mask;

	public CustomExpressionForApplyMasks(final String property,
					final String valueWhenNoData, final String mask) {
		super(property, valueWhenNoData);
		this.mask = mask;
	}

	@Override
	public String getClassName() {
		return String.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {

		final String val = (String) fields.get(getProperty());
		final String valueOf = String.valueOf(val);

		return val != null ? new ApplyMask(valueOf, mask).getWithMask().orElse(valueOf)
						: null;
	}
}
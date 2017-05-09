package br.com.keyworks.generatordatatablereport.addcolumn;

import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionForBoolean;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Classe para adicionar uma coluna
 * a um {@link FastReportBuilder}
 * quando o tipo de dado for um {@link Boolean}
 * 
 * Exibe uma infromação customizada ao invés de true ou false
 * 
 * @see CustomExpressionForBoolean
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class AddColumnForBoolean extends AddColumn {

	public AddColumnForBoolean(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		super(fastReportBuilder, dataColumn);
	}

	@Override
	public AbstractColumn getColumn() {
		return getBuilder()
						.setCustomExpression(new CustomExpressionForBoolean(
										getDataColumn().getProperty(),
										getDataColumn().getColumnReport().whenNoData()))
						.build();
	}
}
package br.com.keyworks.generatordatatablereport.addcolumn;

import java.time.LocalDateTime;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionForLocalDateTime;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Classe para adicionar uma coluna
 * a um {@link FastReportBuilder}
 * quando o tipo de dado for um {@link LocalDateTime}
 * 
 * Exibe a data formatada
 * 
 * @see CustomExpressionForLocalDateTime
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class AddColumnForLocalDateTime extends AddColumn {

	public AddColumnForLocalDateTime(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		super(fastReportBuilder, dataColumn);
	}

	@Override
	public void addColumn() {
		final AbstractColumn abstractColumn = getBuilder()
						.setCustomExpression(new CustomExpressionForLocalDateTime(
										getDataColumn().getProperty(),
										getDataColumn().getColumnReport().whenNoData()))
						.build();

		getFastReportBuilder().addColumn(abstractColumn);
	}
}
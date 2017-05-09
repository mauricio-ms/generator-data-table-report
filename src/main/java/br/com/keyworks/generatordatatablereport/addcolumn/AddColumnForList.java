package br.com.keyworks.generatordatatablereport.addcolumn;

import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport.List;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionForList;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Classe para adicionar uma coluna
 * a um {@link FastReportBuilder}
 * quando o tipo de dado for um {@link List}
 * 
 * Exibe a lista concateda por ', '
 * 
 * @see CustomExpressionForList
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class AddColumnForList extends AddColumn {

	public AddColumnForList(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		super(fastReportBuilder, dataColumn);
	}

	@Override
	public AbstractColumn getColumn() {
		return getBuilder()
						.setCustomExpression(new CustomExpressionForList(
										getDataColumn().getProperty(),
										getDataColumn().getColumnReport().whenNoData()))
						.build();
	}
}
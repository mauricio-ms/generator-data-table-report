package br.com.keyworks.generatordatatablereport.addcolumn;

import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionForApplyMasks;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Classe para adicionar uma coluna
 * a um {@link FastReportBuilder}
 * quando o tipo de dado tiver uma
 * máscara associada, como CPF,
 * CNPJ, CEP, etc..
 * 
 * Exibe o valor formatado
 * 
 * @see CustomExpressionForApplyMasks
 * @See ColumnReport
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class AddColumnForFieldsWithMask extends AddColumn {

	public AddColumnForFieldsWithMask(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		super(fastReportBuilder, dataColumn);
	}

	@Override
	public AbstractColumn getColumn() {
		final DataColumn dataColumn = getDataColumn();
		return getBuilder().setCustomExpression(
						new CustomExpressionForApplyMasks(dataColumn.getProperty(),
										getDataColumn().getColumnReport().whenNoData(),
										dataColumn.getColumnReport().mask()))
						.build();
	}
}
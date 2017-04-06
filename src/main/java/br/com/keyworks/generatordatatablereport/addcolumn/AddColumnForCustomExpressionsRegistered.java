package br.com.keyworks.generatordatatablereport.addcolumn;

import java.util.Objects;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionAbstract;
import br.com.keyworks.generatordatatablereport.customexpression.RegisterCustomExpressions;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Classe utilizada pelo framework para adicionar
 * colunas para expressões customizadas registradas
 * 
 * @see RegisterCustomExpressions
 * @see CustomExpressionAbstract
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public final class AddColumnForCustomExpressionsRegistered extends AddColumn {

	private final CustomExpressionAbstract customExpressionAbstract;

	public AddColumnForCustomExpressionsRegistered(
					final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn,
					final CustomExpressionAbstract customExpressionAbstract) {
		super(fastReportBuilder, dataColumn);
		Objects.requireNonNull(customExpressionAbstract,
						"customExpressionAbstract não deve ser null");
		this.customExpressionAbstract = customExpressionAbstract;
	}

	@Override
	public void addColumn() {
		final AbstractColumn abstractColumn = getBuilder()
						.setCustomExpression(customExpressionAbstract).build();

		getFastReportBuilder().addColumn(abstractColumn);
	}
}
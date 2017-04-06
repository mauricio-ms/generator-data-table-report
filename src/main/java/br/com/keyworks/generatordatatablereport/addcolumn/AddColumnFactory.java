package br.com.keyworks.generatordatatablereport.addcolumn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionAbstract;
import br.com.keyworks.generatordatatablereport.customexpression.RegisterCustomExpressions;
import br.com.keyworks.generatordatatablereport.model.DataColumn;

/**
 * Factory para, a partir
 * de um {@link DataColumn}
 * que contêm informações referente
 * a coluna, criar um {@link AddColumn}
 * que tem por objetivo adicionar uma
 * coluna ao relatório com um comportamento
 * específico dependendo dos dados contidos
 * no {@link DataColumn}
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class AddColumnFactory {

	private AddColumnFactory() {
	}

	public static AddColumn get(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {

		Objects.requireNonNull(fastReportBuilder, "fastReportBuilder não deve ser null");
		Objects.requireNonNull(dataColumn, "dataColumn não deve ser null");

		return getInstanceByRegistereds(fastReportBuilder,
						dataColumn).orElse(getInstanceForFieldsWithMask(fastReportBuilder,
										dataColumn).orElse(getInstanceByClassOfProperty(
														fastReportBuilder, dataColumn)
																		.orElse(getInstanceDefault(
																						fastReportBuilder,
																						dataColumn))));
	}

	private static Optional<AddColumn> getInstanceByRegistereds(
					final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {

		final Optional<CustomExpressionAbstract> customExpressionOp = RegisterCustomExpressions
						.getInstance()
						.getIfIsAssignableFrom(dataColumn.getClassOfProperty());

		if( customExpressionOp.isPresent() ) {
			final CustomExpressionAbstract customExpressionAbstract = customExpressionOp
							.get();

			customExpressionAbstract.setProperty(dataColumn.getProperty());
			customExpressionAbstract.setValueWhenNoData(
							dataColumn.getColumnReport().whenNoData());

			return Optional.of(new AddColumnForCustomExpressionsRegistered(
							fastReportBuilder, dataColumn, customExpressionAbstract));
		}

		return Optional.empty();
	}

	private static Optional<AddColumn> getInstanceForFieldsWithMask(
					final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		return dataColumn.hasMask() ? Optional
						.of(new AddColumnForFieldsWithMask(fastReportBuilder, dataColumn))
						: Optional.empty();
	}

	private static Optional<AddColumn> getInstanceByClassOfProperty(
					final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {

		final Class<?> classOfProperty = dataColumn.getClassOfProperty();
		if( classOfProperty.equals(Boolean.class) ) {
			return Optional.of(new AddColumnForBoolean(fastReportBuilder, dataColumn));
		} else
			if( classOfProperty.equals(List.class) ) {
				return Optional.of(new AddColumnForList(fastReportBuilder, dataColumn));
			} else
				if( classOfProperty.equals(LocalDateTime.class) ) {
					return Optional.of(new AddColumnForLocalDateTime(fastReportBuilder,
									dataColumn));
				} else {
					return Optional.empty();
				}
	}

	private static AddColumn getInstanceDefault(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		return new AddColumn(fastReportBuilder, dataColumn);
	}
}
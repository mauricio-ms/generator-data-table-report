package br.com.keyworks.generatordatatablereport.addcolumn;

import java.util.Objects;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Stretching;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionDefault;
import br.com.keyworks.generatordatatablereport.model.DataColumn;
import br.com.keyworks.generatordatatablereport.styles.StyleHeaderFactory;

/**
 * Classe para adicionar uma coluna
 * a um {@link FastReportBuilder}
 * 
 * Deve receber um {@link DataColumn}
 * que é uma classe para agrupar informções
 * da coluna
 * 
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public class AddColumn {

	private final FastReportBuilder fastReportBuilder;

	private final DataColumn dataColumn;

	private final StyleBuilder styleBuilder;

	private final ColumnBuilder builder;

	public AddColumn(final FastReportBuilder fastReportBuilder,
					final DataColumn dataColumn) {
		Objects.requireNonNull(fastReportBuilder, "fastReportBuilder não deve ser null");
		Objects.requireNonNull(dataColumn, "dataColumn não deve ser null");
		this.fastReportBuilder = fastReportBuilder;
		this.dataColumn = dataColumn;
		styleBuilder = getStyleBuilder();
		builder = createBuilder();
		makeConfigurations();
	}

	protected StyleBuilder getStyleBuilder() {
		return new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.LEFT)
						.setFont(Font.ARIAL_MEDIUM).setVerticalAlign(VerticalAlign.MIDDLE)
						.setPadding(5)
						.setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT)
						.setStretchWithOverflow(true);
	}

	private ColumnBuilder createBuilder() {
		return ColumnBuilder.getNew().setTitle(dataColumn.getTitle())
						.setColumnProperty(dataColumn.getProperty(),
										dataColumn.getClassOfProperty())
						.setStyle(getStyleBuilder().build());
	}

	private void makeConfigurations() {
		configureMarkup();
		setStyleToBuilder();
	}

	private void configureMarkup() {
		if( dataColumn.getColumnReport().html() ) {
			builder.setMarkup("html");
		}
	}

	private void setStyleToBuilder() {
		builder.setStyle(styleBuilder.build());
	}

	public void addColumn() {
		final AbstractColumn abstractColumn = getColumn();
		abstractColumn.setHeaderStyle(StyleHeaderFactory.get());
		fastReportBuilder.addColumn(abstractColumn);
		addGroupIfNecessary(abstractColumn);
	}

	public AbstractColumn getColumn() {
		return builder.setCustomExpression(
						new CustomExpressionDefault(dataColumn.getProperty(),
										dataColumn.getColumnReport().whenNoData()))
						.build();
	}

	public void addGroupIfNecessary(final AbstractColumn abstractColumn) {
		final ColumnReport columnReport = dataColumn.getColumnReport();
		if( columnReport.group() ) {
			final int indexLastColumnAdded = fastReportBuilder.getColumns().size();
			fastReportBuilder.addGroups(indexLastColumnAdded);
			fastReportBuilder.setGroupLayout(indexLastColumnAdded,
							GroupLayout.VALUE_IN_HEADER);
			abstractColumn.getStyle().getFont().setBold(true);
		}
	}

	public FastReportBuilder getFastReportBuilder() {
		return fastReportBuilder;
	}

	public DataColumn getDataColumn() {
		return dataColumn;
	}

	public ColumnBuilder getBuilder() {
		return builder;
	}
}
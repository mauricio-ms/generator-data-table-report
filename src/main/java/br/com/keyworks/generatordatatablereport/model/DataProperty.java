package br.com.keyworks.generatordatatablereport.model;

import java.util.List;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;

/**
 * Classe para agrupar
 * os metadados de um campo ou método
 * que são anotados com {@link ColumnReport} 
 *
 * @author mauricio.scopel
 *
 * @since 4 de jan de 2017
 */
public class DataProperty {

	private final String nameProperty;

	private final Class<?> type;

	private final List<ColumnReport> columnsReport;

	public DataProperty(final String nameProperty, final Class<?> type,
					final List<ColumnReport> columnsReport) {
		this.nameProperty = nameProperty;
		this.type = type;
		this.columnsReport = columnsReport;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public Class<?> getType() {
		return type;
	}

	public List<ColumnReport> getColumnsReport() {
		return columnsReport;
	}
}
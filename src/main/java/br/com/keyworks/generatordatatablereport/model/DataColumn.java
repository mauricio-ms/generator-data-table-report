package br.com.keyworks.generatordatatablereport.model;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;

/**
 * Classe para agrupar as informações
 * utilizadas para criar uma coluna
 * de um relatório
 * 
 * Agrupa basicamente o tipo
 * do campo e o nome da propriedade
 * para que seja possível gerar os valores
 * das linhas, o título a ser exibido no header, 
 * e as informações da anotação {@link ColumnReport}
 * que deve ser colocado sobre o campo ou método
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class DataColumn implements Comparable<DataColumn> {

	private final Class<?> classOfProperty;

	private final String title;

	private final String property;

	private final ColumnReport columnReport;

	public DataColumn(final Class<?> classOfProperty, final String title,
					final String property, final ColumnReport columnReport) {
		Objects.requireNonNull(classOfProperty, "classOfProperty não deve ser null");
		Objects.requireNonNull(title, "title não deve ser null");
		Objects.requireNonNull(property, "property não deve ser null");
		Objects.requireNonNull(columnReport, "columnReport não deve ser null");

		this.title = title;
		this.property = property;
		this.classOfProperty = classOfProperty;
		this.columnReport = columnReport;
	}

	public Boolean hasMask() {
		return StringUtils.isNotBlank(columnReport.mask());
	}

	public Boolean hasValue() {
		return StringUtils.isNotBlank(columnReport.value());
	}

	public String getTitle() {
		return title;
	}

	public String getProperty() {
		return property;
	}

	public Class<?> getClassOfProperty() {
		return classOfProperty;
	}

	public ColumnReport getColumnReport() {
		return columnReport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		DataColumn other = (DataColumn) obj;
		if( property == null ) {
			if( other.property != null )
				return false;
		} else
			if( !property.equals(other.property) )
				return false;
		if( title == null ) {
			if( other.title != null )
				return false;
		} else
			if( !title.equals(other.title) )
				return false;
		return true;
	}

	@Override
	public int compareTo(DataColumn o) {
		return ((Integer) columnReport.order()).compareTo(o.getColumnReport().order());
	}

	@Override
	public String toString() {
		return "DataColumn [property=" + property + ", classOfProperty=" + classOfProperty
						+ "]";
	}
}
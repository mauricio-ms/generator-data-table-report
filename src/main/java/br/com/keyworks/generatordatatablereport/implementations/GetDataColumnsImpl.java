package br.com.keyworks.generatordatatablereport.implementations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;
import br.com.keyworks.generatordatatablereport.model.DataColumn;
import br.com.keyworks.generatordatatablereport.model.DataProperty;
import br.com.keyworks.generatordatatablereport.reflection.FindInnerType;
import br.com.keyworks.generatordatatablereport.utils.BeanUtils;
import br.com.keyworks.generatordatatablereport.utils.ListHelper;
import br.com.keyworks.generatordatatablereport.utils.StringHelper;

/**
 * Implementação de {@link GetDataColumns}
 * 
 * Busca as colunas a partir das anotações
 * {@link ColumnReport}
 *
 * @author mauricio.scopel
 *
 * @since 5 de jan de 2017
 */
public final class GetDataColumnsImpl {

	private final Class<?> classOfData;

	public GetDataColumnsImpl(final Class<?> classOfData) {
		this.classOfData = classOfData;
	}

	/**
	 * Retorna a lista de DataColumn extraída 
	 * do tipo de dado 'classOfData'
	 * 
	 * Um {@link DataColumn} é um método
	 * ou uma propriedade anotado(a)
	 * com {@link ColumnReport}
	 *
	 * @see DataColumn
	 * @see ColumnReport
	 *
	 * @return List<DataColumn>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public List<DataColumn> get() {
		return Stream.concat(getDataColumnsFromFields().stream(),
						getDataColumnsFromMethod().stream()).sorted()
						.collect(Collectors.toList());
	}

	/**
	 * Retorna a lista de DataColumn extraída 
	 * dos métodos do tipo de dado 'classOfData'
	 * 
	 * Um {@link DataColumn} é um método
	 * ou uma propriedade anotado(a)
	 * com {@link ColumnReport}
	 *
	 * @see DataColumn
	 * @see ColumnReport
	 *
	 * @return List<DataColumn>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	private List<DataColumn> getDataColumnsFromMethod() {

		final Predicate<Method> filterMethodWithAnnotation = method -> method
						.isAnnotationPresent(ColumnReport.class)
						|| method.isAnnotationPresent(ColumnReport.List.class);

		final Function<Method, DataProperty> createDataPropertyByField = method -> new DataProperty(
						BeanUtils.getMethodNameWithouGet(method.getName()),
						method.getReturnType(),
						extractColumns().apply(method.getAnnotation(ColumnReport.class),
										method.getAnnotation(ColumnReport.List.class)));

		final List<DataProperty> dataProperties = Stream
						.of(classOfData.getDeclaredMethods())
						.filter(filterMethodWithAnnotation).map(createDataPropertyByField)
						.collect(Collectors.toList());

		return dataProperties.stream().map(createDataColumns()).flatMap(List::stream)
						.collect(Collectors.toList());
	}

	private BiFunction<ColumnReport, ColumnReport.List, List<ColumnReport>> extractColumns() {
		return (columnReport, columnReportList) -> columnReport != null
						? ListHelper.of(columnReport)
						: ListHelper.of(columnReportList.value());
	}

	/**
	 * Retorna a lista de DataColumn extraída 
	 * das propriedades do tipo de dado 'classOfData'
	 * 
	 * Um {@link DataColumn} é um método
	 * ou uma propriedade anotado(a)
	 * com {@link ColumnReport}
	 *
	 * @see DataColumn
	 * @see ColumnReport
	 *
	 * @return List<DataColumn>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	private List<DataColumn> getDataColumnsFromFields() {

		final Predicate<Field> filterFieldWithAnnotation = field -> field
						.isAnnotationPresent(ColumnReport.class)
						|| field.isAnnotationPresent(ColumnReport.List.class);

		final Function<Field, DataProperty> createDataPropertyByField = field -> new DataProperty(
						field.getName(), field.getType(),
						extractColumns().apply(field.getAnnotation(ColumnReport.class),
										field.getAnnotation(ColumnReport.List.class)));

		final List<DataProperty> dataProperties = Stream
						.of(classOfData.getDeclaredFields())
						.filter(filterFieldWithAnnotation).map(createDataPropertyByField)
						.collect(Collectors.toList());

		return dataProperties.stream().map(createDataColumns()).flatMap(List::stream)
						.collect(Collectors.toList());
	}

	/**
	 * Cria uma lista de {@link DataColumn}
	 * a partir de um {@link DataProperty}
	 * 
	 * Para cada {@link DataProperty}
	 * extrai todas {@link ColumnReport}
	 * associadas a ele, ou seja
	 * extrai a lista de anotações que 
	 * foram colocadas sobre um método
	 * ou propriedade
	 * 
	 * @see DataProperty
	 * @see DataColumn
	 * @see FindInnerType
	 *
	 * @return Function<DataProperty,List<DataColumn>>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	private Function<DataProperty, List<DataColumn>> createDataColumns() {

		final BiFunction<String, ColumnReport, String> createProperty = (nameProperty,
						columnReport) -> {

			final String value = columnReport.value();

			return StringUtils.isNotBlank(value)
							? String.format("%s.%s", nameProperty, value) : nameProperty;
		};

		final BiFunction<String, ColumnReport, String> extractLabel = (nameProperty,
						columnReport) -> StringUtils.isBlank(columnReport.label())
										? WordUtils.capitalize(nameProperty)
										: columnReport.label();

		final BiFunction<DataProperty, ColumnReport, Class<?>> extractClass = (
						dataProperty,
						columnReport) -> StringUtils.isNotBlank(columnReport.value())
										? new FindInnerType(dataProperty.getType(),
														columnReport.value()).get()
										: dataProperty.getType();

		final BiFunction<DataProperty, ColumnReport, DataColumn> createDataColumn = (
						dataProperty, columnReport) -> new DataColumn(
										extractClass.apply(dataProperty, columnReport),
										StringHelper.addWhiteSpacesByCamelCase(
														extractLabel.apply(
																		dataProperty.getNameProperty(),
																		columnReport)),
										createProperty.apply(
														dataProperty.getNameProperty(),
														columnReport),
										columnReport);

		return dataProperty -> dataProperty
						.getColumnsReport().stream().map(columnReport -> createDataColumn
										.apply(dataProperty, columnReport))
						.collect(Collectors.toList());
	}
}
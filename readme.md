```
  #####  ####### #     # ####### ######     #    ####### ####### ######     ######     #    #######    #       #######    #    ######  #       #######    ######  ####### ######  ####### ######  ####### 
 #     # #       ##    # #       #     #   # #      #    #     # #     #    #     #   # #      #      # #         #      # #   #     # #       #          #     # #       #     # #     # #     #    #    
 #       #       # #   # #       #     #  #   #     #    #     # #     #    #     #  #   #     #     #   #        #     #   #  #     # #       #          #     # #       #     # #     # #     #    #    
 #  #### #####   #  #  # #####   ######  #     #    #    #     # ######     #     # #     #    #    #     #       #    #     # ######  #       #####      ######  #####   ######  #     # ######     #    
 #     # #       #   # # #       #   #   #######    #    #     # #   #      #     # #######    #    #######       #    ####### #     # #       #          #   #   #       #       #     # #   #      #    
 #     # #       #    ## #       #    #  #     #    #    #     # #    #     #     # #     #    #    #     #       #    #     # #     # #       #          #    #  #       #       #     # #    #     #    
  #####  ####### #     # ####### #     # #     #    #    ####### #     #    ######  #     #    #    #     #       #    #     # ######  ####### #######    #     # ####### #       ####### #     #    #    
                                                                                                                                                                                                          
```

* Generator Data Table Report is a project to permit you create simple reports in a simple way.

# Getting Started

## By MAVEN

*   Create local repository
	``mvn install clean``

*   In pom.xml of project that wish to use 
    generator data table report add:

```
<dependency>
	<groupId>br.keyworks.generatordatatablereport</groupId>
	<artifactId>GeneratorDataTableReport</artifactId>
	<version>{wished-version}</version>
</dependency>
```

# Hot to create a report

## Use annotation in some model to define which columns will be displayed in report

### Model example

```
public class User {

	@ColumnReport(order = 1)
	private final String name;

	@ColumnReport(mask = "###.###.###-##", order = 2)
	private final String cpf;

	@ColumnReport(label = "Phone", mask = "(##) ####-####", order = 3)
	private final String phone;

	public User(final String name, 
	            final String cpf, 
	            final String phone) {
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}
}
```

* The class should have getter's

** The ColumnReport annotation defines that the field or method in which it is to be used will be a column in the report

** Params of ColumnReport annotation
1.  label: Defines the header name, if omitted, the field name or method name will be used, capitalizing the first letter and adding a blank space between others already capitelized
2.  mask: Permit to define a mask for fields as CEP, CPF, CNPJ, PHONE and so on ... Sould be used in the format ###.###.###-## by example, where each # will be replaced by the value of the field, and the other characters, other than # are retained
3. value: Permit to define that a value belonging to an object will be displayed as a column, by example:
	```
		@ColumnReport(value = "address.cidade")
		private User user;
	```
4. whenNoData: Permit to define a default value for when there is no data to bw displayed
5. order: Permit to define the order of exibition of columns
6. html: Permit that a column to be displayed as html, remembering that tags allowed are the same that Jasper support


## Openning a report
```
new GeneratorDataTableReport<>(listOfData,
						new InfoReportSimple(User.class, "Report - Users"))
										.openReport();
```

## Generate the report in bytes, to be possible create services to download
```
new GeneratorDataTableReport<>(listOfData,
						new InfoReportSimple(User.class, "Report - Users"))
										.generateReport();
```

## Overwriting the layout of report

# Overwriting the styles

## Implement GetStyles

```
public class GetStylesMyImpl implements GetStyles {

	public GetStylesMyImpl() {
	}

	@Override
	public Style getStyleTitle() {
		return new Style...;
	}

	@Override
	public Style getStyleSubtitle() {
		return new Style...;
	}

	@Override
	public Style getStyleWhenNoData() {
		return new Style...;
	}
}
```

## If you want to overwrite only some methods, and then extend GetStylesSimple

```
public class GetStylesMyImpl extends GetStylesSimple {

	public GetStylesMyImpl() {
	}

	@Override
	public Style getStyleTitle() {
		return new Style...;
	}
}
```

# Overwriting Layout

## Implement GetInfoLayout

```
public class GetInfoLayoutMyImpl implements GetInfoLayout {

	private final String nameReport;

	public GetInfoLayoutMyImpl(final String nameReport) {
		this.nameReport = nameReport;
	}

	@Override
	public String getTextTitle() {
		return nameReport;
	}

	@Override
	public String getTextSubtitle() {
		return "My subtitle";
	}

	@Override
	public Integer getBottomMargin() {
		return 50;
	}

	@Override
	public WhenNoData getWhenNoData() {
		return new WhenNoData("No data", true, false);
	}

	@Override
	public Optional<ImageBanner> getImageBannerToHeader() {
		return Optional.empty();
	}

	@Override
	public DecidePageOrientation getDecidePageOrientation() {
		return new DecidePageOrientation(5);
	}

	@Override
	public Boolean isPrintBackgroundOnOddRows() {
		return true;
	}

	@Override
	public Boolean isUseFullPageWidth() {
		return true;
	}
}
```

## If you want to overwrite only some methods, and then extend GetInfoLayoutSimple

```
public class GetInfoLayoutMyImpl extends GetInfoLayoutSimple {

	public GetInfoLayoutMyImpl(final String nameReport) {
		super(nameReport);
	}

	@Override
	public Optional<ImageBanner> getImageBannerToHeader() {
		return Optional.of(new ImageBanner("path-image",
						ImageAlignment.ALIGN_LEFT, 90, 60));
	}
}
```

# Finally, create InfoReport

## If you overwrite Styles and Layout, and then implement InfoReport

```
public class InfoReportMyImpl implements InfoReport {

	private final Class<?> classOfData;

	private final String nameReport;

	public InfoReportMyImpl(final Class<?> classOfData, final String nameReport) {
		Objects.requireNonNull(classOfData, "classOfData should not be null");
		Objects.requireNonNull(nameReport, "nameReport should not be null");
		this.classOfData = classOfData;
		this.nameReport = nameReport;
	}
	
	protected String getNameReport() {
		return nameReport;
	}
	
	@Override
	public Class<?> getClassOfData() {
		return classOfData;
	}

	@Override
	public GetStyles getGetStyles() {
		return new GetStylesMyImpl();
	}

	@Override
	public GetInfoLayout getGetInfoLayout() {
		return new GetInfoLayoutMyImpl(nameReport);
	}
}
```

## If you overwrite only one, or Styles, or Layout, and then extend InfoReportSimple

```
public final class InfoReportMyImpl extends InfoReportSimple {

	public InfoReportMyImpl(final Class<?> classOfData, 
	                        final String nameReport) {
		super(classOfData, nameReport);
	}

	@Override
	public GetInfoLayout getGetInfoLayout() {
		return new GetInfoLayoutMyImpl(getNameReport());
	}
}
```

# I want to display a data by a custom way

## Create a CustomExpression
```
public final class CustomExpressionForBoolean 
                   extends CustomExpressionAbstract {

	private static final long serialVersionUID = 1168523101575543022L;

	public CustomExpressionForBoolean() {
	}

	@Override
	public String getClassName() {
		return Boolean.class.getName();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getValue(Map fields, Map variables, Map parameters) {
		final Boolean val = (Boolean) fields.get(getProperty());
		return Boolean.TRUE.equals(val) ? "YES" : "NO";
	}
}
```
## Register a CustomExpression

```
RegisterCustomExpressions.getInstance().put(ClassForBeCustomized.class,
						new MyImplementationOfCustomExpression());
```
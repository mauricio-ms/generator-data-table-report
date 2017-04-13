package br.com.keyworks.generatordatatablereport;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.ImageScaleMode;
import ar.com.fdvs.dj.domain.constants.Page;
import br.com.keyworks.generatordatatablereport.addcolumn.AddColumnFactory;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;
import br.com.keyworks.generatordatatablereport.contracts.GetInfoLayout;
import br.com.keyworks.generatordatatablereport.contracts.GetStyles;
import br.com.keyworks.generatordatatablereport.contracts.InfoReport;
import br.com.keyworks.generatordatatablereport.implementations.GetDataColumnsImpl;
import br.com.keyworks.generatordatatablereport.layout.WhenNoData;
import br.com.keyworks.generatordatatablereport.model.DataColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe para gerar um relatório em forma 
 * de tabela representando uma lista de
 * dados, o modelo representado por <T> deve 
 * conter propriedades anotadas com
 * {@link ColumnReport} que serão 
 * as colunas do relatório
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public final class GeneratorDataTableReport<T> {

	private static final Logger LOGGER = Logger
					.getLogger(GeneratorDataTableReport.class.getName());

	private final List<T> data;

	private final InfoReport infoReport;

	private final FastReportBuilder fastReportBuilder;

	private DynamicReport dynamicReport;

	public GeneratorDataTableReport(final List<T> data, final InfoReport infoReport) {
		Objects.requireNonNull(data, "data não deve ser null");
		Objects.requireNonNull(infoReport, "infoReport não deve ser null");
		this.data = data;
		this.infoReport = infoReport;
		fastReportBuilder = new FastReportBuilder();
		initialize();
	}

	private void initialize() {
		addColumnsAndConfigurePageStyle();
		configureStyles();
		dynamicReport = generateDynamicReport();
	}

	/**
	 * Gera o relatório em um array de bytes
	 * para possibilitar a criação de serviços 
	 * de download de relatórios
	 *
	 * @return Optional<byte[]>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public Optional<byte[]> generateReport() {
		return generateReportInBytes();
	}

	/**
	 * Abre o relatório
	 * com o JasperViewer
	 *
	 * @see JasperViewer
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public void openReport() {

		final JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		try {
			final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(
							dynamicReport, new ClassicLayoutManager(), dataSource);

			JasperViewer.viewReport(jasperPrint);

		} catch (JRException e) {
			LOGGER.warning(() -> "openReport(): " + e);
		}
	}

	private Optional<byte[]> generateReportInBytes() {
		final Optional<JasperPrint> jasperPrintOp = getJasperPrint();
		try {
			return jasperPrintOp.isPresent()
							? Optional.of(JasperExportManager
											.exportReportToPdf(jasperPrintOp.get()))
							: Optional.empty();
		} catch (JRException e) {
			LOGGER.warning(() -> "generateReportInBytes(): " + e);
		}
		return Optional.empty();
	}

	private Optional<JasperPrint> getJasperPrint() {

		final JRDataSource dataSource = new JRBeanCollectionDataSource(data);

		try {
			return Optional.of(DynamicJasperHelper.generateJasperPrint(dynamicReport,
							new ClassicLayoutManager(), dataSource));
		} catch (JRException e) {
			LOGGER.warning(() -> "getJasperPrint(): " + e);
		}

		return Optional.empty();
	}

	public Optional<JRXlsExporter> getJrxlsExporter() {
		final Optional<JasperPrint> jasperPrintOp = getJasperPrint();
		return jasperPrintOp.isPresent()
						? Optional.of(getJrxlsExporter(jasperPrintOp.get()))
						: Optional.empty();
	}

	private JRXlsExporter getJrxlsExporter(final JasperPrint jasperPrint) {
		final JRXlsExporter jrXlsExporter = new JRXlsExporter();
		jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		return jrXlsExporter;
	}

	private void configureStyles() {
		configureStyleHeader();
		configureStyleDataTable();
		configureStyleWhenNoData();
	}

	private void configureStyleHeader() {

		final GetStyles getStyles = infoReport.getGetStyles();
		final GetInfoLayout getInfoLayout = infoReport.getGetInfoLayout();

		fastReportBuilder.setTitle(getInfoLayout.getTextTitle())
						.setTitleStyle(getStyles.getStyleTitle())
						.setSubtitle(getInfoLayout.getTextSubtitle())
						.setSubtitleStyle(getStyles.getStyleSubtitle())
						.setBottomMargin(getInfoLayout.getBottomMargin());

		fastReportBuilder.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y,
						AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);

		getInfoLayout.getImageBannerToHeader().ifPresent(
						imageBannerToHeader -> fastReportBuilder.addFirstPageImageBanner(
										imageBannerToHeader.getPath(),
										imageBannerToHeader.getWidth(),
										imageBannerToHeader.getHeight(),
										imageBannerToHeader.getAlignment().getValue(),
										ImageScaleMode.REAL_SIZE));
	}

	private void configureStyleWhenNoData() {

		final WhenNoData whenNoData = infoReport.getGetInfoLayout().getWhenNoData();

		fastReportBuilder.setWhenNoData(whenNoData.getText(),
						infoReport.getGetStyles().getStyleWhenNoData(),
						whenNoData.isShowTitle(), whenNoData.isShowColumnHeader());
	}

	private void configureStyleDataTable() {
		fastReportBuilder
						.setPrintBackgroundOnOddRows(infoReport.getGetInfoLayout()
										.isPrintBackgroundOnOddRows())
						.setUseFullPageWidth(infoReport.getGetInfoLayout()
										.isUseFullPageWidth());
	}

	private DynamicReport generateDynamicReport() {
		return fastReportBuilder.build();
	}

	private void addColumnsAndConfigurePageStyle() {

		final List<DataColumn> dataColumnsOrdered = new GetDataColumnsImpl(
						infoReport.getClassOfData()).get();

		definePageStyle(dataColumnsOrdered.size());

		final Consumer<DataColumn> addColumn = dataColumn -> AddColumnFactory
						.get(fastReportBuilder, dataColumn).addColumn();

		dataColumnsOrdered.stream().forEachOrdered(addColumn);
	}

	private void definePageStyle(final Integer sizeOfData) {

		if( infoReport.getGetInfoLayout().getDecidePageOrientation()
						.isPortrait(sizeOfData) ) {
			fastReportBuilder.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		} else {
			fastReportBuilder.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		}
	}
}
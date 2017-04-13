package br.com.keyworks.generatordatatablereport.generation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import br.com.keyworks.generatordatatablereport.GeneratorDataTableReport;
import br.com.keyworks.generatordatatablereport.contracts.InfoReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public final class ReportXlsWritable<T> implements ReportWritable {

	private static final Logger LOGGER = Logger
					.getLogger(ReportXlsWritable.class.getName());

	private final InfoReport infoReport;

	private final List<T> reportData;

	private final String fileName;

	public ReportXlsWritable(final InfoReport infoReport, final List<T> reportData,
					final String fileName) {
		Objects.requireNonNull(infoReport, "infoReport não deve ser null");
		Objects.requireNonNull(reportData, "reportData não deve ser null");
		Objects.requireNonNull(fileName, "fileName não deve ser null");
		this.infoReport = infoReport;
		this.reportData = reportData;
		this.fileName = fileName;
	}

	@Override
	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	}

	@Override
	public String getExtension() {
		return "xls";
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void writeReport(final HttpServletResponse response) {

		final Optional<JRXlsExporter> jrxlsExporterOp = new GeneratorDataTableReport<>(
						reportData, infoReport).getJrxlsExporter();

		if( jrxlsExporterOp.isPresent() ) {
			final JRXlsExporter jrXlsExporter = jrxlsExporterOp.get();
			jrXlsExporter.setExporterOutput(
							HttpServletResponseOututStreamExporter.of(response));
			try {
				jrXlsExporter.exportReport();
			} catch (JRException e) {
				LOGGER.warning(() -> "writeReport(): " + e);
			}
		}
	}
}
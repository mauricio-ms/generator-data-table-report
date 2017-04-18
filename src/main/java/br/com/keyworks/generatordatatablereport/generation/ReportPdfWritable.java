package br.com.keyworks.generatordatatablereport.generation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import br.com.keyworks.generatordatatablereport.GeneratorDataTableReport;
import br.com.keyworks.generatordatatablereport.contracts.InfoReport;

public final class ReportPdfWritable<T> implements ReportWritable {

	private static final Logger LOGGER = Logger
					.getLogger(ReportPdfWritable.class.getName());

	private final InfoReport infoReport;

	private final List<T> reportData;

	private final String fileName;

	public ReportPdfWritable(final InfoReport infoReport, final List<T> reportData,
					final String fileName) {
		Objects.requireNonNull(infoReport, "infoReport não deve ser null");
		Objects.requireNonNull(reportData, "reportData não deve ser null");
		Objects.requireNonNull(fileName, "fileName não deve ser null");
		this.infoReport = infoReport;
		this.reportData = reportData;
		this.fileName = fileName;
	}

	@Override
	public String getExtension() {
		return "pdf";
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void writeReport(final HttpServletResponse response) throws IOException {

		LOGGER.info(() -> "writeReport PDF");

		final Optional<byte[]> jrxlsExporterOp = new GeneratorDataTableReport<>(
						reportData, infoReport).generateReport();

		if( jrxlsExporterOp.isPresent() ) {
			LOGGER.info(() -> "Report PDF generated");
			final OutputStream outputStream = response.getOutputStream();
			outputStream.write(jrxlsExporterOp.get());
			LOGGER.info(() -> "Report PDF writed to Stream");
		}
	}
}
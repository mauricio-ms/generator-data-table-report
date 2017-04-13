package br.com.keyworks.generatordatatablereport.generation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.export.OutputStreamExporterOutput;

public final class HttpServletResponseOututStreamExporter
				implements OutputStreamExporterOutput {

	private static final Logger LOGGER = Logger
					.getLogger(HttpServletResponseOututStreamExporter.class.getName());

	private final HttpServletResponse httpServletResponse;

	private HttpServletResponseOututStreamExporter(
					final HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public static HttpServletResponseOututStreamExporter of(
					final HttpServletResponse httpServletResponse) {
		Objects.requireNonNull(httpServletResponse,
						"httpServletResponse não deve ser null");
		return new HttpServletResponseOututStreamExporter(httpServletResponse);
	}

	@Override
	public OutputStream getOutputStream() {
		try {
			return httpServletResponse.getOutputStream();
		} catch (IOException e) {
			LOGGER.warning(() -> "getOutputStream(): " + e);
		}
		return null;
	}

	@Override
	public void close() {
		final OutputStream outputStream = getOutputStream();
		if( outputStream != null ) {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				LOGGER.warning(() -> "close()" + e);
			}
		}
	}

}
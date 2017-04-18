package br.com.keyworks.generatordatatablereport.generation;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import br.com.keyworks.generatordatatablereport.utils.DownloadFileFormatter;

public interface ReportWritable {

	public default String getContentType() {
		return "application/".concat(getExtension());
	}

	public abstract String getExtension();

	public abstract String getFileName();

	public default String getTransferEncoding() {
		return "chunked";
	}

	public default void write(final HttpServletResponse response) throws IOException {
		response.addHeader("Content-Disposition",
						("attachment; filename=").concat(
										DownloadFileFormatter.formatFileNameWithExtension(
														getFileName(), getExtension())));
		response.addHeader("Transfer-Encoding", getTransferEncoding());
		response.setContentType(getContentType());
		writeReport(response);
		close(response);
	}

	public abstract void writeReport(final HttpServletResponse response)
					throws IOException;

	public default void close(final HttpServletResponse response) throws IOException {
		final OutputStream outputStream = response.getOutputStream();
		if( outputStream != null ) {
			outputStream.flush();
			outputStream.close();
		}
	}
}
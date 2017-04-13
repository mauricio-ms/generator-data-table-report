package br.com.keyworks.generatordatatablereport.utils;

public final class DownloadFileFormatter {

	private DownloadFileFormatter() {
	}

	public static String formatFileNameWithExtension(final String fileName,
					final String fileExtension) {
		return fileName.indexOf(fileExtension) == -1
						? fileName.concat(".").concat(fileExtension) : fileName;
	}
}
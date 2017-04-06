package br.com.keyworks.generatordatatablereport.layout;

import java.util.Objects;

/**
 * Classe para representar uma imagem 
 * de um relatório
 * 
 * @see ImageAlignment
 *
 * @author mauricio.scopel
 *
 * @since 4 de jan de 2017
 */
public class ImageBanner {

	private final String path;

	private final ImageAlignment alignment;

	private final Integer width;

	private final Integer height;

	public ImageBanner(final String path, final ImageAlignment alignment,
					final Integer width, final Integer height) {

		Objects.requireNonNull(path, "path não deve ser null");
		Objects.requireNonNull(alignment, "alignment não deve ser null");
		Objects.requireNonNull(width, "width não deve ser null");
		Objects.requireNonNull(height, "height não deve ser null");
		this.path = path;
		this.alignment = alignment;
		this.width = width;
		this.height = height;
	}

	public String getPath() {
		return path;
	}

	public ImageAlignment getAlignment() {
		return alignment;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}
}
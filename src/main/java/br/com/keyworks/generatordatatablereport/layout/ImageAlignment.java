package br.com.keyworks.generatordatatablereport.layout;

/**
 * Enum para representar os possíveis 
 * alinhamentos de uma imagem em um relatório
 * 
 * @see ImageBanner
 *
 * @author mauricio.scopel
 *
 * @since 4 de jan de 2017
 */
public enum ImageAlignment {

	ALIGN_LEFT((byte) 0), ALIGN_RIGHT((byte) 1), ALIGN_CENTER((byte) 2);

	private final byte value;

	private ImageAlignment(final byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}
}
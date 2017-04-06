package br.com.keyworks.generatordatatablereport.layout;

import java.util.Objects;
import br.com.keyworks.generatordatatablereport.contracts.GetInfoLayout;

/**
 * Classe para customizar
 * o layout do relatório
 * quando não há dados 
 * para ser exibidos
 * 
 * @see GetInfoLayout
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public final class WhenNoData {

	private final String text;

	private final boolean showTitle;

	private final boolean showColumnHeader;

	public WhenNoData(final String text, final boolean showTitle,
					final boolean showColumnHeader) {
		Objects.requireNonNull(text, "text não deve ser null");
		Objects.requireNonNull(showTitle, "showTitle não deve ser null");
		Objects.requireNonNull(showColumnHeader, "showColumnHeader não deve ser null");
		this.text = text;
		this.showTitle = showTitle;
		this.showColumnHeader = showColumnHeader;
	}

	public String getText() {
		return text;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public boolean isShowColumnHeader() {
		return showColumnHeader;
	}
}
package br.com.keyworks.generatordatatablereport.implementations;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import br.com.keyworks.generatordatatablereport.contracts.GetStyles;

/**
 * Implementação básica de {@link GetStyles}
 * 
 * Criada para ser utilizada caso 
 * o cliente não queira implementar 
 * uma personalizada
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public class GetStylesSimple implements GetStyles {

	private final Style styleTitleAndSubtitle;

	public GetStylesSimple() {
		this.styleTitleAndSubtitle = new StyleBuilder(true)
						.setFont(Font.ARIAL_MEDIUM_BOLD)
						.setHorizontalAlign(HorizontalAlign.RIGHT)
						.setVerticalAlign(VerticalAlign.TOP).setPaddingTop(0).build();
	}

	@Override
	public Style getStyleTitle() {
		return styleTitleAndSubtitle;
	}

	@Override
	public Style getStyleSubtitle() {
		return styleTitleAndSubtitle;
	}

	@Override
	public Style getStyleWhenNoData() {
		return new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER)
						.setVerticalAlign(VerticalAlign.MIDDLE).build();
	}
}
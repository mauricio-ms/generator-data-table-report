package br.com.keyworks.generatordatatablereport.styles;

import java.awt.Color;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

public final class StyleHeaderFactory {

	private StyleHeaderFactory() {
	}

	public static Style get() {
		final Style headerStyle = new Style();
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.decode("#a6a6a6"));
		headerStyle.setTextColor(Color.WHITE);
		headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		return headerStyle;
	}
}
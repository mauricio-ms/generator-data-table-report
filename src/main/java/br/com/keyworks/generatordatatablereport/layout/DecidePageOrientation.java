package br.com.keyworks.generatordatatablereport.layout;

import java.util.Objects;

/**
 * Implementação para decidir
 * se o relatório é exibido
 * em retrato ou paisagem a partir 
 * do número de colunas
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public final class DecidePageOrientation {

	private Boolean portrait;

	private Integer maxColumnsToBePortrait;

	public DecidePageOrientation(final Boolean portrait) {
		Objects.requireNonNull(portrait, "portrait não deve ser null");
		this.portrait = portrait;
	}

	public DecidePageOrientation(final Integer maxColumnsToBePortrait) {
		Objects.requireNonNull(maxColumnsToBePortrait,
						"maxColumnsToBePortrait não deve ser null");

		if( maxColumnsToBePortrait < 0 ) {
			throw new NullPointerException(
							"maxColumnsToBePortrait não deve ser negativo");
		}

		this.maxColumnsToBePortrait = maxColumnsToBePortrait;
	}

	public Boolean isPortrait() {
		return Boolean.TRUE.equals(portrait);
	}

	public Boolean isPortrait(final Integer columnsOfReport) {
		Objects.requireNonNull(columnsOfReport, "columnsOfReport não deve ser null");
		return isPortrait() || columnsOfReport <= maxColumnsToBePortrait;
	}

	public Boolean isLandscape() {
		return !portrait;
	}
}
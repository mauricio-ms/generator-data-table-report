package br.com.keyworks.generatordatatablereport.contracts;

import ar.com.fdvs.dj.domain.Style;

/**
 * Interface para sobreescrever os estilos 
 * de um relatório
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public interface GetStyles {

	public abstract Style getStyleTitle();

	public abstract Style getStyleSubtitle();

	public abstract Style getStyleWhenNoData();
}
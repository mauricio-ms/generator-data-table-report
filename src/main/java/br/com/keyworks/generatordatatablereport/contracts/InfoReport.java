package br.com.keyworks.generatordatatablereport.contracts;

import br.com.keyworks.generatordatatablereport.implementations.GetInfoLayoutSimple;
import br.com.keyworks.generatordatatablereport.implementations.GetStylesSimple;

/**
 * Interface que deve ser fornecida pelo cliente
 * para gerar o relatório
 *
 * @author mauricio.scopel
 *
 * @since 5 de jan de 2017
 */
public interface InfoReport {

	/**
	 * Tipo de dado a ser exibido 
	 * pelo relatório
	 *
	 * @return Class<?>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract Class<?> getClassOfData();

	/**
	 * Implementação de estilos
	 *
	 * Pode ser utilizada a default
	 * {@link GetStylesSimple}
	 * 
	 * Ou pode-se implementar uma customizada
	 * {@link GetStyles}
	 *
	 * @return GetStyles
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract GetStyles getGetStyles();

	/**
	 * Implementação de layout
	 *
	 * Pode ser utilizada a default
	 * {@link GetInfoLayoutSimple}
	 * 
	 * Ou pode-se implementar uma customizada
	 * {@link GetInfoLayout}
	 *
	 * @return {@link GetInfoLayout}
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract GetInfoLayout getGetInfoLayout();
}
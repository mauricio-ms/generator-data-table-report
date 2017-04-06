package br.com.keyworks.generatordatatablereport.contracts;

import java.util.Optional;
import br.com.keyworks.generatordatatablereport.layout.DecidePageOrientation;
import br.com.keyworks.generatordatatablereport.layout.ImageBanner;
import br.com.keyworks.generatordatatablereport.layout.WhenNoData;

/**
 * Interface para sobreescrever o
 * layout de um relatório
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public interface GetInfoLayout {

	public abstract String getTextTitle();

	public abstract String getTextSubtitle();

	/**
	 * Retornar a margem entre a tabela
	 * e o fim da página
	 *
	 * @return Integer
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract Integer getBottomMargin();

	/**
	 * Retornar a implementação
	 * do comportamento desejado quando 
	 * o relatório não tiver dados para 
	 * serem exibidos
	 * 
	 * @see WhenNoData
	 *
	 * @return WhenNoData
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract WhenNoData getWhenNoData();

	/**
	 * Retornar um Optional com valor para exibir 
	 * uma imagem no cabeçalho
	 *
	 * Para não exibir imagem retornar
	 * {@code Optional.empty()}
	 * 
	 * @see ImageBanner
	 *
	 * @return Optional<ImageBanner>
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract Optional<ImageBanner> getImageBannerToHeader();

	/**
	 * Retornar a implementação do comportamento
	 * de orientação da página
	 *
	 * @see DecidePageOrientation
	 *
	 * @return DecidePageOrientation
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract DecidePageOrientation getDecidePageOrientation();

	/**
	 * Retornar {@code true} para exibir
	 * as linhas com oscilação de cores
	 * 
	 * Retornar {@code false} para exibir
	 * as linhas sem oscilação de cores
	 *
	 * @return Boolean
	 *
	 * @author mauricio.ms
	 *
	 * @since 6 de jan de 2017
	 */
	public abstract Boolean isPrintBackgroundOnOddRows();

	public abstract Boolean isUseFullPageWidth();
}
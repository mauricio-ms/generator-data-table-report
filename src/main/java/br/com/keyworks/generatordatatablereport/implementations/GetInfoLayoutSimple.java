package br.com.keyworks.generatordatatablereport.implementations;

import java.time.LocalDateTime;
import java.util.Optional;
import br.com.keyworks.generatordatatablereport.contracts.GetInfoLayout;
import br.com.keyworks.generatordatatablereport.layout.DecidePageOrientation;
import br.com.keyworks.generatordatatablereport.layout.ImageBanner;
import br.com.keyworks.generatordatatablereport.layout.WhenNoData;
import br.com.keyworks.generatordatatablereport.utils.DateFormatterHelper;

/**
 * Implementação básica de {@link GetInfoLayout}
 * 
 * Criada para ser utilizada caso 
 * o cliente não queira implementar 
 * uma personalizada
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public class GetInfoLayoutSimple implements GetInfoLayout {

	private final String nameReport;

	public GetInfoLayoutSimple(final String nameReport) {
		this.nameReport = nameReport;
	}

	@Override
	public String getTextTitle() {
		return nameReport;
	}

	@Override
	public String getTextSubtitle() {
		return DateFormatterHelper.formatDateTime(LocalDateTime.now());
	}

	@Override
	public Integer getBottomMargin() {
		return 50;
	}

	@Override
	public WhenNoData getWhenNoData() {
		return new WhenNoData("Nenhum registro encontrado", true, false);
	}

	@Override
	public Optional<ImageBanner> getImageBannerToHeader() {
		return Optional.empty();
	}

	@Override
	public DecidePageOrientation getDecidePageOrientation() {
		return new DecidePageOrientation(5);
	}

	@Override
	public Boolean isPrintBackgroundOnOddRows() {
		return true;
	}

	@Override
	public Boolean isUseFullPageWidth() {
		return true;
	}
}
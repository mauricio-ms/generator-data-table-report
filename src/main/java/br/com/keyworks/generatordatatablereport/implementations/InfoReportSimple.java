package br.com.keyworks.generatordatatablereport.implementations;

import java.util.Objects;
import br.com.keyworks.generatordatatablereport.contracts.GetInfoLayout;
import br.com.keyworks.generatordatatablereport.contracts.GetStyles;
import br.com.keyworks.generatordatatablereport.contracts.InfoReport;

/**
 * Implementação básica de {@link InfoReport}
 * 
 * Criada para ser utilizada caso 
 * o cliente não queira implementar 
 * um personalizado
 * 
 * Ou, caso queira extender para retornar
 * apenas algumas coisas personalizadas
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public class InfoReportSimple implements InfoReport {

	private final Class<?> classOfData;

	private final String nameReport;

	public InfoReportSimple(final Class<?> classOfData, final String nameReport) {
		Objects.requireNonNull(classOfData, "classOfData não deve ser null");
		Objects.requireNonNull(nameReport, "nameReport não deve ser null");
		this.classOfData = classOfData;
		this.nameReport = nameReport;
	}
	
	protected String getNameReport() {
		return nameReport;
	}
	
	@Override
	public Class<?> getClassOfData() {
		return classOfData;
	}

	@Override
	public GetStyles getGetStyles() {
		return new GetStylesSimple();
	}

	@Override
	public GetInfoLayout getGetInfoLayout() {
		return new GetInfoLayoutSimple(nameReport);
	}
}
package br.com.keyworks.generatordatatablereport.test.grouped;

import br.com.keyworks.generatordatatablereport.GeneratorDataTableReport;
import br.com.keyworks.generatordatatablereport.implementations.InfoReportSimple;

public final class ViewerGroupedReport {

	private ViewerGroupedReport() {
	}

	public static void main(String[] args) {
		new GeneratorDataTableReport<>(AddressFactory.get(),
						new InfoReportSimple(Address.class, "Report - Address"))
										.openReport();
	}
}
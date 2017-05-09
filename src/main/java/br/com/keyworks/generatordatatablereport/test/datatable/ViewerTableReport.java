package br.com.keyworks.generatordatatablereport.test.datatable;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import br.com.keyworks.generatordatatablereport.GeneratorDataTableReport;
import br.com.keyworks.generatordatatablereport.customexpression.CustomExpressionAbstract;
import br.com.keyworks.generatordatatablereport.customexpression.RegisterCustomExpressions;
import br.com.keyworks.generatordatatablereport.implementations.InfoReportSimple;
import br.com.keyworks.generatordatatablereport.utils.ListHelper;

/**
 * Classe para abrir o relatório como aplicação desktop.
 * 
 * Criada para testar a classe {@link GeneratorDataTableReport}
 *
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
public class ViewerTableReport {

	private ViewerTableReport() {
	}

	public static void main(String[] args) {
		new ViewerTableReport().openReport();
	}

	@SuppressWarnings("serial")
	public void openReport() {

		RegisterCustomExpressions.getInstance().put(Boolean.class,
						new CustomExpressionAbstract() {

							@Override
							public String getClassName() {
								return Boolean.class.getName();
							}

							@SuppressWarnings("rawtypes")
							@Override
							public String getValue(Map fields, Map variables,
											Map parameters) {
								final Boolean val = (Boolean) fields.get(getProperty());

								return BooleanUtils.isTrue(val) ? "YES" : "NO";
							}
						});

		final List<Usuario> usuarios = ListHelper.createN(5L,
						usuario -> new Usuario("<b>João</b>", null,
										new Empresa(new Endereco("Ipê"))));

		new GeneratorDataTableReport<>(usuarios,
						new InfoReportSimple(Usuario.class, "Relatório - Usuários"))
										.openReport();
	}
}
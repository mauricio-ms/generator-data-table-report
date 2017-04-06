package br.com.keyworks.generatordatatablereport.test;

import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;

/**
 * Classe criada apenas para 
 * testar o relatório
 *
 * @see ViewerTableReport
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public class Usuario {

	@ColumnReport(order = 1, whenNoData = "--", html = true)
	private String nome;

	@ColumnReport(label = "Phone", mask = "(##) ####-####", order = 2)
	private String telefone;

	@ColumnReport(label = "Cidade Emp.", value = "endereco.cidade", order = 3)
	private Empresa empresa;

	@ColumnReport
	private Boolean ativo;

	public Usuario(final String nome, final String telefone, final Empresa empresa) {
		this.nome = nome;
		this.telefone = telefone;
		this.empresa = empresa;
		this.ativo = true;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Boolean getAtivo() {
		return ativo;
	}
}
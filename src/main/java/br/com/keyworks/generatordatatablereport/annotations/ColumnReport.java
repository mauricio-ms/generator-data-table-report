package br.com.keyworks.generatordatatablereport.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para marcar algum campo de um modelo
 * como um campo do seu relatório
 * 
 * @author mauricio.scopel
 *
 * @since 30 de dez de 2016
 */
@Target(value = { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnReport {

	/**
	 * Valor exibido no header da 
	 * coluna correspondente no relatório
	 * 
	 * Se estiver sem valor, o nome do campo ou
	 * método anotado com a anotação será utilizado
	 */
	String label() default "";

	/**
	 * Deve ser utilizado em campos 
	 * como CEP, telefone, CPF, etc...
	 * 
	 * Deve receber no formato:
	 * 	###.###.###-## (Exemplo de CEP)
	 */
	String mask() default "";

	/**
	 * Deve ser usado quando se 
	 * deseja navegar em um objeto,
	 * para alcançar alguma propriedade 
	 * do mesmo
	 * 
	 * Se for necessário acessar mais de um valor
	 * de algum objeto, usar {@link List}
	 * passando uma lista de {@link ColumnReport}
	 */
	String value() default "";

	/**
	 * Deve ser usado para setar algum valor 
	 * quando a coluna correspondente não possuir 
	 * valor em alguma das linhas do relatório
	 */
	String whenNoData() default "-";

	/**
	 * Deve ser utilizado para controlar
	 * a ordem de exibição das colunas no relatório
	 * 
	 * Podendo ser utilizado quaisquer números, o que 
	 * importa é só a sua ordem
	 * 
	 * Ex: -1,-2,-3,-4,-5
	 * 
	 * Ex: 1,2,3,4,5
	 */
	int order() default 0;

	/**
	 * Deve ser utilizado quando para as linhas 
	 * (não para o HEADER), se desejar exibir em html
	 * 
	 * ** Lembrando que o suporte a tags é
	 * 		o mesmo do Jasper
	 */
	boolean html() default false;

	/**
	 * Define várias {@code @ColumnReport}
	 */
	@Target(value = { ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface List {
		ColumnReport[] value();
	}
}
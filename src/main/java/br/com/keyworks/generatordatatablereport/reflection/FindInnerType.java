package br.com.keyworks.generatordatatablereport.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import br.com.keyworks.generatordatatablereport.utils.BeanUtils;

/**
 * Classe para descobrir o tipo
 * de um navegando em um objeto
 * 
 * Suas dependências são:
 * 
 * Class: O tipo original ao qual 
 * se deseja navegar
 *
 * Value: Uma String com os campos que
 * se deseja navegar, separados por '.'
 * 
 * @apiNote Se algum campo do value for método
 * deve ser passado sem o get
 * 
 * Exemplo:
 *
 * <p>
   static class A {

		private Long prop1;

		private B b;

		public A() {
			b = new B();
		}
	}

	static class B {
		private String prop1;

		private C c;

		public B() {
			prop1 = "valueProp1";
		}
	}

	static class C {
		private Boolean prop1;

		private A a;

		public C() {
			prop1 = true;
		}
	}
	
	A chamada
		new FindInnerType()
			.getByField(A.class.getDeclaredField("b").getClass(), "c.a.prop1");
			
			deve retornar Long
	</p>
 * 
 * @see FindInnerTypeTest
 *
 * @author mauricio.scopel
 *
 * @since 4 de jan de 2017
 */
public class FindInnerType {

	private final Class<?> parentType;

	private final String property;

	public FindInnerType(final Class<?> parentType, final String property) {
		this.parentType = parentType;
		this.property = property;
	}

	public Class<?> get() {
		return getType(parentType, property);
	}

	private Optional<Class<?>> extractClass(
					final Function<String, Optional<Field>> getFieldOpByName,
					final Function<String, Optional<Method>> getMethodOpByName,
					final String propertyName) {

		final Optional<Field> fieldOp = getFieldOpByName.apply(propertyName);

		if( fieldOp.isPresent() ) {
			return Optional.of(fieldOp.get().getType());
		}

		final Optional<Method> methodOp = getMethodOpByName.apply(propertyName);

		return methodOp.isPresent() ? Optional.of(methodOp.get().getReturnType())
						: Optional.empty();
	}

	/**
	 * Função que cria duas funções aninhadas,
	 * onde uma busca um campo a partir do tipo
	 * e do nome da propriedade,
	 * e outra busca um método a partir do tipo
	 * e do nome da propriedade
	 *
	 * @param clazz
	 * @param propertyName
	 *
	 * @return Optional<Class<?>>
	 * 	Um Optional com o tipo encontrado
	 *
	 * @author mauricio.ms
	 *
	 * @since 4 de jan de 2017
	 */
	private Optional<Class<?>> getTypeByAllProperties(final Class<?> clazz,
					final String propertyName) {

		final Function<String, Optional<Field>> getFieldOpByName = fieldName -> Stream
						.of(clazz.getDeclaredFields())
						.filter(field -> field.getName().equals(fieldName)).findFirst();

		final Function<String, Optional<Method>> getMethodOpByName = methodName -> Stream
						.of(clazz.getMethods())
						.filter(method -> BeanUtils
										.getMethodNameWithouGet(method.getName())
										.equals(methodName))
						.findFirst();

		return extractClass(getFieldOpByName, getMethodOpByName, propertyName);
	}

	/**
	 * Função recursiva para retornar o tipo final
	 * baseado em uma String no formato propA.propB.propA.name
	 *
	 * @param parentType
	 * @param property
	 *
	 * @return Class<?>
	 * 	Tipo da última propriedade, se não encontrar retorna {@code null}
	 *
	 * @author mauricio.ms
	 *
	 * @since 4 de jan de 2017
	 */
	private Class<?> getType(final Class<?> parentType, final String property) {

		if( StringUtils.isBlank(property) ) {
			return parentType;
		}

		final char delimiter = '.';
		final int indexOf = property.indexOf(delimiter);

		final String propertyName = indexOf != -1 ? property.substring(0, indexOf)
						: property;

		final Optional<Class<?>> propertyTypeOp = getTypeByAllProperties(parentType,
						propertyName);

		final String nameOfNextProperty = indexOf != -1 ? property.substring(indexOf + 1)
						: "";

		return propertyTypeOp.isPresent()
						? getType(propertyTypeOp.get(), nameOfNextProperty) : null;
	}
}
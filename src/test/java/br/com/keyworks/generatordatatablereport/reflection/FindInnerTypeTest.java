package br.com.keyworks.generatordatatablereport.reflection;

import org.junit.Assert;
import org.junit.Test;
import br.com.keyworks.generatordatatablereport.reflection.FindInnerType;

public class FindInnerTypeTest {

	static class A {

		@SuppressWarnings("unused")
		private Long prop1;

		@SuppressWarnings("unused")
		private Float prop2;

		@SuppressWarnings("unused")
		private B b;

		public A() {
			b = new B();
		}
	}

	static class B {

		@SuppressWarnings("unused")
		private String prop1;

		private C c;

		public B() {
			prop1 = "valueProp1";
		}

		public C getInstanciaC() {
			return c;
		}
	}

	static class C {

		@SuppressWarnings("unused")
		private Boolean prop1;

		private A a;

		public C() {
			prop1 = true;
		}

		public A getInstanciaA() {
			return a;
		}
	}

	@Test
	public void navigateIntoTwoProperties()
					throws NoSuchFieldException, SecurityException {
		Assert.assertEquals(Long.class,
						new FindInnerType(A.class.getDeclaredField("b").getType(),
										"c.a.prop1").get());
	}

	@Test
	public void navigateIntoOneMethodAndOneProperty()
					throws NoSuchFieldException, SecurityException {
		Assert.assertEquals(Boolean.class,
						new FindInnerType(A.class.getDeclaredField("b").getType(),
										"instanciaC.prop1").get());
	}

	@Test
	public void navigateIntoTwoMethodsAndOneProperty()
					throws NoSuchFieldException, SecurityException {
		Assert.assertEquals(Float.class,
						new FindInnerType(A.class.getDeclaredField("b").getType(),
										"instanciaC.instanciaA.prop2").get());
	}
}
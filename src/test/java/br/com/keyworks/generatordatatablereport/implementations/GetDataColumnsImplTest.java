package br.com.keyworks.generatordatatablereport.implementations;

import org.junit.Assert;
import org.junit.Test;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;
import br.com.keyworks.generatordatatablereport.implementations.GetDataColumnsImpl;

public class GetDataColumnsImplTest {

	class A {

		@ColumnReport
		private String a;
	}

	class B {

		@ColumnReport
		private String a;

		@ColumnReport
		private String b;
	}

	class C {

		@ColumnReport.List(@ColumnReport)
		private String a;
	}

	class D {

		@ColumnReport.List({ @ColumnReport, @ColumnReport })
		private String a;
	}

	class E {

		@ColumnReport.List({ @ColumnReport, @ColumnReport })
		private String method() {
			return "";
		}
	}

	@Test
	public void getOneSimpleByProperty() {
		Assert.assertEquals(1, new GetDataColumnsImpl(A.class).get().size());
	}

	@Test
	public void getTwoSimplesByProperty() {
		Assert.assertEquals(2, new GetDataColumnsImpl(B.class).get().size());
	}

	@Test
	public void getOneComposedByProperty() {
		Assert.assertEquals(1, new GetDataColumnsImpl(C.class).get().size());
	}

	@Test
	public void getTwoComposedsByProperty() {
		Assert.assertEquals(2, new GetDataColumnsImpl(D.class).get().size());
	}

	@Test
	public void getTwoComposedsByMethod() {
		Assert.assertEquals(2, new GetDataColumnsImpl(E.class).get().size());
	}
}
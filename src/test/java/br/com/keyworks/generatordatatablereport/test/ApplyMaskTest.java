package br.com.keyworks.generatordatatablereport.test;

import org.junit.Assert;
import org.junit.Test;
import br.com.keyworks.generatordatatablereport.mask.ApplyMask;

public class ApplyMaskTest {

	@Test
	public void naoDeveRetornarValorComMascara() {
		Assert.assertTrue(!new ApplyMask("887164372", "###.###.###-##").getWithMask()
						.isPresent());
	}

	@Test
	public void deveRetornarValorComMascara() {
		Assert.assertTrue(new ApplyMask("88716437209", "###.###.###-##").getWithMask()
						.isPresent());
	}
}
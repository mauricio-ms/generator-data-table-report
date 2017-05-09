package br.com.keyworks.generatordatatablereport.test.grouped;

import java.util.List;
import br.com.keyworks.generatordatatablereport.utils.ListHelper;

public final class AddressFactory {

	private AddressFactory() {
	}

	public static List<Address> get() {
		return ListHelper.immutableOf(
						Address.of("Rio Grande do Sul", "Caxias do Sul", 450000),
						Address.of("Rio Grande do Sul", "Ipê", 8000),
						Address.of("Rio Grande do Sul", "Ipê", 8000),
						Address.of("Rio Grande do Sul", "Bagé", 8000),
						Address.of("Rio De Janeiro", "Jaraguá", 55000),
						Address.of("São Paulo", "Cotia", 25000),
						Address.of("São Paulo", "Mogi Mirim", 75000));
	}
}

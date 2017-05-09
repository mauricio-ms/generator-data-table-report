package br.com.keyworks.generatordatatablereport.test.grouped;

import java.util.Objects;
import br.com.keyworks.generatordatatablereport.annotations.ColumnReport;

public final class Address {

	@ColumnReport(order = 1, group = true)
	private final String state;

	@ColumnReport(order = 2)
	private final String city;

	@ColumnReport(order = 3)
	private final Integer numberOfInhabitants;

	@ColumnReport(order = 4)
	private final Boolean active;

	private Address(final String state, final String city,
					final Integer numberOfInhabitants) {
		this.state = state;
		this.city = city;
		this.numberOfInhabitants = numberOfInhabitants;
		active = true;
	}

	public static Address of(final String state, final String city,
					final Integer numberOfInhabitants) {
		Objects.requireNonNull(state, "state shouldn't be null");
		Objects.requireNonNull(city, "city shouldn't be null");
		Objects.requireNonNull(numberOfInhabitants,
						"numberOfInhabitants shouldn't be null");
		return new Address(state, city, numberOfInhabitants);
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public Integer getNumberOfInhabitants() {
		return numberOfInhabitants;
	}

	public Boolean getActive() {
		return active;
	}

	@Override
	public String toString() {
		return "Address [state=" + state + ", city=" + city + ", numberOfInhabitants="
						+ numberOfInhabitants + "]";
	}
}
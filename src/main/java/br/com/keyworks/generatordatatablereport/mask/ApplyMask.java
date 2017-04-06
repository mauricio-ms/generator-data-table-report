package br.com.keyworks.generatordatatablereport.mask;

import java.util.Objects;
import java.util.Optional;

/**
 * Classe para aplicar qualquer máscara,
 * a ideia é passar um valor sem máscara
 * e uma máscara utilizando o caractere #
 * 
 * Por exemplo para cpf:
 * ###.###.###-##
 *
 * @author mauricio.scopel
 *
 * @since 2 de jan de 2017
 */
public final class ApplyMask {

	private final String valueWithoutMask;
	private final String mask;

	public ApplyMask(final String valueWithoutMask, final String mask) {
		Objects.requireNonNull(valueWithoutMask, "valueWithoutMask não deve ser null");
		Objects.requireNonNull(mask, "mask não deve ser null");
		this.valueWithoutMask = removeMask(valueWithoutMask);
		this.mask = mask;
	}

	private String removeMask(final String maskedValue) {
		return maskedValue == null ? null : maskedValue.replaceAll("[^a-zA-Z0-9#]", "");
	}

	/**
	 * Retorna o valor com a máscara aplicada
	 *
	 * @return String
	 *
	 * @author mauricio.ms
	 *
	 * @since 2 de jan de 2017
	 */
	public Optional<String> getWithMask() {

		if( isMaskCorrectForValue() ) {
			return Optional.empty();
		}

		int countCharsMask = 0;
		String valueWithMask = mask;
		for (int i = 0; i < mask.length(); i++) {

			if( mask.charAt(i) == '#' ) {
				valueWithMask = valueWithMask.replaceFirst("#", String
								.valueOf(valueWithoutMask.charAt(i - countCharsMask)));
			} else {
				countCharsMask++;
			}
		}

		return Optional.of(valueWithMask);
	}

	private boolean isMaskCorrectForValue() {
		final String removeMask = removeMask(mask);
		final int lengthOfValueWithouMask = removeMask != null ? removeMask.length() : -1;
		return valueWithoutMask.length() != lengthOfValueWithouMask;
	}
}
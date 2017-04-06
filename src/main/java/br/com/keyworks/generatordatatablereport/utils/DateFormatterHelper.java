package br.com.keyworks.generatordatatablereport.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para 
 * formatação de datas
 *
 * @author mauricio.scopel
 *
 * @since 6 de jan de 2017
 */
public final class DateFormatterHelper {

	private DateFormatterHelper() {
	}

	public static String formatDateAsString(LocalDateTime localDateTime, String pattern) {
		return formatDate(localDateTime, pattern);
	}

	public static String formatDateAsString(LocalDate localDate, String pattern) {
		return formatDate(localDate, pattern);
	}

	public static String formatToSystemZone(OffsetDateTime zonedDatetime,
					String pattern) {
		return zonedDatetime
						.withOffsetSameInstant(ZoneId.systemDefault().getRules()
										.getOffset(Instant.now()))
						.format(DateTimeFormatter.ofPattern(pattern));
	}

	private static String formatDate(LocalDate localDate, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDate.format(formatter);
	}

	private static String formatDate(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	public static String formatDate(LocalDateTime localDateTime) {
		return formatDate(localDateTime, "dd/MM/yyyy");
	}

	public static String formatDate(LocalDate localDate) {
		return formatDate(localDate, "dd/MM/yyyy");
	}

	public static String formatTime(LocalDateTime localDateTime) {
		return formatDate(localDateTime, "HH:mm:ss");
	}

	public static String formatDateTime(LocalDateTime localDateTime) {
		return String.format("%s - %s", formatDate(localDateTime),
						formatTime(localDateTime));
	}
}
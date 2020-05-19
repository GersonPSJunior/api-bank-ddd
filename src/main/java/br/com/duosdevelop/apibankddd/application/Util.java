package br.com.duosdevelop.apibankddd.application;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Util {

    public static final DateTimeFormatter MEDIUM_DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
}

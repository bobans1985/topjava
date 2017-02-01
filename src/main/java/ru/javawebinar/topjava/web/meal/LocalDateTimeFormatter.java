package ru.javawebinar.topjava.web.meal;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by boban on 01.02.2017.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd'T'HH:mm");
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
       LocalDateTime out = StringUtils.isEmpty(text) ? LocalDateTime.now() : LocalDateTime.parse(text, DATE_TIME_FORMATTER);
        return out;
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object == null ? "" : object.format(DATE_TIME_FORMATTER);
    }
}

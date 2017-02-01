package ru.javawebinar.topjava.web.meal;

import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Created by boban on 31.01.2017.
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return DateTimeUtil.parseLocalDateTime(source);
    }

}

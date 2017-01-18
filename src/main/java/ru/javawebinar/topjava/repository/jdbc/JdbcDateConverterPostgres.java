package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * Created by boban on 17.01.2017.
 */
@Component
@Profile("postgres")
public class JdbcDateConverterPostgres implements JdbcDateConverter<LocalDateTime>{

    public LocalDateTime convert(LocalDateTime ldt) {
        return ldt;
    }
}

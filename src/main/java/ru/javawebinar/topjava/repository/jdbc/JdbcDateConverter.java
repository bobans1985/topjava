package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

/**
 * Created by boban on 17.01.2017.
 */
@NoRepositoryBean
public interface JdbcDateConverter<T> {
    public T convert(LocalDateTime ldt);
}

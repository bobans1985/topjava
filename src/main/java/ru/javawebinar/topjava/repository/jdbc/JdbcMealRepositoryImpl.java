package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbc;

    private SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",meal.getId());
        parameterSource.addValue("userid",userId);
        parameterSource.addValue("datetime",meal.getDateTime());
        parameterSource.addValue("description",meal.getDescription());
        parameterSource.addValue("calories",meal.getCalories());
        if (meal.isNew()) {
            Number id = insertMeal.executeAndReturnKey(parameterSource);
            meal.setId(id.intValue());
        }  else {
            namedParameterJdbc.update("UPDATE meals SET userid=:userid, datetime=:datetime, description=:description, calories=:calories WHERE id=:id",parameterSource);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM MEALS WHERE id=? AND userid=?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT ID,DATETIME,DESCRIPTION,CALORIES FROM MEALS WHERE ID=? AND USERID=? ", ROW_MAPPER, id, userId);
        return meals.stream().findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM MEALS WHERE userid=? ORDER BY datetime DESC", new RowMapper<Meal>() {
            @Override
            public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Meal(rs.getInt("id"),rs.getTimestamp("datetime").toLocalDateTime(),rs.getString("description"),rs.getInt("calories"));
            }
        }, userId);
        return meals;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userid",userId);
        parameterSource.addValue("startDate",startDate);
        parameterSource.addValue("endDate",endDate);
        List<Meal> meals = namedParameterJdbc.query("SELECT ID,DATETIME,DESCRIPTION,CALORIES FROM MEALS WHERE userid=:userid and datetime>=:startDate and datetime<=:endDate ORDER BY datetime DESC",parameterSource,ROW_MAPPER);
        return meals;
    }
}

package com.tabeldata.example.dao;

import com.tabeldata.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@Repository
@Transactional(readOnly = true)
public class SecurityDao {

    @Autowired
    private NamedParameterJdbcTemplate paramsJdbcTemplate;

    public User findUserByUsername(String username) throws EmptyResultDataAccessException, SQLException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);
        StringBuilder query = new StringBuilder(
                "select         u.id                as user_id,\n" +
                        "       u.username          as username,\n" +
                        "       u.email             as email_address,\n" +
                        "       u.password          as password,\n" +
                        "       u.is_enabled        as is_enabled,\n" +
                        "       u.is_sudo           as is_sudo,\n" +
                        "       u.is_locked         as is_locked,\n" +
                        "       u.created_by        as created_by,\n" +
                        "       u.created_date      as created_date,\n" +
                        "       u.last_updated_by   as last_update_by,\n" +
                        "       u.last_updated_date as last_update_date\n" +
                        "from user_management.users u\n" +
                        "where u.username = :username"
        );
        return this.paramsJdbcTemplate.queryForObject(query.toString(), params, new UserRowMapper());
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("username"),
                    resultSet.getString("email_address"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_enabled"),
                    resultSet.getBoolean("is_locked"),
                    resultSet.getBoolean("is_sudo"),
                    resultSet.getString("created_by"),
                    resultSet.getTimestamp("created_date"),
                    resultSet.getString("last_update_by"),
                    resultSet.getTimestamp("last_update_date"),
                    Arrays.asList()
            );
        }
    }

}

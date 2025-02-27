package com.qucoon.hospitalmanagement.mapper;


import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericRowMapper<T> implements RowMapper<T> {
    private final Class<T> type;

    public GenericRowMapper(Class<T> type) {
        this.type = type;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            T instance = type.getDeclaredConstructor().newInstance();

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = field.getName();

                try {
                    Object value = rs.getObject(columnName);
                    if (value != null) {
                        field.set(instance, value);
                    }
                } catch (SQLException e) {
                    // Column not found, ignore
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping row to " + type.getSimpleName(), e);
        }
    }
}



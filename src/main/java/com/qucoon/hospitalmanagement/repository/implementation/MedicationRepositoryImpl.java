package com.qucoon.hospitalmanagement.repository.implementation;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(EmployeeQuery.GET_ALL, new EmployeeRowMapper());
    }
}

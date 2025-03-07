package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.StaffMapper;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.StaffRepository;
import com.qucoon.hospitalmanagement.repository.query.StaffQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Staff createStaff(Staff staff) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffFirstName", staff.getStaffFirstName())
                .addValue("staffLastName", staff.getStaffLastName())
                .addValue("staffEmail", staff.getStaffEmail())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffSalary", staff.getStaffSalary())
                .addValue("staffPhoneNumber", staff.getStaffPhoneNumber())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffHireDate", staff.getStaffHireDate());

        jdbcTemplate.update(StaffQuery.INSERT_STAFF, params, keyHolder, new String[]{"staffId"});

        // Retrieve the generated staffId
        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            staff.setStaffId(generatedId.intValue()); // Set the last inserted ID to staff object
        }

        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        return jdbcTemplate.query(StaffQuery.GET_ALL_STAFF, new StaffMapper());
    }

    @Override
    public Staff getStaffById(int staffId) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource("staffId", staffId);
            return jdbcTemplate.queryForObject(StaffQuery.GET_STAFF_BY_ID, params, new StaffMapper());
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int updateStaff(Staff Staff, int staffId) {
        try{
//            MapSqlParameterSource params = new MapSqlParameterSource("staffId", staffId);
//            return jdbcTemplate.queryForObject(StaffQuery., params, new StaffMapper());
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("staffId", staffId)
                    .addValue("staffFirstName",Staff.getStaffFirstName())
                    .addValue("staffLastName",Staff.getStaffLastName())
                    .addValue("staffEmail",Staff.getStaffEmail())
                    .addValue("staffRole",Staff.getStaffRole())
                    .addValue("staffSalary",Staff.getStaffSalary())
                    .addValue("staffPhoneNumber",Staff.getStaffPhoneNumber())
                    .addValue("staffDepartment",Staff.getStaffDepartment())
                    .addValue("staffHireDate",Staff.getStaffHireDate())
                    .addValue("staffStatus",Staff.getStaffStatus());

            var query = StaffQuery.buildUpdateQuery(Staff, String.valueOf(staffId));
            return jdbcTemplate.update(query, params);
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteStaff(int staffId) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource("staffId", staffId);
            return jdbcTemplate.update(StaffQuery.DELETE_STAFF, params);
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return 0;
        }
    }
}

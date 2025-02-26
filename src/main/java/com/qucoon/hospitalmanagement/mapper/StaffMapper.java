package com.qucoon.hospitalmanagement.mapper;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StaffMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Staff.builder()
                .staffId(rs.getInt("staffId"))
                .staffFirstName(rs.getString("staffFirstName"))
                .staffLastName(rs.getString("staffLastName"))
                .staffRole(rs.getString("staffRole"))
                .staffDepartment(rs.getString("staffDepartment"))
                .staffPhoneNumber(rs.getString("staffPhoneNumber"))
                .staffEmail(rs.getString("staffEmail"))
                .staffSalary(rs.getDouble("staffSalary"))
                .staffHireDate(rs.getString("staffHireDate"))
                .staffStatus(rs.getString("staffStatus"))
                .staffCreatedAt(rs.getString("staffCreatedAt"))
                .staffUpdatedAt(rs.getString("staffUpdatedAt"))
                .build();
    }
}

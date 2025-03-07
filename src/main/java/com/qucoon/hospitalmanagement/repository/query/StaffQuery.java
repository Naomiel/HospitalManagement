package com.qucoon.hospitalmanagement.repository.query;

import com.qucoon.hospitalmanagement.model.entity.Staff;

import java.util.HashMap;
import java.util.Map;

public class StaffQuery {

    public static final String INSERT_STAFF = """
    INSERT INTO Staff (staffFirstName, staffLastName, staffRole, staffDepartment, staffPhoneNumber, staffEmail, staffSalary, staffHireDate, staffStatus, staffCreatedAt, staffUpdatedAt) 
    VALUES (:staffFirstName, :staffLastName, :staffRole, :staffDepartment, :staffPhoneNumber, :staffEmail, :staffSalary, :staffHireDate, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
            
    """;

    public static final String GET_ALL_STAFF = "SELECT * FROM Staff where staffStatus != 'deleted'";

    public static final String GET_STAFF_BY_ID = "SELECT * FROM Staff WHERE staffId = :staffId and staffStatus != 'deleted'";

//    public static final String UPDATE_STAFF = """
//    UPDATE Staff
//    SET staffFirstName = :staffFirstName, staffLastName = :staffLastName, staffRole = :staffRole, staffDepartment = :staffDepartment,
//        staffPhoneNumber = :staffPhoneNumber, staffEmail = :staffEmail, staffSalary = :staffSalary, staffHireDate = :staffHireDate,
//        staffStatus = :staffStatus, staffUpdatedAt = CURRENT_TIMESTAMP
//    WHERE staffId = :staffId
//    """;

    public static final String DELETE_STAFF = "Update Staff set staffStatus = 'deleted' WHERE staffId = :staffId";

    public static String buildUpdateQuery(Staff request, String staff_id) {
        StringBuilder query = new StringBuilder("UPDATE Staff SET ");
        Map<String, Object> params = new HashMap<>();

        if (request.getStaffFirstName() != null) {
            query.append("staffFirstName = :staffFirstName, ");
            params.put("staffFirstName", request.getStaffFirstName());
        }
        if (request.getStaffLastName() != null) {
            query.append("staffLastName = :staffLastName, ");
            params.put("staffLastName", request.getStaffLastName());
        }
        if (request.getStaffRole() != null) {
            query.append("staffRole = :staffRole, ");
            params.put("staffRole", request.getStaffRole());
        }
        if (request.getStaffDepartment() != null) {
            query.append("staffDepartment = :staffDepartment, ");
            params.put("staffDepartment", request.getStaffDepartment());
        }
        if (request.getStaffPhoneNumber() != null) {
            query.append("staffPhoneNumber = :staffPhoneNumber, ");
            params.put("staffPhoneNumber", request.getStaffPhoneNumber());
        }
        if (request.getStaffEmail() != null) {
            query.append("staffEmail = :staffEmail, ");
            params.put("staffEmail", request.getStaffEmail());
        }
        if (request.getStaffSalary() != null) {
            query.append("staffSalary = :staffSalary, ");
            params.put("staffSalary", request.getStaffSalary());
        }
        if (request.getStaffHireDate() != null) {
            query.append("staffHireDate = :staffHireDate, ");
            params.put("staffHireDate", request.getStaffHireDate());
        }
        if (request.getStaffStatus() != null) {
            query.append("staffStatus = :staffStatus, ");
            params.put("staffStatus", request.getStaffStatus());
        }

        // Always update timestamp
        query.append("staffUpdatedAt = CURRENT_TIMESTAMP ");

        // Add WHERE condition
        query.append("WHERE staffId = :staffId");
        params.put("staffId", staff_id);

        return query.toString();
    }

}

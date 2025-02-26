package com.qucoon.hospitalmanagement.repository.Interface;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;

import java.util.List;

public interface StaffRepository {
    int createStaff(Staff staff);
    List<Staff> getAllStaff();
    Staff getStaffById(int staffId);
    int updateStaff(Staff Staff, int staffId);
    int deleteStaff(int staffId);
}

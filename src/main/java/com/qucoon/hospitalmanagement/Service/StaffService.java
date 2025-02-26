package com.qucoon.hospitalmanagement.Service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import com.qucoon.hospitalmanagement.model.request.StaffCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import com.qucoon.hospitalmanagement.repository.Interface.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public int createStaff(StaffCreateRequest staffCreateRequest) {
        Gson gson = new Gson();
        var staff = gson.fromJson(gson.toJson(staffCreateRequest), Staff.class);
        return staffRepository.createStaff(staff);
    }

    public List<Staff> getAllStaff(){
        return staffRepository.getAllStaff();
    }

    public Staff getStaffById(int staffId) {
        try
        {
            return staffRepository.getStaffById(staffId);
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());

        return null;
        }
    }

    public int updateStaff(StaffUpdateRequest staffUpdateRequest, int staffId) {
        Gson gson = new Gson();
        var staff = gson.fromJson(gson.toJson(staffUpdateRequest), Staff.class);
        return staffRepository.updateStaff(staff, staffId);
    }
    public int deleteStaff(int staffId) {
        return staffRepository.deleteStaff(staffId);
    }

}

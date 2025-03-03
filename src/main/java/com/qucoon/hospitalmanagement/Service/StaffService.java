package com.qucoon.hospitalmanagement.Service;


import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Staff;
import com.qucoon.hospitalmanagement.model.request.StaffCreateRequest;
import com.qucoon.hospitalmanagement.model.request.StaffUpdateRequest;
import com.qucoon.hospitalmanagement.model.response.StaffResponse;
import com.qucoon.hospitalmanagement.repository.Interface.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public StaffResponse createStaff(StaffCreateRequest staffCreateRequest) {
        Gson gson = new Gson();
        var request = gson.fromJson(gson.toJson(staffCreateRequest), Staff.class);
        try
        {
            var staff = staffRepository.createStaff(request);
//            System.out.println(staff);
            List<Staff> staff_list = new ArrayList<>();
            if (staff != null) {
                staff_list.add(staff);
            }
            return new StaffResponse("00","successful","Staff created succesfully", staff_list);
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new StaffResponse("09","failed","Unable to create staff", new ArrayList<Staff>() );
        }
//        return new StaffResponse("00","Unable to create staff", new ArrayList<Staff>() );
    }

    public StaffResponse getAllStaff(){
         var staff = staffRepository.getAllStaff();

         return new StaffResponse("00","successful","Staff fetched succesfully", staff);
    }

    public StaffResponse getStaffById(int staffId) {

        try
        {
            var staff = staffRepository.getStaffById(staffId);
            List<Staff> staff_list = new ArrayList<>();
            if (staff != null) {
                staff_list.add(staff);
                return new StaffResponse("00","successful","Staff fetched succesfully", staff_list);
            }
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new StaffResponse("09","failed","Unable to find staff", new ArrayList<Staff>() );
        }
        return new StaffResponse("09","failed","Unable to find staff", new ArrayList<Staff>() );
    }

    public StaffResponse updateStaff(StaffUpdateRequest staffUpdateRequest, int staffId) {
        try{
        Gson gson = new Gson();
        var staff = gson.fromJson(gson.toJson(staffUpdateRequest), Staff.class);
        var updateStaff = staffRepository.updateStaff(staff, staffId);
        if(updateStaff == 1) {
            List<Staff> staff_list = new ArrayList<>();
            var staffInstance = staffRepository.getStaffById(staffId);
            if (staffInstance != null) {
                staff_list.add(staffInstance);
            }
            return new StaffResponse("00","successful","Staff updated succesfully", staff_list);
        }
        }
        catch(Exception ex){
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new StaffResponse("09","failed","Unable to update staff", new ArrayList<Staff>() );
        }
        return new StaffResponse("09","failed", "Unable to update staff", new ArrayList<Staff>());
    }
//    public int deleteStaff(int staffId) {
//        return staffRepository.deleteStaff(staffId);
//    }

    public StaffResponse deleteStaff(int staffId) {

        try
        {
            var staff = staffRepository.deleteStaff(staffId);
            if(staff > 0) return new StaffResponse("00","successful", "Staff deleted succesfully", new ArrayList<Staff>());
        }
        catch(Exception ex)
        {
            System.out.println(">> EXCEPTION ");
            System.out.println(ex.getMessage());
            return new StaffResponse("09","failed","Unable to delete staff", new ArrayList<Staff>() );
        }
        return new StaffResponse("09","failed","Unable to delete staff", new ArrayList<Staff>() );
    }
}

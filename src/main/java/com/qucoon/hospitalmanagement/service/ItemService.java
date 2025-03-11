package com.qucoon.hospitalmanagement.service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.repository.Interface.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {
    private final ItemRepository itemRepository;


    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public AppResponse<List<Item>> getAllItem(){
        try{
        var resp =  itemRepository.getAllItems();
        return new AppResponse<>("00", "success", "Items Fetched Successfully",resp);
    } catch (Exception e) {
        System.err.println("Database operation failed: {}" + e.getMessage() + e);
        return new AppResponse<>("106", "failed", "Internal Server Error", null);
    }
    }

    public AppResponse<List<Item>> getAllActiveItem(){
        try {
            var resp =  itemRepository.getAllActiveItems();
            return new AppResponse<>("00", "success", "Items Fetched Successfully",resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }


    public AppResponse<Item> getItemById(int id){
        try {
            var resp =  itemRepository.getItemById(id);
            if (resp == null) {
                return new AppResponse<>("00", "failed", "Item Not Found or has been deleted", null);
            }
            return new AppResponse<>("00", "success", "Item Fetched Successfully",resp);
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> createItem(ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        try {
            var resp =  itemRepository.createItem(item);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Item Created Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that medicationSales and medication ids exist", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> updateItem(String id, ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  itemRepository.updateItem(Id, item);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Item updated Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that medicationSales and medication ids exist", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }

    public AppResponse<Void> deleteItem(int id) {
        try {
            var resp =  itemRepository.deleteItem(id);
            if (resp > 0){
                return new AppResponse<>("00", "success", "Medication Deleted Successfully", null);
            } else {
                return new AppResponse<>("106", "failed", "Please Enter valid Request body and ensure that item id exist", null);
            }
        } catch (Exception e) {
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new AppResponse<>("106", "failed", "Internal Server Error", null);
        }
    }
}

package com.qucoon.hospitalmanagement.service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import com.qucoon.hospitalmanagement.model.response.GenericResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllItemsResponse;
import com.qucoon.hospitalmanagement.model.response.GetItemResponse;
import com.qucoon.hospitalmanagement.repository.Interface.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);


    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public GetAllItemsResponse getAllItem(){
        try{
        var resp =  itemRepository.getAllItems();
        return new GetAllItemsResponse("00", "success", "Items Fetched Successfully",resp);
    } catch (Exception e) {
        logger.error("Database operation failed: {}", e.getMessage(), e);
        System.err.println("Database operation failed: {}" + e.getMessage() + e);
        return new GetAllItemsResponse("106", "failed", "Internal Server Error", null);
    }
    }

    public GetAllItemsResponse getAllActiveItem(){
        try {
            var resp =  itemRepository.getAllActiveItems();
            return new GetAllItemsResponse("00", "success", "Items Fetched Successfully",resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetAllItemsResponse("106", "failed", "Internal Server Error", null);
        }
    }


    public GetItemResponse getItemById(int id){
        try {
            var resp =  itemRepository.getItemById(id);
            if (resp == null) {
                return new GetItemResponse("00", "failed", "Item Not Found or has been deleted", null);
            }
            return new GetItemResponse("00", "success", "Item Fetched Successfully",resp);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GetItemResponse("106", "failed", "Internal Server Error", null);
        }
    }

    public GenericResponse createItem(ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        try {
            var resp =  itemRepository.createItem(item);
            if (resp > 0){
                return new GenericResponse("00", "success", "Item Created Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that medicationSales and medication ids exist");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse updateItem(String id, ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        try {
            var resp =  itemRepository.updateItem(Id, item);
            if (resp > 0){
                return new GenericResponse("00", "success", "Item updated Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that medicationSales and medication ids exist");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }

    public GenericResponse deleteItem(int id) {
        try {
            var resp =  itemRepository.deleteItem(id);
            if (resp > 0){
                return new GenericResponse("00", "success", "Medication Deleted Successfully");
            } else {
                return new GenericResponse("106", "failed", "Please Enter valid Request body and ensure that item id exist");
            }
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            return new GenericResponse("106", "failed", "Internal Server Error");
        }
    }
}

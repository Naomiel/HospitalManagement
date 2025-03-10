package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.service.ItemService;

import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import com.qucoon.hospitalmanagement.model.response.GenericResponse;
import com.qucoon.hospitalmanagement.model.response.GetAllItemsResponse;
import com.qucoon.hospitalmanagement.model.response.GetItemResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create-item")
    public ResponseEntity<GenericResponse> createItem(@RequestBody ItemCreateRequest request){
        return ResponseEntity.ok(itemService.createItem(request));
    }

    @PatchMapping("/update/{itemId}")
    public ResponseEntity<GenericResponse> updateItem(@Valid @RequestBody ItemCreateRequest request, @PathVariable String itemId){
        return ResponseEntity.ok(itemService.updateItem(itemId,request));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<GenericResponse> deleteItem(@PathVariable int itemId){
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<GetAllItemsResponse> getItems(){
        return ResponseEntity.ok(itemService.getAllItem());
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllItemsResponse> getActiveItems(){
        return ResponseEntity.ok(itemService.getAllActiveItem());
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<GetItemResponse> getItemById(@PathVariable int itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }
}

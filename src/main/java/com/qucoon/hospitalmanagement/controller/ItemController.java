package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.response.AppResponse;
import com.qucoon.hospitalmanagement.service.ItemService;

import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<AppResponse<Void>> createItem(@RequestBody ItemCreateRequest request){
        return ResponseEntity.ok(itemService.createItem(request));
    }

    @PatchMapping("/update/{itemId}")
    public ResponseEntity<AppResponse<Void>> updateItem(@Valid @RequestBody ItemCreateRequest request, @PathVariable String itemId){
        return ResponseEntity.ok(itemService.updateItem(itemId,request));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<AppResponse<Void>> deleteItem(@PathVariable int itemId){
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

    @GetMapping("/all-general")
    public ResponseEntity<AppResponse<List<Item>>> getItems(){
        return ResponseEntity.ok(itemService.getAllItem());
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<Item>>> getActiveItems(){
        return ResponseEntity.ok(itemService.getAllActiveItem());
    }

    @GetMapping("/get/{itemId}")
    public ResponseEntity<AppResponse<Item>> getItemById(@PathVariable int itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }
}

package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.ItemService;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /*@PostMapping("/create-item")
    public ResponseEntity<String> createItem(@RequestBody ItemCreateRequest request){
        var resp =itemService.createItem(request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item failed to create");
        return ResponseEntity.ok("Item created successfully");
    }*/

    @PostMapping("/update-item/{itemId}")
    public ResponseEntity<String> updateItem(@RequestBody ItemCreateRequest request, @PathVariable String itemId){
        var resp =itemService.updateItem(itemId,request);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item failed to update");
        return ResponseEntity.ok("Item updated successfully");
    }

    @PostMapping("/delete-item/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable int itemId){
        var resp = itemService.deleteItem(itemId);
        if(resp<1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item failed to delete");
        return ResponseEntity.ok("Item deleted successfully");
    }

    @GetMapping("/all-general")
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(itemService.getAllItem());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getActiveItems(){
        return ResponseEntity.ok(itemService.getAllActiveItem());
    }

    @GetMapping("/get-by-id/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable int itemId){
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }
}

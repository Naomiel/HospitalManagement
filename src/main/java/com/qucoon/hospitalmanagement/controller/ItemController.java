package com.qucoon.hospitalmanagement.controller;

import com.qucoon.hospitalmanagement.Service.ItemService;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create-item")
    public ResponseEntity<Map<String, Object>> createItem(@RequestBody ItemCreateRequest request){
        var resp =itemService.createItem(request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Item creation failed");
            response.put("data", Map.of("Item", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Item created successfully");
        response.put("data", Map.of("Item", request));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update-item/{itemId}")
    public ResponseEntity<Map<String, Object>> updateItem(@Valid @RequestBody ItemCreateRequest request, @PathVariable String itemId){
        var resp =itemService.updateItem(itemId,request);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Item update failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Item update successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-item/{itemId}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable int itemId){
        var resp = itemService.deleteItem(itemId);
        Map<String, Object> response = new HashMap<>();

        if (resp < 1) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Item deletion failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Item deletion successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-general")
    public ResponseEntity<Map<String, Object>> getItems(){
        var resp = ResponseEntity.ok(itemService.getAllItem());
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Items fetched successfully");
        response.put("data", Map.of("Item", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getActiveItems(){
        var resp = ResponseEntity.ok(itemService.getAllActiveItem());
        Map<String, Object> response = new HashMap<>();

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Items fetched successfully");
        response.put("data", Map.of("Item", resp));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-id/{itemId}")
    public ResponseEntity<Map<String, Object>> getItemById(@PathVariable int itemId){
        var resp = ResponseEntity.ok(itemService.getItemById(itemId));
        Map<String, Object> response = new HashMap<>();

        if (resp == null) {
            response.put("response_code", "09");
            response.put("status", "failed");
            response.put("message", "Item fetch failed");
            response.put("data", Map.of("Item", "{}"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("response_code", "00");
        response.put("status", "success");
        response.put("message", "Item fetched successfully");
        response.put("data", Map.of("Item", resp));
        return ResponseEntity.ok(response);
    }
}

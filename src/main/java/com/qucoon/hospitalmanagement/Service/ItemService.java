package com.qucoon.hospitalmanagement.Service;

import com.google.gson.Gson;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.model.request.ItemCreateRequest;
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

    public List<Item> getAllItem(){
        return itemRepository.getAllItems();
    }

    public List<Item> getAllActiveItem(){
        return itemRepository.getAllActiveItems();
    }


    public Item getItemById(int id){
        return itemRepository.getItemById(id);
    }

    /*public int createItem(ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        return itemRepository.createItem(item);
    }*/

    public int updateItem(String id, ItemCreateRequest request){
        Gson gson = new Gson();
        var item = gson.fromJson(gson.toJson(request), Item.class);
        var Id = gson.fromJson(gson.toJson(id), String.class);
        return itemRepository.updateItem(Id, item);
    }

    public int deleteItem(int id) {
        return itemRepository.deleteItem(id);
    }
}

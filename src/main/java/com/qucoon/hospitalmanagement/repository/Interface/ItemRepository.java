package com.qucoon.hospitalmanagement.repository.Interface;

import com.qucoon.hospitalmanagement.model.entity.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAllItems();

    List<Item> getAllActiveItems();

    Item getItemById(int itemId);

    int createItem(Item item);

    int updateItem(String itemId, Item item);

    int deleteItem(int itemId);
}

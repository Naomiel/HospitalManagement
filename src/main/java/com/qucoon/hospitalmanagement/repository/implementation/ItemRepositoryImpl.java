package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.ItemMapper;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.repository.Interface.ItemRepository;
import com.qucoon.hospitalmanagement.repository.query.ItemQuery;
import com.qucoon.hospitalmanagement.repository.query.MedicationSalesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate ;}

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(ItemQuery.GET_ALL_ITEMS, new ItemMapper());
    }
    @Override
    public List<Item> getAllActiveItems() {
        return jdbcTemplate.query(ItemQuery.GET_ALL_ACTIVE_ITEMS, new ItemMapper());
    }

    @Override
    public Item getItemById(int itemId) {
        MapSqlParameterSource params = new MapSqlParameterSource("itemId", itemId);
        return jdbcTemplate.queryForObject(ItemQuery.GET_ITEM_BY_ID, params, new ItemMapper());
    }

    @Override
    public int createItem(Item item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("itemMedicationSalesId", item.getItemMedicationSalesId())
                .addValue("itemMedicationId", item.getItemMedicationId())
                .addValue("itemQuantity", item.getItemQuantity())
                .addValue("itemStatus", item.getItemStatus());
        return jdbcTemplate.update(ItemQuery.INSERT_ITEM, params);
    }

    @Override
    public int updateItem(String itemId, Item item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("itemId", itemId)
                .addValue("itemMedicationSalesId", item.getItemMedicationSalesId())
                .addValue("itemMedicationId", item.getItemMedicationId())
                .addValue("itemQuantity", item.getItemQuantity());
        var sqlQuery = ItemQuery.buildUpdateQuery(item, String.valueOf(itemId));
        return jdbcTemplate.update(sqlQuery, params);
    }
    @Override
    public int deleteItem(int itemId) {
        MapSqlParameterSource params = new MapSqlParameterSource("itemId", itemId);
        return jdbcTemplate.update(ItemQuery.DELETE_ITEM_BY_ID, params);
    }
}

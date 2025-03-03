package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.exception.DatabaseOperationException;
import com.qucoon.hospitalmanagement.mapper.ItemMapper;
import com.qucoon.hospitalmanagement.model.entity.Item;
import com.qucoon.hospitalmanagement.repository.Interface.ItemRepository;
import com.qucoon.hospitalmanagement.repository.query.ItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);


    @Autowired
    public ItemRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate ;}

    @Override
    public List<Item> getAllItems() {

        try {
            return jdbcTemplate.query(ItemQuery.GET_ALL_ITEMS, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get all items from the database", e);
        }
    }
    @Override
    public List<Item> getAllActiveItems() {
        try {
            return jdbcTemplate.query(ItemQuery.GET_ALL_ACTIVE_ITEMS, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get all active items from the database", e);
        }
    }

    @Override
    public Item getItemById(int itemId) {
        MapSqlParameterSource params = new MapSqlParameterSource("itemId", itemId);
        try {
            return jdbcTemplate.queryForObject(ItemQuery.GET_ITEM_BY_ID, params, new ItemMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);
            // Throw a custom exception
            throw new DatabaseOperationException("Failed to get item from the database", e);
        }
    }

    @Override
    public int createItem(Item item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("itemMedicationSalesId", item.getItemMedicationSalesId())
                .addValue("itemMedicationId", item.getItemMedicationId())
                .addValue("itemQuantity", item.getItemQuantity())
                .addValue("itemStatus", item.getItemStatus());
        try {
            return jdbcTemplate.update(ItemQuery.INSERT_ITEM, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);            // Throw a custom exception
            throw new DatabaseOperationException("Failed to insert item into the database", e);
        }
    }

    @Override
    public int updateItem(String itemId, Item item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("itemId", itemId)
                .addValue("itemMedicationSalesId", item.getItemMedicationSalesId())
                .addValue("itemMedicationId", item.getItemMedicationId())
                .addValue("itemQuantity", item.getItemQuantity());
        //var sqlQuery = ItemQuery.buildUpdateQuery(item, String.valueOf(itemId));
        try {
            return jdbcTemplate.update(ItemQuery.UPDATE_ITEM, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);            // Throw a custom exception
            throw new DatabaseOperationException("Failed to update item in the database", e);
        }
    }
    @Override
    public int deleteItem(int itemId) {
        MapSqlParameterSource params = new MapSqlParameterSource("itemId", itemId);
        try {
            return jdbcTemplate.update(ItemQuery.DELETE_ITEM_BY_ID, params);
        } catch (Exception e) {
            logger.error("Database operation failed: {}", e.getMessage(), e);
            System.err.println("Database operation failed: {}" + e.getMessage() + e);            // Throw a custom exception
            throw new DatabaseOperationException("Failed to delete item from the database", e);
        }
    }
}

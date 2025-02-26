package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.EquipmentMapper;
import com.qucoon.hospitalmanagement.model.entity.Equipment;
import com.qucoon.hospitalmanagement.repository.Interface.EquipmentRepository;
import com.qucoon.hospitalmanagement.repository.query.EquipmentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EquipmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createEquipment(Equipment equipment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentName", equipment.getEquipmentName())
                .addValue("equipmentCategory", equipment.getEquipmentCategory())
                .addValue("equipmentQuantity", equipment.getEquipmentQuantity())
                .addValue("equipmentPurchaseDate", equipment.getEquipmentPurchaseDate())
                .addValue("equipmentStatus", equipment.getEquipmentStatus());

        return jdbcTemplate.update(EquipmentQuery.INSERT_EQUIPMENT, params);
    }
    

    @Override
    public List<Equipment> getAllEquipment() {
        return jdbcTemplate.query(EquipmentQuery.GET_ALL_EQUIPMENT, new EquipmentMapper());
    }

    @Override
    public Equipment getEquipmentById(int equipmentId) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource("equipmentId", equipmentId);
            return jdbcTemplate.queryForObject(EquipmentQuery.GET_EQUIPMENT_BY_ID, params, new EquipmentMapper());
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int updateEquipment(Equipment Equipment, int equipmentId) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("equipmentId", equipmentId)
                    .addValue("equipmentName",Equipment.getEquipmentName())
                    .addValue("equipmentCategory",Equipment.getEquipmentCategory())
                    .addValue("equipmentQuantity",Equipment.getEquipmentQuantity())
                    .addValue("equipmentPurchaseDate",Equipment.getEquipmentPurchaseDate())
                    .addValue("equipmentStatus",Equipment.getEquipmentStatus());

            var query = EquipmentQuery.buildUpdateQuery(Equipment, String.valueOf(equipmentId));
            return jdbcTemplate.update(query, params);
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteEquipment(int equipmentId) {
        try{
            MapSqlParameterSource params = new MapSqlParameterSource("equipmentId", equipmentId);
            return jdbcTemplate.update(EquipmentQuery.DELETE_EQUIPMENT, params);
        }
        catch(Exception e){
            System.out.println(">> EXCEPTION ");
            System.out.println(e.getMessage());
            return 0;
        }
    }
}

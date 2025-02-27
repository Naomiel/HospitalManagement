package com.qucoon.hospitalmanagement.repository.implementation;

import com.qucoon.hospitalmanagement.mapper.MedicationSalesMapper;
import com.qucoon.hospitalmanagement.model.entity.MedicationSales;
import com.qucoon.hospitalmanagement.model.request.MedicationList;
import com.qucoon.hospitalmanagement.repository.Interface.MedicationSalesRepository;
import com.qucoon.hospitalmanagement.repository.query.MedicationSalesQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MedicationSalesRepositoryImpl implements MedicationSalesRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationSalesRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate ;}

    @Override
    public List<MedicationSales> getAllMedicationSales() {
        return jdbcTemplate.query(MedicationSalesQuery.GET_ALL_MEDICATION_SALES, new MedicationSalesMapper());
    }

    @Override
    public List<MedicationSales> getAllActiveMedicationSales() {
        return jdbcTemplate.query(MedicationSalesQuery.GET_ALL_ACTIVE_MEDICATION_SALES, new MedicationSalesMapper());
    }

    @Override
    public MedicationSales getMedicationSalesById(int medicationSalesId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationSalesId",  medicationSalesId);
        return jdbcTemplate.queryForObject(MedicationSalesQuery.GET_MEDICATION_SALES_BY_ID, params,  new MedicationSalesMapper());
    }

    @Override
    public int createMedicationSales(MedicationSales medicationSales) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationSalesPatientId", medicationSales.getMedicationSalesPatientId())
                .addValue("medicationSalesStaffId", medicationSales.getMedicationSalesStaffId())
                .addValue("medicationSalesStatus", medicationSales.getMedicationSalesStatus());
        jdbcTemplate.update(MedicationSalesQuery.INSERT_MEDICATION_SALES, params);
        Integer insertedId = jdbcTemplate.queryForObject(MedicationSalesQuery.GET_LAST_INSERT_ID, Map.of(), Integer.class);

        // Handle potential null case
        return insertedId != null ? insertedId : -1;
    }

    @Override
    public int createMedicationSalesItems(int itemMedicationSalesId, List<MedicationList> medicationIdAndQuantity) {
        if (medicationIdAndQuantity.isEmpty()) {
            return 0; // No items to insert
        }

        StringBuilder valuesPlaceholder = new StringBuilder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        for (int i = 0; i < medicationIdAndQuantity.size(); i++) {
            valuesPlaceholder.append("(:itemMedicationSalesId").append(i)
                    .append(", :itemMedicationId").append(i)
                    .append(", :itemQuantity").append(i).append("), ");

            params.addValue("itemMedicationSalesId" + i, itemMedicationSalesId)
                    .addValue("itemMedicationId" + i, medicationIdAndQuantity.get(i).getItemMedicationId())
                    .addValue("itemQuantity" + i, medicationIdAndQuantity.get(i).getItemQuantity());
        }

        // Remove last comma and space
        String finalQuery = String.format(MedicationSalesQuery.INSERT_MEDICATION_SALES_ITEMS,
                valuesPlaceholder.substring(0, valuesPlaceholder.length() - 2));

        return jdbcTemplate.update(finalQuery, params);
    }


    @Override
    public int updateMedicationSales(String medicationSalesId, MedicationSales medicationSales) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationSalesPatientId", medicationSales.getMedicationSalesPatientId())
                .addValue("medicationSalesStaffId", medicationSales.getMedicationSalesStaffId());
        return jdbcTemplate.update(MedicationSalesQuery.UPDATE_MEDICATION_SALES, params);
    }

    @Override
    public int deleteMedicationSales(int medicationSalesId) {
        MapSqlParameterSource params = new MapSqlParameterSource("medicationSalesId",  medicationSalesId);
        return jdbcTemplate.update(MedicationSalesQuery.DELETE_MEDICATION_SALES, params);
    }
}

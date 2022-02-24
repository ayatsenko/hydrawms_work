package com.idltd.hydramob.repository.kpi_facts.cs_crm_general;

import com.idltd.hydramob.entity.kpi_facts.cs_crm_general.MenuKPIFactsCsCRMClientsNotFill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsCrmClientsNotFillRepository extends CrudRepository<MenuKPIFactsCsCRMClientsNotFill, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_NOTFILL_CLIENTS_LIST(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCsCRMClientsNotFill> queryUserKPICRMKPIFactsClientsNotFill(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );;
}
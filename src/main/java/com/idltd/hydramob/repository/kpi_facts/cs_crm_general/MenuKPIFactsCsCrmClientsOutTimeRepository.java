package com.idltd.hydramob.repository.kpi_facts.cs_crm_general;

import com.idltd.hydramob.entity.kpi_facts.cs_crm_general.MenuKPIFactsCsCRMClientsOutTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsCrmClientsOutTimeRepository extends CrudRepository<MenuKPIFactsCsCRMClientsOutTime, Long> {
        @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_OUTTIME_CLIENTS_LIST(?1,?2,?3,?4,?5))")
        List<MenuKPIFactsCsCRMClientsOutTime> queryUserKPICsCRMKPIFactsClientsOutTime(Long user_id, Long role_id, Long req_role_id, Long year, Long month);
}
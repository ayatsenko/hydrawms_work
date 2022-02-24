package com.idltd.hydramob.repository.kpi_facts.cs_crm;

import com.idltd.hydramob.entity.kpi_facts.cs_crm.MenuKPIFactsCsCRMClients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MenuKPIFactsCsCRMClientsRepository extends CrudRepository<MenuKPIFactsCsCRMClients, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vCRM_KPI_CLIENTS_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCsCRMClients> queryMenuKPIFactsCsCRMClientsByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );
}
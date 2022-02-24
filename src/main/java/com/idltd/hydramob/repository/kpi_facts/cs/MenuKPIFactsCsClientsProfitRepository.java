package com.idltd.hydramob.repository.kpi_facts.cs;

import com.idltd.hydramob.entity.kpi_facts.cs.MenuKPIFactsCsClientsProfit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsClientsProfitRepository extends CrudRepository<MenuKPIFactsCsClientsProfit, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_CLIENTS_PERS_PROF_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCsClientsProfit> queryMenuKPIFactsCsClientsProfitByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );
}
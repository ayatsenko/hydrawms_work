package com.idltd.hydramob.repository.kpi_facts.cs;

import com.idltd.hydramob.entity.kpi_facts.cs.MenuKPIFactsCsClientsFinDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsClientsFinDetailRepository extends CrudRepository<MenuKPIFactsCsClientsFinDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_SALE_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuKPIFactsCsClientsFinDetail> queryMenuKPIFactsCleintsFinDetailByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id
    );
}
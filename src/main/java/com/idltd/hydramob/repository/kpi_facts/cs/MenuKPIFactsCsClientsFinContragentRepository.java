package com.idltd.hydramob.repository.kpi_facts.cs;

import com.idltd.hydramob.entity.kpi_facts.cs.MenuKPIFactsCsClientsFinContragent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsClientsFinContragentRepository extends CrudRepository<MenuKPIFactsCsClientsFinContragent, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_CLIENTS_FIN_CONTRAGENT_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuKPIFactsCsClientsFinContragent> queryMenuKPIFactsCsClientsFinContragentByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long ser_id
    );
}
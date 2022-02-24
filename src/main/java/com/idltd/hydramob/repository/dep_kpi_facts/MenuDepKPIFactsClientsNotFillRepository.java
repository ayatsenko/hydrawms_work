package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactsClientsNotFill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactsClientsNotFillRepository extends CrudRepository<MenuDepKPIFactsClientsNotFill, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_NOTFILL_CLIENTS_LIST(?1,?2,?3,?4,?5))")
    List<MenuDepKPIFactsClientsNotFill> queryUserKPICRMDepKPIFactsClientsNotFill(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);;
}
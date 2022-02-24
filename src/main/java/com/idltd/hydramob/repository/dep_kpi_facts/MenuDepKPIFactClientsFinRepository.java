package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactClientsFin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactClientsFinRepository extends CrudRepository<MenuDepKPIFactClientsFin, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_CLIENTS_FIN_MENU(?1,?2,?3,?4,?5))")
    List<MenuDepKPIFactClientsFin> queryMenuDepKPIFactClientsFinByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
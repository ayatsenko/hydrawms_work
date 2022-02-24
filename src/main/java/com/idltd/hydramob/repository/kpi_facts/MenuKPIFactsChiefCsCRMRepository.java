package com.idltd.hydramob.repository.kpi_facts;

import com.idltd.hydramob.entity.kpi_facts.MenuKPIFactsCheifCsCRM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsChiefCsCRMRepository extends CrudRepository<MenuKPIFactsCheifCsCRM, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_CHIEF_SALE_CRM_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCheifCsCRM> queryUserKPIChiefCsCRMByUserID(
            Long user_id, Long role_id, Long req_role_id, Long year, Long month
    );
}
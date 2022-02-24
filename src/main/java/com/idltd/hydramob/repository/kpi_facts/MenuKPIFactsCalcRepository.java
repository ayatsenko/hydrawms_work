package com.idltd.hydramob.repository.kpi_facts;

import com.idltd.hydramob.entity.kpi_facts.MenuKPIFactsCalc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCalcRepository extends CrudRepository<MenuKPIFactsCalc, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_SALE_MAIN_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuKPIFactsCalc> queryUserKPICalcByUserID(
            Long user_id, Long role_id, Long req_role_id, Long year, Long month, Long dep_id
    );
}
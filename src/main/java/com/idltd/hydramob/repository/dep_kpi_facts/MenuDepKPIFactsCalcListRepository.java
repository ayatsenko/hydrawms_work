package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactsCalcList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactsCalcListRepository extends CrudRepository<MenuDepKPIFactsCalcList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_SALE_MAIN_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuDepKPIFactsCalcList> queryUserKPICalcMainListByUserID(Long user_id, Long role_id, Long req_role_id, Long year, Long month, Long dep_id);
}
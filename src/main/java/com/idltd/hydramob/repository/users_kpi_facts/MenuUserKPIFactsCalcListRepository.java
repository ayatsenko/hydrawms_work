package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactsCalcList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactsCalcListRepository extends CrudRepository<MenuUserKPIFactsCalcList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_SALE_MAIN_SIMPL_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFactsCalcList> queryUserKPICalcMainListByUserID(Long user_id, Long role_id, Long req_role_id, Long year, Long month);
}
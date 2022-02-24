package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.MenuDepKPIPlansMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIPlansMainListRepository extends CrudRepository<MenuDepKPIPlansMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_DEP_PLAN_MENU(?1,?2,?3,?4,?5))")
    List<MenuDepKPIPlansMainList> queryUserKPIPlansMainMenuByUserID(Long user_id, Long role_id, Long dep_id, Long year, Long dep_kpi_type_id);
}
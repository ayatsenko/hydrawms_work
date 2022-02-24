package com.idltd.hydramob.repository.users_kpi_plans;

import com.idltd.hydramob.entity.users_kpi_plans.MenuUserKPIPlansList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIPlansListRepository extends CrudRepository<MenuUserKPIPlansList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_PLAN_MENU(?1,?2,?3,?4))")
    List<MenuUserKPIPlansList> queryUserKPIPlansListByUserID(Long user_id, Long role_id, Long year, Long user_kpi_type_id);
}
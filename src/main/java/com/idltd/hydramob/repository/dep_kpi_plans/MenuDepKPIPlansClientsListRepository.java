package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.MenuDepUserKPIPlansClientsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIPlansClientsListRepository extends CrudRepository<MenuDepUserKPIPlansClientsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_PLAN_CNT_MENU(?1,?2,?3,?4,?5))")
    List<MenuDepUserKPIPlansClientsList> queryUserKPIPlansClientsListByUserID(
            Long user_id, Long role_id, Long dep_kpi_type_year, Long dep_kpi_type_id, Long dep_id
    );
}
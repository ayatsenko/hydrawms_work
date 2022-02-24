package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Plan_Client_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Plan_Client_ListRepository extends CrudRepository<Plan_Client_List, Long> {
    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_KPI_VIEW.vDEP_KPI_PLAN_FIN_MONTH_NEW_CLIENT(?1,?2,?3,?4,?5,?6,?7))")
    List<Plan_Client_List> queryPlanClientByUserID(
            Long user_id, Long role_id, Long dep_id, Long dep_kpi_profit_year, Long dep_kpi_profit_month, Long dep_kpi_type_id, String cnt_filter
    );
}

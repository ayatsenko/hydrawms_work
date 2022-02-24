package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.MenuDepUserKPIProfitPlansTab;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIProfitPlansTabRepository extends CrudRepository<MenuDepUserKPIProfitPlansTab, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_PLAN_FIN_MONTH_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuDepUserKPIProfitPlansTab> queryUserKPIProfitPlansTabByUserID(
            Long user_id, Long role_id, Long dep_id, Long dep_kpi_profit_year, Long dep_kpi_profit_month, Long dep_kpi_type_id
    );
}
package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.dep_kpi_plans.MenuDepUserKPIPlansUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIPlansUsersListRepository extends CrudRepository<MenuDepUserKPIPlansUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_PLAN_USERS_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuDepUserKPIPlansUsersList> queryUserKPIPlansListByUserID(
            Long user_id, Long role_id, Long year, Long user_kpi_type_id, Long dep_user_id, Long dep_id
    );
}
package com.idltd.hydramob.repository.users_kpi_ops;

import com.idltd.hydramob.entity.users_kpi_ops.MenuUserKPIOPSMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIOPSMainListRepository extends CrudRepository<MenuUserKPIOPSMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_OPS_MAIN_MENU(?1,?2,?3))")
    List<MenuUserKPIOPSMainList> queryUserKPIOPSMainListByUserID(Long user_id, Long role_id, Long year);
}
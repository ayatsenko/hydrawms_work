package com.idltd.hydramob.repository.users_kpi_ops;

import com.idltd.hydramob.entity.users_kpi_ops.MenuUserKPIOPSCalcList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIOPSCalcListRepository extends CrudRepository<MenuUserKPIOPSCalcList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_OPS_MAIN_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIOPSCalcList> queryUserKPICalcMainListByUserID(Long user_id, Long role_id, Long req_role_id, Long year, Long month);
}
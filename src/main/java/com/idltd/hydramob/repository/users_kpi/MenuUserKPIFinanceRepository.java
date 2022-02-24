package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.MenuUserKPIFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFinanceRepository extends CrudRepository<MenuUserKPIFinance, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFinance> queryMenuUserKPIFinanceByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
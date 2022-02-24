package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactOPSHeadNewFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactOPSHeadNewFinanceRepository extends CrudRepository<MenuDepKPIFactOPSHeadNewFinance, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vHEAD_NEW_FINANCE_KPI_OPS_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuDepKPIFactOPSHeadNewFinance> queryMenuDepKPIFactOPSHeadNEwFinanceByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id);
}
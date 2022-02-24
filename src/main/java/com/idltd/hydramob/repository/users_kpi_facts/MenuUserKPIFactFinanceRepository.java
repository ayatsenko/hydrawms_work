package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactFinanceRepository extends CrudRepository<MenuUserKPIFactFinance, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_SALE_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFactFinance> queryMenuUserKPIFactFinanceByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactsMainListRepository extends CrudRepository<MenuUserKPIFactsMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vFINANCE_KPI_FACT_MAIN_MENU(?1,?2,?3))")
    List<MenuUserKPIFactsMainList> queryUserKPIFactsMainListByUserID(Long user_id, Long role_id, Long year);
}
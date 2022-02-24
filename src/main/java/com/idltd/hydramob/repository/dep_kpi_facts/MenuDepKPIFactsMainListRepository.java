package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactsMainListRepository extends CrudRepository<MenuDepKPIFactsMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_FACT_MAIN_MENU(?1,?2,?3))")
    List<MenuDepKPIFactsMainList> queryDepKPIFactsMainListByUserID(Long user_id, Long role_id, Long year);
}
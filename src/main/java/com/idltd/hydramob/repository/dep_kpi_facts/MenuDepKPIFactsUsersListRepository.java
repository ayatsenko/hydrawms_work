package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactsUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactsUsersListRepository extends CrudRepository<MenuDepKPIFactsUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vDEP_KPI_FACT_USERS_MENU(?1,?2,?3,?4))")
    List<MenuDepKPIFactsUsersList> queryDepKPIFactsUsersListByUserID(Long user_id, Long role_id, Long year, Long dep_id);
}
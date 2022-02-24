package com.idltd.hydramob.repository.kpi_facts.cs_crm;

import com.idltd.hydramob.entity.kpi_facts.cs_crm.MenuKPIFactsCsCRMTodo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsCRMTodoRepository extends CrudRepository<MenuKPIFactsCsCRMTodo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_KPI_VIEW.vCRM_TODO_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCsCRMTodo> queryMenuKPIFactsCsCRMTodoByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );
}
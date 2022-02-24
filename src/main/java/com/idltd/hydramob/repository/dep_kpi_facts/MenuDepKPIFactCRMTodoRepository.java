package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactCRMTodo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactCRMTodoRepository extends CrudRepository<MenuDepKPIFactCRMTodo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_KPI_VIEW.vCRM_TODO_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuDepKPIFactCRMTodo> queryMenuDepKPIFactCRMTodoByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
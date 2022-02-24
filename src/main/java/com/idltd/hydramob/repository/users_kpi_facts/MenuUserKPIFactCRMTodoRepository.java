package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactCRMTodo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactCRMTodoRepository extends CrudRepository<MenuUserKPIFactCRMTodo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_KPI_VIEW.vCRM_TODO_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFactCRMTodo> queryMenuUserKPIFactCRMTodoByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
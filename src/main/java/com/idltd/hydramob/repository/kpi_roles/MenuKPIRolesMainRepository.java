package com.idltd.hydramob.repository.kpi_roles;

import com.idltd.hydramob.entity.kpi_roles.MenuKPIRoleMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIRolesMainRepository extends CrudRepository<MenuKPIRoleMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_ROLES_MENU(?1,?2))")
    List<MenuKPIRoleMain> queryKPIRoleMenuByUserID(Long user_id, Long role_id);
}
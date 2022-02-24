package com.idltd.hydramob.repository.wms_kpi_project;

import com.idltd.hydramob.entity.wms_kpi_project.MenuWMSKPIProjectDepartmentUsers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSKPIProjectDepartmentUsersRepository extends CrudRepository<MenuWMSKPIProjectDepartmentUsers, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_KPI_VIEW.vWMS_USERS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuWMSKPIProjectDepartmentUsers> queryWMSKPIProjectDepartmentUsersByID(
            Long user_id,
            Long role_id,
            Long year,
            Long wms_dep_id,
            Long wms_project_id
    );
}
package com.idltd.hydramob.repository.wms_kpi_project;

import com.idltd.hydramob.entity.wms_kpi_project.MenuWMSKPIProjectDepartmentProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSKPIProjectDepartmentProjectRepository extends CrudRepository<MenuWMSKPIProjectDepartmentProject, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_KPI_VIEW.vWMS_PROJECTS_KPI_MENU(?1,?2,?3,?4))")
    List<MenuWMSKPIProjectDepartmentProject> queryWMSKPIProjectDepartmentProjectByID(
            Long user_id,
            Long role_id,
            Long year,
            Long wms_dep_id
    );
}
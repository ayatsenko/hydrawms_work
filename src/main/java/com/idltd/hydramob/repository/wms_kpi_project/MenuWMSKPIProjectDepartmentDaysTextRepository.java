package com.idltd.hydramob.repository.wms_kpi_project;

import com.idltd.hydramob.entity.wms_kpi_project.MenuWMSKPIProjectDepartmentDaysText;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSKPIProjectDepartmentDaysTextRepository extends CrudRepository<MenuWMSKPIProjectDepartmentDaysText, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_KPI_VIEW.vWMS_USERS_DAY_TEXT_KPI_MENU(?1,?2,?3,?4,?5,?6,?7,?8))")
    List<MenuWMSKPIProjectDepartmentDaysText> queryWMSKPIProjectDepartmentDaysTextByID(
            Long user_id,
            Long role_id,
            Long year,
            Long wms_dep_id,
            Long wms_project_id,
            Long req_user_id,
            Long month,
            Long wms_kpi_param_id
    );
}
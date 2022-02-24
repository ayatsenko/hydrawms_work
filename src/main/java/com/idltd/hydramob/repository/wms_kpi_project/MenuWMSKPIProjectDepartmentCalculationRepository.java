package com.idltd.hydramob.repository.wms_kpi_project;

import com.idltd.hydramob.entity.wms_kpi_project.MenuWMSKPIProjectDepartmentCalculation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSKPIProjectDepartmentCalculationRepository extends CrudRepository<MenuWMSKPIProjectDepartmentCalculation, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_KPI_VIEW.vWMS_USERS_KPI_MONTH_DETAIL_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuWMSKPIProjectDepartmentCalculation> queryWMSKPIProjectDepartmentUsersDetailByID(
            Long user_id,
            Long role_id,
            Long year,
            Long wms_dep_id,
            Long wms_project_id,
            Long req_user_id,
            Long month
    );
}
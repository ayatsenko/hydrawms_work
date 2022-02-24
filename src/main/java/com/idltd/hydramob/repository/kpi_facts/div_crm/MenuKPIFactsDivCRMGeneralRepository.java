package com.idltd.hydramob.repository.kpi_facts.div_crm;

import com.idltd.hydramob.entity.kpi_facts.div_crm.MenuKPIFactsDivCRMGeneral;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsDivCRMGeneralRepository extends CrudRepository<MenuKPIFactsDivCRMGeneral, Long> {
        @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_SALE_CRM_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsDivCRMGeneral> queryUserKPICRMMainListByUserID(Long user_id, Long role_id, Long req_role_id, Long year, Long month);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_CHIEF_SALE_CRM_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsDivCRMGeneral> queryUserKPICRMChiefMainListByUserID(Long user_id, Long role_id, Long req_role_id, Long year, Long month);
}
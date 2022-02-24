package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactCRMGeneral;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactCRMGeneralRepository extends CrudRepository<MenuUserKPIFactCRMGeneral, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vCRM_KPI_GENERAL_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFactCRMGeneral> queryMenuUserKPIFactCRMGeneralByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
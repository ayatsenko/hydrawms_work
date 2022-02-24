package com.idltd.hydramob.repository.kpi_facts.div_crm;

import com.idltd.hydramob.entity.kpi_facts.div_crm.MenuKPIFactsDivCRMRequests;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsDivCRMRequestsRepository extends CrudRepository<MenuKPIFactsDivCRMRequests, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vCRM_REQUESTS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsDivCRMRequests> queryMenuDepKPIFactCRMRequestsByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );
}
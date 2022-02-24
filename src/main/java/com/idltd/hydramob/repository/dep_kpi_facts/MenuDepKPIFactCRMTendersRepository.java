package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactCRMTenders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactCRMTendersRepository extends CrudRepository<MenuDepKPIFactCRMTenders, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vCRM_TENDERS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuDepKPIFactCRMTenders> queryMenuDepKPIFactCRMTendersByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
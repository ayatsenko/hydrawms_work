package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactChiefUnpaid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactChiefUnpaidRepository extends CrudRepository<MenuDepKPIFactChiefUnpaid, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vCHIEF_UNPAID_KPI_SALE_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuDepKPIFactChiefUnpaid> queryMenuDepKPIFactChiefUnpaidByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id);
}
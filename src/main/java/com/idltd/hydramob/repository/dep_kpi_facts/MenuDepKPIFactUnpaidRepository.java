package com.idltd.hydramob.repository.dep_kpi_facts;

import com.idltd.hydramob.entity.dep_kpi_facts.MenuDepKPIFactUnpaid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepKPIFactUnpaidRepository extends CrudRepository<MenuDepKPIFactUnpaid, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUNPAID_KPI_SALE_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuDepKPIFactUnpaid> queryMenuDepKPIFactUnpaidByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id);
}
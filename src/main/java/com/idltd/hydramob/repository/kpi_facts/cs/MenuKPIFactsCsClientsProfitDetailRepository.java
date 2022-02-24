package com.idltd.hydramob.repository.kpi_facts.cs;

import com.idltd.hydramob.entity.kpi_facts.cs.MenuKPIFactsCsClientsProfitDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsClientsProfitDetailRepository extends CrudRepository<MenuKPIFactsCsClientsProfitDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vPROFIT_KPI_SALE_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuKPIFactsCsClientsProfitDetail> queryMenuKPIFactsCsClientsProfitDetailByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id
    );
}
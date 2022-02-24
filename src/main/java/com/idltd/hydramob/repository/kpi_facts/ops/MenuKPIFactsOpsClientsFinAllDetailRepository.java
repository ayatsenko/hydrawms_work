package com.idltd.hydramob.repository.kpi_facts.ops;

import com.idltd.hydramob.entity.kpi_facts.ops.MenuKPIFactsOpsClientsFinAllDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsOpsClientsFinAllDetailRepository extends CrudRepository<MenuKPIFactsOpsClientsFinAllDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_OPS_FIN_All_DETAIL_MENU(?1,?2,?3,?4,?5,?6,?7))")
    List<MenuKPIFactsOpsClientsFinAllDetail> queryMenuKPIFactsOpsCleintsFinAllDetailByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id, Long ser_id
    );
}
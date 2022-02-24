package com.idltd.hydramob.repository.kpi_facts.ops;

import com.idltd.hydramob.entity.kpi_facts.ops.MenuKPIFactsOpsClientsFinCountDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsOpsClientsFinCountDetailRepository extends CrudRepository<MenuKPIFactsOpsClientsFinCountDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_OPS_FIN_COUNT_DETAIL_MENU(?1,?2,?3,?4,?5,?6))")
    List<MenuKPIFactsOpsClientsFinCountDetail> queryMenuKPIFactsOpsCleintsFinCountDetailByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month, Long cnt_id
    );
}
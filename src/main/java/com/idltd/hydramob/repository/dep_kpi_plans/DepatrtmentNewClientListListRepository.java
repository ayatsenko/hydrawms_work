package com.idltd.hydramob.repository.dep_kpi_plans;

import com.idltd.hydramob.entity.TMS_Client_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepatrtmentNewClientListListRepository extends CrudRepository<TMS_Client_List, Long> {
    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_KPI_VIEW.vCLIENT_FILTERED_LIST(?1,?2,?3,?4,?5))")
    List<TMS_Client_List> queryDepNewClientFilteredByUserID(
            Long user_id, Long role_id, Long dep_kpi_type_year, Long dep_id, String cnt_filter
    );
}

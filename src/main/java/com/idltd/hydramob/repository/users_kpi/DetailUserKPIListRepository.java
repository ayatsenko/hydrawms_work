package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.DetailUserKPIList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailUserKPIListRepository extends CrudRepository<DetailUserKPIList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUserMonthKPI_DETAIL(?1,?2,?3,?4,?5))")
    List<DetailUserKPIList> queryUserKPIDetailListByUserID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
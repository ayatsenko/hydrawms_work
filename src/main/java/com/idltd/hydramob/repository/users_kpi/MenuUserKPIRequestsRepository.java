package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.MenuUserKPIRequests;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIRequestsRepository extends CrudRepository<MenuUserKPIRequests, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vREQUESTS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIRequests> queryMenuUserKPIRequestsByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
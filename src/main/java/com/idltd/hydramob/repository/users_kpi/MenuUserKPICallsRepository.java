package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.MenuUserKPICalls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPICallsRepository extends CrudRepository<MenuUserKPICalls, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_KPI_VIEW.vCALLS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPICalls> queryMenuUserKPICallsByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
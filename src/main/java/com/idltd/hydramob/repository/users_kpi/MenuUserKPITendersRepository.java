package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.MenuUserKPITenders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPITendersRepository extends CrudRepository<MenuUserKPITenders, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vTENDERS_KPI_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPITenders> queryMenuUserKPITendersByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
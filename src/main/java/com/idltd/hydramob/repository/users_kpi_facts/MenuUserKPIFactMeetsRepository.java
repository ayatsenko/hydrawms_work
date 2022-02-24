package com.idltd.hydramob.repository.users_kpi_facts;

import com.idltd.hydramob.entity.users_kpi_facts.MenuUserKPIFactMeets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIFactMeetsRepository extends CrudRepository<MenuUserKPIFactMeets, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_KPI_VIEW.vMEETS_KPI_SALE_MENU(?1,?2,?3,?4,?5))")
    List<MenuUserKPIFactMeets> queryMenuUserKPIFactMeetsByID(Long user_id, Long role_id, Long cur_user_id, Long year, Long month);
}
package com.idltd.hydramob.repository.users_kpi;

import com.idltd.hydramob.entity.users_kpi.MenuUserKPIList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIListRepository extends CrudRepository<MenuUserKPIList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUserMonthKPI(?1,?2,?3,?4))")
    List<MenuUserKPIList> queryUserKPIListByUserID(Long user_id, Long role_id, Long year, Long month);
}
package com.idltd.hydramob.repository.users_kpi_meets;

import com.idltd.hydramob.entity.users_kpi_meets.MenuUserKPIMeetsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIMeetsListRepository extends CrudRepository<MenuUserKPIMeetsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_MEETS_MENU(?1,?2))")
    List<MenuUserKPIMeetsList> queryUserKPIMeetUsersListByUserID(Long user_id, Long role_id);
}
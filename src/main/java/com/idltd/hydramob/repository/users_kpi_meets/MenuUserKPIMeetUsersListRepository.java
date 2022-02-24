package com.idltd.hydramob.repository.users_kpi_meets;

import com.idltd.hydramob.entity.users_kpi_meets.MenuUserKPIMeetUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserKPIMeetUsersListRepository extends CrudRepository<MenuUserKPIMeetUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_MEET_USERS_MENU(?1,?2,?3))")
    List<MenuUserKPIMeetUsersList> queryUserKPIMeetsListByUserID(Long user_id, Long role_id, Long user_kpi_meet_id);
}
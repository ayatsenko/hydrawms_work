package com.idltd.hydramob.repository.users_kpi_meets;

import com.idltd.hydramob.entity.users_kpi_meets.UserKPIMeetsUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserKPIMeetsUsersListRepository extends CrudRepository<UserKPIMeetsUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_MEET_USERS_LIST(?1,?2,?3)) ORDER BY USER_NAME")
    List<UserKPIMeetsUsersList> queryUserKPIMeetsUsersListByUserID(Long user_id, Long role_id, Long user_kpi_meet_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_MEET_USER_ONE(?1,?2,?3)) ORDER BY USER_NAME")
    List<UserKPIMeetsUsersList> queryUserKPIMeetsUserOneByUserID(Long user_id, Long role_id, Long req_user_id);
}
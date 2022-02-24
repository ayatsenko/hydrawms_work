package com.idltd.hydramob.repository.users_time;

import com.idltd.hydramob.entity.users_time.MenuUsersTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserTimeRepository extends CrudRepository<MenuUsersTime, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VSYSTEM_TIME_JORNAL(?1,?2,?3,?4,?5,?6))")
    List<MenuUsersTime> queryUserTimeByUserID(Long user_id, Long role_id, Long req_user_id, String start_date, String end_date, Long check_id);
}
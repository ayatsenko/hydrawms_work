package com.idltd.hydramob.repository.users_time;

import com.idltd.hydramob.entity.users_time.MenuUserSessions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserSessionsRepository extends CrudRepository<MenuUserSessions, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VSYSTEM_SESSION_JORNAL(?1,?2,?3,?4,?5))")
    List<MenuUserSessions> queryUserSessionsByUserID(Long user_id, Long role_id, Long req_user_id, String start_date, String end_date);
}
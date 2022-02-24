package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Status_ListRepository extends CrudRepository<User_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_STATUS_LIST(?1,?2))")
    List<User_Status_List> queryUserStatusListByID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_STATUS_LIST(?1,?2)) WHERE USER_STATUS_ID = ?3")
    List<User_Status_List> queryUserOneStatusListByID(Long user_id, Long role_id, Long user_status_id);
}

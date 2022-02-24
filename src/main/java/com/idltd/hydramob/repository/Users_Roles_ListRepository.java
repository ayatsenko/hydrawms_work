package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Users_Roles_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Users_Roles_ListRepository extends CrudRepository<Users_Roles_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE (PKG_USERS_VIEW.vUSERS_NEW_SUBUSERS(?1,?2,?3,?4))")
    List<Users_Roles_List> queryGetSubUsersListByID(Long user_id, Long role_id, Long cur_user_id, Long cur_role_id);
}
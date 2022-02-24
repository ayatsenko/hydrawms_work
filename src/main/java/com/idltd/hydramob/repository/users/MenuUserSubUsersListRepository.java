package com.idltd.hydramob.repository.users;

import com.idltd.hydramob.entity.users.MenuUserSubUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserSubUsersListRepository extends CrudRepository<MenuUserSubUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_SUBUSER_MENU(?1,?2,?3))")
    List<MenuUserSubUsersList> queryUserSubUsersByUserID(Long user_id, Long role_id, Long main_user_id);
}
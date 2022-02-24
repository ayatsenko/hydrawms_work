package com.idltd.hydramob.repository.users;

import com.idltd.hydramob.entity.users.MenuUserList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserListRepository extends CrudRepository<MenuUserList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSERS_MENU(?1,?2))")
    List<MenuUserList> queryUserMenuListByUserID(Long user_id, Long role_id);
}
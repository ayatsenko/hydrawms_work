package com.idltd.hydramob.repository.users;

import com.idltd.hydramob.entity.users.MenuUserContragentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuUserContragentsListRepository extends CrudRepository<MenuUserContragentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_CONTRAGENTS_MENU(?1,?2,?3))")
    List<MenuUserContragentsList> queryUserContragentsByUserID(Long user_id, Long role_id, Long cur_user_id);
}
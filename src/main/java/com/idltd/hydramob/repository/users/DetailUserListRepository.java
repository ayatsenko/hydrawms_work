package com.idltd.hydramob.repository.users;

import com.idltd.hydramob.entity.users.DetailUserList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailUserListRepository extends CrudRepository<DetailUserList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_EDIT_DETAIL(?1,?2,?3))")
    List<DetailUserList> queryUserDetailByUserID(Long user_id, Long role_id, Long cur_user_id);
}
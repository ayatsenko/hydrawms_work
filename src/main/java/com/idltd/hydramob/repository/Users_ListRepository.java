package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Users_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Users_ListRepository extends CrudRepository<Users_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VUSER_DETAIL(?1))")
    List<Users_List> queryUserByID(Long user_id);
}

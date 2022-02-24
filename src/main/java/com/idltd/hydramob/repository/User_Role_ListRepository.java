package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_Role_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Role_ListRepository extends CrudRepository<User_Role_List, Long> {
    @Query(nativeQuery = true, value = "select * from USERS_ROLES where user_id = ?1")
    List<User_Role_List> queryGetUserRoleByID(Long user_id);
}
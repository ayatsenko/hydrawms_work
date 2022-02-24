package com.idltd.hydramob.repository.roles;

import com.idltd.hydramob.entity.roles.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRolesRepository extends CrudRepository<Roles, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vROLES_LIST(?1,?2))")
    List<Roles> queryRoleListByID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vROLES_LIST(?1,?2)) WHERE ROLE_ID = ?3")
    List<Roles> queryOneRoleListByID(Long user_id, Long role_id, Long cur_role_id);
}
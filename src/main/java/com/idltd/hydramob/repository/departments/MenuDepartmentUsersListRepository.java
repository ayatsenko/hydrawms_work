package com.idltd.hydramob.repository.departments;

import com.idltd.hydramob.entity.departments.MenuDepartmentUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDepartmentUsersListRepository extends CrudRepository<MenuDepartmentUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.vDEPARTMENT_USERS_MENU(?1,?2,?3))")
    List<MenuDepartmentUsersList> queryDepartmentUsersMenuByUserID(Long user_id, Long role_id, Long dep_id);
}
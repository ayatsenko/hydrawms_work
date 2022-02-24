package com.idltd.hydramob.repository.departments;

import com.idltd.hydramob.entity.departments.DepartmentUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentUsersListRepository extends CrudRepository<DepartmentUsersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.vDEPARTMENT_USERS_LIST(?1,?2)) ORDER BY USER_NAME")
    List<DepartmentUsersList> queryDepartmentUsersListByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.vDEPARTMENT_USER_ONE(?1,?2,?3)) ORDER BY USER_NAME")
    List<DepartmentUsersList> queryDepartmentUserOneByUserID(Long user_id, Long role_id, Long req_user_id);
}
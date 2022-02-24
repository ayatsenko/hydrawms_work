package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.DetailProjectsUsersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProjectsUsersListRepository extends CrudRepository<DetailProjectsUsersList, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_PROJECTS_VIEW.VPROJ_USER_ROLES_DETAIL(?1,?2,?3))")
    List<DetailProjectsUsersList> queryDetailProjectUsersListByID(Long user_id, Long role_id, Long prrl_id);
}
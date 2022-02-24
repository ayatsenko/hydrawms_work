package com.idltd.hydramob.repository.ops_request;

import com.idltd.hydramob.entity.ops_request.MenuOpsRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuOpsRequestsRepository extends CrudRepository<MenuOpsRequest, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.VOPS_REQUEST_MAIN_MENU(?1,?2))")
    List<MenuOpsRequest> queryOpsRequestByUserID(Long user_id, Long role_id);
}


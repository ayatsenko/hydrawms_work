package com.idltd.hydramob.repository.client_requests;

import com.idltd.hydramob.entity.client_requests.MenuClientRequestTimesDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientRequestTimeDetailRepository extends CrudRepository<MenuClientRequestTimesDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_MENU_DETAIL(?1,?2,?3))")
    List<MenuClientRequestTimesDetail> queryMenuClientRequestTimeDetailByReqID(
            Long user_id, Long role_id, Long req_id
    );
}

package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.MenuRequestTimesDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestTimeDetailRepository extends CrudRepository<MenuRequestTimesDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.VREQUEST_MENU_DETAIL(?1,?2,?3))")
    List<MenuRequestTimesDetail> queryMenuRequestTimeDetailByReqID(
            Long user_id, Long role_id, Long req_id
    );
}

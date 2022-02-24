package com.idltd.hydramob.repository.client_requests;

import com.idltd.hydramob.entity.client_requests.DetailClientRequestList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientRequestListRepository extends CrudRepository<DetailClientRequestList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_SIMPLE_DETAIL(?1,?2,?3))")
    List<DetailClientRequestList> queryGetSimpleClientReqDetailByID(Long user_id, Long role_id, Long req_id);
}
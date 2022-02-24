package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.DetailRequestModalList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRequestListRepository extends CrudRepository<DetailRequestModalList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REQUEST_VIEW.vREQ_SIMPLE_DETAIL_NEW(?1,?2,?3))")
    List<DetailRequestModalList> queryGetSimpleReqDetailByID(Long user_id, Long role_id, Long req_id);
}
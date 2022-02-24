package com.idltd.hydramob.repository.fin_result;

import com.idltd.hydramob.entity.fin_result.DetailFinResultErrorClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailFinResultErrorClientRepository extends CrudRepository<DetailFinResultErrorClient, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VFINANCE_LOAD_ERROR_CNT_DETAIL(?1,?2,?3))")
    List<DetailFinResultErrorClient> queryGetFinResultErrorClientDetail(Long user_id, Long role_id, Long req_ser_id);
}
package com.idltd.hydramob.repository.fin_result;

import com.idltd.hydramob.entity.fin_result.MenuFinResultDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFinResultDetailRepository extends CrudRepository<MenuFinResultDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ADMIN_VIEW.VFINANCE_LOAD_JORNAL(?1,?2,?3,?4,?5))")
    List<MenuFinResultDetail> queryGetFinResultDetail(Long user_id, Long role_id, Long req_ser_id, String start_date, String end_date);
}
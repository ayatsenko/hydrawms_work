package com.idltd.hydramob.repository.clients_abc;

import com.idltd.hydramob.entity.clients_abc.MenuClientsABCDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsABCDetailRepository extends CrudRepository<MenuClientsABCDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ANALYTICS_VIEW.vABC_CLIENTS(?1,?2,?3,?4,?5))")
    List<MenuClientsABCDetail> queryGetClientsABCDetail(Long user_id, Long role_id, Long req_ser_id, String start_date, String end_date);
}
package com.idltd.hydramob.repository.wms_clients_exchange;

import com.idltd.hydramob.entity.wms_clients_exchange.ContragentExchangeServiceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContragentExchangeServiceListRepository extends CrudRepository<ContragentExchangeServiceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_CONTRAGENT_SERVICE_LIST(?1,?2,?3))")
    List<ContragentExchangeServiceList> queryExchangeCntServiceList(Long user_id, Long role_id, Long cnt_id);
}

package com.idltd.hydramob.repository.wms_clients_exchange;

import com.idltd.hydramob.entity.wms_clients_exchange.ContragentExchangeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContragentExchangeListRepository extends CrudRepository<ContragentExchangeList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_CONTRAGENT_LIST(?1,?2))")
    List<ContragentExchangeList> queryExchangeCntList(Long user_id, Long role_id);
}

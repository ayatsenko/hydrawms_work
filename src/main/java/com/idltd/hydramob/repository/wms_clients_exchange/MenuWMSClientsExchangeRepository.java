package com.idltd.hydramob.repository.wms_clients_exchange;

import com.idltd.hydramob.entity.wms_clients_exchange.MenuWMSClientsExchange;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSClientsExchangeRepository extends CrudRepository<MenuWMSClientsExchange, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_MENU(?1,?2))")
    List<MenuWMSClientsExchange> queryWMSClientsExchangeByID(
            Long user_id,
            Long role_id
    );
}
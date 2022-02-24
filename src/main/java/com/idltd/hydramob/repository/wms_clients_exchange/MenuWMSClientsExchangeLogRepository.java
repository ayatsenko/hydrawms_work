package com.idltd.hydramob.repository.wms_clients_exchange;

import com.idltd.hydramob.entity.wms_clients_exchange.MenuWMSClientsExchangeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSClientsExchangeLogRepository extends CrudRepository<MenuWMSClientsExchangeLog, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_LOG_MENU(?1,?2,?3))")
    List<MenuWMSClientsExchangeLog> queryWMSClientsExchangeLogByID(
            Long user_id, Long role_id, Long exch_ser_file_id
    );
}
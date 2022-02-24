package com.idltd.hydramob.repository.wms_clients_quantity;

import com.idltd.hydramob.entity.wms_clients_quantity.MenuWMSClientsQuantity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSClientsQuantityRepository extends CrudRepository<MenuWMSClientsQuantity, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.VWH_CLIENTS_QUANTITY_MENU(?1,?2))")
    List<MenuWMSClientsQuantity> queryWMSClientsQuantityByID(
            Long user_id,
            Long role_id
    );
}
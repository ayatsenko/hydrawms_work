package com.idltd.hydramob.repository.wms_sale_orders;

import com.idltd.hydramob.entity.wms_sale_orders.MenuWMSSaleOrdersMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSSaleOrdersMainRepository extends CrudRepository<MenuWMSSaleOrdersMain, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_TEMP_MENU(?1,?2))")
    List<MenuWMSSaleOrdersMain> queryWMSSaleOrdersMainByID(
            Long user_id,
            Long role_id
    );
}
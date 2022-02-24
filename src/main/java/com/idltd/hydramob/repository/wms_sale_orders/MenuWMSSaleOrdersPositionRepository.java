package com.idltd.hydramob.repository.wms_sale_orders;

import com.idltd.hydramob.entity.wms_sale_orders.MenuWMSSaleOrdersPositions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSSaleOrdersPositionRepository extends CrudRepository<MenuWMSSaleOrdersPositions, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_DETAIL_MENU(?1,?2,?3,?4))")
    List<MenuWMSSaleOrdersPositions> queryWMSSaleOrdersPositionsByID(
            Long user_id,
            Long role_id,
            Long req_exch_ser_file_id,
            Long req_exch_ser_file_row_id
    );
}
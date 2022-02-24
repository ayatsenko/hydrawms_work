package com.idltd.hydramob.repository.wms_sale_orders;

import com.idltd.hydramob.entity.wms_sale_orders.MenuWMSSaleOrdersPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSSaleOrdersPostRepository extends CrudRepository<MenuWMSSaleOrdersPost, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_POST_LINK_MENU(?1,?2,?3))")
    List<MenuWMSSaleOrdersPost> queryWMSSaleOrdersPostByID(
            Long user_id,
            Long role_id,
            Long exch_sale_order_id
    );
}
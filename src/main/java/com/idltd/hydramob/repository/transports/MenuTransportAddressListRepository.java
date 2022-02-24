package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportAddressList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportAddressListRepository extends CrudRepository<MenuTransportAddressList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_ADDRESS_MENU(?1,?2,?3))")
    List<MenuTransportAddressList> queryTransportAddressMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
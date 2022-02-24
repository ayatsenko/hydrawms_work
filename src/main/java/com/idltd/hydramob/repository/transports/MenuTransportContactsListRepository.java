package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportContactsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportContactsListRepository extends CrudRepository<MenuTransportContactsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vTRANSPORT_CONTACTS_MENU(?1,?2,?3))")
    List<MenuTransportContactsList> queryTransportContactsMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
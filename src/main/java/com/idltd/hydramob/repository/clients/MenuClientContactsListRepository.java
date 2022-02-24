package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientContactsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientContactsListRepository extends CrudRepository<MenuClientContactsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_CONTACTS_MENU(?1,?2,?3))")
    List<MenuClientContactsList> queryClientContactsMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
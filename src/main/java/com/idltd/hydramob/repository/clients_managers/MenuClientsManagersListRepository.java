package com.idltd.hydramob.repository.clients_managers;

import com.idltd.hydramob.entity.clients_managers.MenuClientsManagersList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientsManagersListRepository extends CrudRepository<MenuClientsManagersList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.VCONTRAGENT_USER_SER_MENU(?1,?2,?3,?4))")
    List<MenuClientsManagersList> queryClientManagersMenuListByUserID(Long user_id, Long role_id, Long cs_user_id, Long ops_user_id);
}
package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientSubList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientSubListRepository extends CrudRepository<MenuClientSubList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_SUB_LIST(?1,?2,?3))")
    List<MenuClientSubList> queryClientMenuSubListByUserID(Long user_id, Long role_id, Long cnt_id);
}
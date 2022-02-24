package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientReferenceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientReferenceListRepository extends CrudRepository<MenuClientReferenceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_REFENCE_MENU(?1,?2,?3))")
    List<MenuClientReferenceList> queryClientMenuReferenceListByUserID(Long user_id, Long role_id, Long cnt_id);
}
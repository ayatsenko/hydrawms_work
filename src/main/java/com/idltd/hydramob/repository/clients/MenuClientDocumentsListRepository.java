package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientDocumentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientDocumentsListRepository extends CrudRepository<MenuClientDocumentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOCUMENTS_MENU(?1,?2,?3))")
    List<MenuClientDocumentsList> queryClientDocumentsMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
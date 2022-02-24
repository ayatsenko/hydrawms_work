package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportDocumentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportDocumentsListRepository extends CrudRepository<MenuTransportDocumentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOCUMENTS_MENU(?1,?2,?3))")
    List<MenuTransportDocumentsList> queryTransportDocumentsMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientDocumentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientDocumentsListRepository extends CrudRepository<DetailClientDocumentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOCUMENTS_DETAIL(?1,?2,?3))")
    List<DetailClientDocumentsList> queryClientDocumentsDetailListByUserID(Long user_id, Long role_id, Long cnt_doc_id);
}
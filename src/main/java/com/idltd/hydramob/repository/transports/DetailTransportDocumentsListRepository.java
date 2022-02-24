package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportDocumentsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportDocumentsListRepository extends CrudRepository<DetailTransportDocumentsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOCUMENTS_DETAIL(?1,?2,?3))")
    List<DetailTransportDocumentsList> queryTransportDocumentsDetailListByUserID(Long user_id, Long role_id, Long cnt_doc_id);
}
package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportDocFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportDocFilesListRepository extends CrudRepository<DetailTransportDocFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOC_FILES_DETAIL(?1,?2,?3))")
    List<DetailTransportDocFilesList> queryTransportDocFileDetailListByUserID(Long user_id, Long role_id, Long cnt_doc_file_id);
}
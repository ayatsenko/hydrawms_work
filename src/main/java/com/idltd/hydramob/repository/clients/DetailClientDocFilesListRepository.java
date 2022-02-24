package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientDocFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientDocFilesListRepository extends CrudRepository<DetailClientDocFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOC_FILES_DETAIL(?1,?2,?3))")
    List<DetailClientDocFilesList> queryClientAddressDetailListByUserID(Long user_id, Long role_id, Long cnt_doc_file_id);
}
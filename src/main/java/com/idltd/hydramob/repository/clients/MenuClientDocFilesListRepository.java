package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.MenuClientDocFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientDocFilesListRepository extends CrudRepository<MenuClientDocFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DOC_FILES_MENU(?1,?2,?3,?4))")
    List<MenuClientDocFilesList> queryClientDocFilesMenuListByUserID(Long user_id, Long role_id, Long cnt_id, Long cnt_doc_id);
}
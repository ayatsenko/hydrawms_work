package com.idltd.hydramob.repository.client_docs;

import com.idltd.hydramob.entity.client_docs.MenuClientBillsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClientBillsListRepository extends CrudRepository<MenuClientBillsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_BILLS_MENU(?1,?2,?3))")
    List<MenuClientBillsList> queryClientBillsMenuListByUserID(Long user_id, Long role_id, Long cnt_doc_id);
}
package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientContactsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientsContactsListRepository extends CrudRepository<DetailClientContactsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_CONT_ALL_DETAIL(?1,?2,?3))")
    List<DetailClientContactsList> queryClientContactsListDetaulByUserID(Long user_id, Long role_id, Long cc_id);
}
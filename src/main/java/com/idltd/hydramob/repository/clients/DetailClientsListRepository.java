package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientsListRepository extends CrudRepository<DetailClientsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_DETAIL(?1,?2,?3))")
    List<DetailClientsList> queryClientListDetaulByUserID(Long user_id, Long role_id, Long cnt_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_NEW_DETAIL(?1,?2))")
    List<DetailClientsList> queryNewClientListByUserID(Long user_id, Long role_id);
}
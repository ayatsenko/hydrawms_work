package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientServicesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientsServicesListRepository extends CrudRepository<DetailClientServicesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_SERVICES_DETAIL(?1,?2,?3))")
    List<DetailClientServicesList> queryClientServicesListDetaulByUserID(Long user_id, Long role_id, Long cntserl_id);
}
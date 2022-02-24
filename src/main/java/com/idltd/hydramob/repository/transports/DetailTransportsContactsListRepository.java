package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportContactsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportsContactsListRepository extends CrudRepository<DetailTransportContactsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vTRANSPORT_CONT_ALL_DETAIL(?1,?2,?3))")
    List<DetailTransportContactsList> queryTransportContactsListDetaulByUserID(Long user_id, Long role_id, Long cc_id);
}
package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportAddressList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportAddressListRepository extends CrudRepository<DetailTransportAddressList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_ADDRESS_DETAIL(?1,?2,?3))")
    List<DetailTransportAddressList> queryTransportAddressDetailListByUserID(Long user_id, Long role_id, Long cnt_add_id);
}
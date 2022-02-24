package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientAddressList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientAddressListRepository extends CrudRepository<DetailClientAddressList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCONTRAGENT_ADDRESS_DETAIL(?1,?2,?3))")
    List<DetailClientAddressList> queryClientAddressDetailListByUserID(Long user_id, Long role_id, Long cnt_add_id);
}
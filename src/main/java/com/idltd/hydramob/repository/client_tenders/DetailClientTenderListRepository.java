package com.idltd.hydramob.repository.client_tenders;

import com.idltd.hydramob.entity.client_tenders.DetailClientTenderList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientTenderListRepository extends CrudRepository<DetailClientTenderList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VTENDER_USER_DETAIL(?1,?2,?3))")
    List<DetailClientTenderList> queryClientTenderDetailByID(Long user_id, Long role_id, Long req_id);
}
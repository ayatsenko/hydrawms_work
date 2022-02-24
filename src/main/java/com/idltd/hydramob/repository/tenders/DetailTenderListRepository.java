package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.tenders.DetailTenderList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTenderListRepository extends CrudRepository<DetailTenderList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TENDERS_VIEW.VTENDER_USER_DETAIL(?1,?2,?3))")
    List<DetailTenderList> queryTenderDetailByID(Long user_id, Long role_id, Long req_id);
}
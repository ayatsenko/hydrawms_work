package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.tenders.DetailTenderFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTenderFilesListRepository extends CrudRepository<DetailTenderFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_STORE_DETAIL(?1,?2,?3))")
    List<DetailTenderFilesList> queryTenderFileDetailByID(Long user_id, Long role_id, Long req_store_id);
}
package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.requests.DetailRequestFilesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRequestFilesListRepository extends CrudRepository<DetailRequestFilesList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vREQUEST_STORE_DETAIL(?1,?2,?3))")
    List<DetailRequestFilesList> queryRequestFileDetailByID(Long user_id, Long role_id, Long req_store_id);
}
package com.idltd.hydramob.repository.requests;

import com.idltd.hydramob.entity.RequestFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFileRepository extends CrudRepository<RequestFile, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_STORE where req_store_id = ?1")
    List<RequestFile> queryByReqStoreFileID(Long req_store_id);

    @Query(nativeQuery = true, value = "select * from REQUEST_STORE where req_store_id = ?1")
    RequestFile queryByReqStoreCurrFileID(Long req_store_id);
}

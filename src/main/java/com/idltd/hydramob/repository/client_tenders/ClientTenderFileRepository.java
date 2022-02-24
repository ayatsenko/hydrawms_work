package com.idltd.hydramob.repository.client_tenders;

import com.idltd.hydramob.entity.TenderFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientTenderFileRepository extends CrudRepository<TenderFile, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_STORE where req_store_id = ?1")
    List<TenderFile> queryByReqStoreFileID(Long req_store_id);
}

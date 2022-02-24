package com.idltd.hydramob.repository.tenders;

import com.idltd.hydramob.entity.requests.RequestFiascoStatusList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderFiascoStatusListRepository extends CrudRepository<RequestFiascoStatusList, Long> {
    @Query(nativeQuery = true, value = "select * from REQUEST_FIASCO_STATUS ORDER BY REQ_FIA_STATUS_ID")
    List<RequestFiascoStatusList> queryFiascoStatusAll();
}

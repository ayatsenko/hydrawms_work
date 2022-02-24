package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.ClientLawTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLawTypeListRepository extends CrudRepository<ClientLawTypeList, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT_LAW_TYPES ORDER BY CNT_LAW_TYPE_ID")
    List<ClientLawTypeList> queryGetAllLawTypeList();
}

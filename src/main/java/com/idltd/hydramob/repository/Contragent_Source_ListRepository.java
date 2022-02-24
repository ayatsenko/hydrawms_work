package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Contragent_Source_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Contragent_Source_ListRepository extends CrudRepository<Contragent_Source_List, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT_SOURCE where CNT_SOURCE_ID > 0 AND CLIENT_ID = 1")
    List<Contragent_Source_List> queryEditContragentSource();

    @Query(nativeQuery = true, value = "select * from CONTRAGENT_SOURCE where CNT_SOURCE_ID > 0 AND TRAN_ID = 1")
    List<Contragent_Source_List> queryTransportContragentSource();

    @Query(nativeQuery = true, value = "select * from CONTRAGENT_SOURCE where CNT_SOURCE_ID = ?1 AND CLIENT_ID = 1")
    List<Contragent_Source_List> queryCurrentContragentSource(Long cnt_source_id);
}

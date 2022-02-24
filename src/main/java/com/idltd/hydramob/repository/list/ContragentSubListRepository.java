package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ContragentSubList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContragentSubListRepository extends CrudRepository<ContragentSubList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_NEW_SUB_LIST(?1,?2)) ORDER BY CNT_NAME")
    List<ContragentSubList> queryCntSubList(Long user_id, Long role_id);
}

package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Contract_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Contract_Type_ListRepository extends CrudRepository<TMS_Contract_Type_List, Long> {
    @Query(nativeQuery = true, value = "select TMS_CONTT_ID, TMS_CONTT_SNAME from TMS_CONTRACTS_TYPE WHERE TMS_CONTT_ID = ?1")
    List<TMS_Contract_Type_List> queryTMSContTypeListByID(Long tms_contt_id);
}

package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Cars_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cars_Type_ListRepository extends CrudRepository<Cars_Type_List, Long> {
    @Query(nativeQuery = true, value = "select CNTC_TYPE_ID, CNTC_TYPE_SNAME from CONTRAGENT_CAR_TYPE WHERE CNTC_TYPE_ID = ?1")
    List<Cars_Type_List> queryClientCarsTypeByID(Long cntc_type_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CARS_TYPE_LIST(?1,?2,?3))")
    List<Cars_Type_List> queryCurClientCarsTypeByID(Long user_id, Long role_id, Long cnt_id);
}

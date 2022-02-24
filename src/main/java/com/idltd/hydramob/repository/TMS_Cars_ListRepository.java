package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.TMS_Cars_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Cars_ListRepository extends CrudRepository<TMS_Cars_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CARS_LIST(?1,?2,?3,?4))")
    List<TMS_Cars_List> queryTMSClientCarListByUserID(Long user_id, Long role_id, Long cnt_id, Long cntc_type_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CARS_ALL_LIST(?1,?2,?3))")
    List<TMS_Cars_List> queryTMSClientAllCarListByUserID(Long user_id, Long role_id, Long cnt_id);
}

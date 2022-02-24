package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.TMS_Drivers_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Drivers_ListRepository extends CrudRepository<TMS_Drivers_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_DRIVERS_LIST(?1,?2,?3))")
    List<TMS_Drivers_List> queryTMSClientDriversListByUserID(Long user_id, Long role_id, Long cnt_id);
}

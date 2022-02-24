package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.TMS_Client_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Client_ListRepository extends CrudRepository<TMS_Client_List, Long> {
    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vCLIENT_LIST(?1,?2))")
    List<TMS_Client_List> queryTMSClientByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vCLIENT_ONE_LIST(?1,?2,?3))")
    List<TMS_Client_List> queryTMSOneClientByUserID(Long user_id, Long role_id, Long cnt_id);

    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from TABLE(PKG_TMS_VIEW.vCLIENT_FILTERED_LIST(?1,?2,?3))")
    List<TMS_Client_List> queryTMSClientFilteredByUserID(Long user_id, Long role_id, String cnt_filter);
}

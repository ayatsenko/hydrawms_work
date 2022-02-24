package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Service_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Service_Type_ListRepository extends CrudRepository<Service_Type_List, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM CRM_SERVICE where SER_ID not in (select SER_ID from VCONTRAGENT_SERVICE_LINK where cnt_id = ?1)")
    List<Service_Type_List> queryNewSerIDByCnt(Long cnt_id);

    @Query(nativeQuery = true, value = "SELECT * FROM CRM_SERVICE where SER_ID not in (select SER_ID from VUSERS_CRM_SERVICE_LINK where user_id = ?1)")
    List<Service_Type_List> queryNewSerIDByOpsID(Long user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM CRM_SERVICE where SER_ID in (select SER_ID from VUSERS_CRM_SERVICE_LINK where user_id = ?1)")
    List<Service_Type_List> querySerListByOpsID(Long user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM CRM_SERVICE where SER_ID = ?1")
    List<Service_Type_List> queryGetByID(Long ser_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_SERVICE_LIST(?1,?2,?3)) ORDER BY SER_NAME")
    List<Service_Type_List> queryTMSSerIDByUserID(Long user_id, Long role_id, Long form_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REPORTS_VIEW2.VREPORT_SERVICE(?1)) ORDER BY SER_NAME")
    List<Service_Type_List> queryReportSerIDByUserID(Long user_id);

    @Query(nativeQuery = true, value = "SELECT PKG_USERS_VIEW.VUSER_SERVICE_CHECK(?1,?2,?3) FROM DUAL")
    Long queryValSerIDByUserID(Long user_id, Long role_id, Long ser_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REPORTS_VIEW2.VREPORT_SERVICE(?1)) WHERE SER_ID <> 0 AND SER_ID NOT IN (select SER_ID from VCONTRAGENT_SERVICE_LINK where cnt_id = ?2) ORDER BY SER_NAME")
    List<Service_Type_List> queryNewOPSSerIDByUserID(Long user_id, Long cnt_id);

    @Query(nativeQuery = true, value = "SELECT * FROM CRM_SERVICE where VT_ID <> 1")
    List<Service_Type_List> queryGetAllService();

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_REQUEST_VIEW.vREQUEST_SERVICE_LIST(?1,?2,?3,?4)) ORDER BY SER_NAME")
    List<Service_Type_List> queryGetAllUserService(Long user_id, Long role_id, Long req_user_id, Long cnt_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vUSER_SERVICE_LIST(?1,?2)) ORDER BY SER_NAME")
    List<Service_Type_List> queryGetUserService(Long user_id, Long role_id);
}

package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.MenuTMSBrockersClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSBrockersClaimsTimeLogsRepository extends CrudRepository<MenuTMSBrockersClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuTMSBrockersClaimsTimeLogs> queryClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
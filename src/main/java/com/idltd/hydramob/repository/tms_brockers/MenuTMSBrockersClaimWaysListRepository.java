package com.idltd.hydramob.repository.tms_brockers;

import com.idltd.hydramob.entity.tms_brockers.MenuTMSBrockersClaimWaysList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSBrockersClaimWaysListRepository extends CrudRepository<MenuTMSBrockersClaimWaysList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_WAYS_MENU(?1,?2,?3))")
    List<MenuTMSBrockersClaimWaysList> queryClaimWaysMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
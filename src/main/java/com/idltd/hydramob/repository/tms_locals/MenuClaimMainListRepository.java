package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.TMSMenuClaimsMainList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimMainListRepository extends CrudRepository<TMSMenuClaimsMainList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<TMSMenuClaimsMainList> queryClaimFLDMenuListByUserID(Long user_id, Long role_id, Long clm_id, String start_date, String end_date, Long ser_id);
}
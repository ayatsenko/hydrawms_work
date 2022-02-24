package com.idltd.hydramob.repository.tms_ua;

import com.idltd.hydramob.entity.tms_ua.MenuClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimsListRepository extends CrudRepository<MenuClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuClaimsList> queryClaimsMenuListByUserID(Long user_id, Long role_id, Long full_world_filter_id, String full_world_filter_start_date, String full_world_filter_end_date, Long ser_id);
}
package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.MenuPartWorldClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuPartWorldClaimsListRepository extends CrudRepository<MenuPartWorldClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuPartWorldClaimsList> queryClaimsMenuListByUserID(Long user_id, Long role_id, Long full_world_filter_id, String full_world_filter_start_date, String full_world_filter_end_date, Long ser_id);
}
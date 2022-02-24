package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimsListRepository extends CrudRepository<MenuFLGClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLG_MENU_TIME(?1,?2,?3,?4,?5,?6))")
    List<MenuFLGClaimsList> queryFLGClaimsMenuListByUserID(
            Long user_id, Long role_id, Long part_world_filter_id, String part_world_filter_start_date, String part_world_filter_end_date, Long ser_id
    );
}
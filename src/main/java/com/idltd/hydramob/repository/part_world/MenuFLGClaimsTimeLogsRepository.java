package com.idltd.hydramob.repository.part_world;

import com.idltd.hydramob.entity.part_world.MenuFLGClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLGClaimsTimeLogsRepository extends CrudRepository<MenuFLGClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FLG_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuFLGClaimsTimeLogs> queryClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
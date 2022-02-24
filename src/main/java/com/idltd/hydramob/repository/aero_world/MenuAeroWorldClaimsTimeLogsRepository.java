package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.MenuAeroWorldClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAeroWorldClaimsTimeLogsRepository extends CrudRepository<MenuAeroWorldClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FA_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuAeroWorldClaimsTimeLogs> queryFAClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
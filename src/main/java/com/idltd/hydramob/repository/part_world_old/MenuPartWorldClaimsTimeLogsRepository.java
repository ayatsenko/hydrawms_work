package com.idltd.hydramob.repository.part_world_old;

import com.idltd.hydramob.entity.part_world_old.MenuPartWorldClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuPartWorldClaimsTimeLogsRepository extends CrudRepository<MenuPartWorldClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuPartWorldClaimsTimeLogs> queryClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
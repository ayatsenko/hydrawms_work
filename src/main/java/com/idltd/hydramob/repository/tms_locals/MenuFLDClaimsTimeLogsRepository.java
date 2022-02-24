package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.full_world.MenuClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimsTimeLogsRepository extends CrudRepository<MenuClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FLD_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuClaimsTimeLogs> queryFLDClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
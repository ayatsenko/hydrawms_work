package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.MenuSeaShipsClaimsTimeLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuSeaShipsClaimsTimeLogsRepository extends CrudRepository<MenuSeaShipsClaimsTimeLogs, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_FS_CLIMS_TIME_LOG(?1,?2,?3))")
    List<MenuSeaShipsClaimsTimeLogs> queryFLDClaimsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
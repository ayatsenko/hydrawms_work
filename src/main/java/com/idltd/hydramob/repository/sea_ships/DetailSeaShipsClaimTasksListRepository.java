package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.DetailSeaShipsClaimTasksList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSeaShipsClaimTasksListRepository extends CrudRepository<DetailSeaShipsClaimTasksList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TASKS_DETAIL(?1,?2,?3))")
    List<DetailSeaShipsClaimTasksList> queryClaimTasksDetailListByUserID(
            Long user_id, Long role_id, Long clmtl_id
    );
}
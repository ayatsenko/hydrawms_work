package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.DetailSeaShipsClaimsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSeaShipsClaimsListRepository extends CrudRepository<DetailSeaShipsClaimsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FS_DETAIL(?1,?2,?3))")
    List<DetailSeaShipsClaimsList> queryDetailFSClaimsListByUserID(
            Long user_id, Long role_id, Long clm_id
    );
}
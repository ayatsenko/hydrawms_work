package com.idltd.hydramob.repository.wms_pairs;

import com.idltd.hydramob.entity.wms_pairs.MenuWMSPairsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSPairsMainRepository extends CrudRepository<MenuWMSPairsMain, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_PAIRS_MENU(?1,?2))")
    List<MenuWMSPairsMain> queryWMSPairMainByID(
            Long user_id,
            Long role_id
    );
}
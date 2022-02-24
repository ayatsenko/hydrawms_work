package com.idltd.hydramob.repository.wms_pairs;

import com.idltd.hydramob.entity.wms_pairs.MenuWMSPairsUsers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuWMSPairsUsersRepository extends CrudRepository<MenuWMSPairsUsers, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_TYPE_FILE_PAIR_USERS_MENU(?1,?2,?3))")
    List<MenuWMSPairsUsers> queryWMSPairUsersByID(
            Long user_id,
            Long role_id,
            Long pair_id
    );
}
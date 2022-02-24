package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.TMS_Incoterms_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Incoterms_ListRepository extends CrudRepository<TMS_Incoterms_List, Long> {
    @Query(nativeQuery = true, value = "select * from ZCRM.TMS_CLAIMS_INCOTERMS")
    List<TMS_Incoterms_List> queryTMSIncotersByUserID(Long user_id, Long role_id);
}

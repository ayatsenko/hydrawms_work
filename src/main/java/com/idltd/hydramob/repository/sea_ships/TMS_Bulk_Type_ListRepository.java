package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.TMS_Bulk_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Bulk_Type_ListRepository extends CrudRepository<TMS_Bulk_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from ZCRM.TMS_CLAIMS_BULK_TYPE")
    List<TMS_Bulk_Type_List> queryTMSBulkTypeByUserID(Long user_id, Long role_id);
}

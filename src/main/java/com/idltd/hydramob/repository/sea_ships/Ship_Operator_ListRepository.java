package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.Ship_Operator_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ship_Operator_ListRepository extends CrudRepository<Ship_Operator_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_SHIP_OPERATORS(?1,?2))")
    List<Ship_Operator_List> queryShipOperatorByUserID(Long user_id, Long role_id);
}

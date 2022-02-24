package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.Ships_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ships_ListRepository extends CrudRepository<Ships_List, Long> {
    @Query(nativeQuery = true, value = "select * from ZCRM.CONTRAGENT_SHIPS WHERE CNT_ID = ?1")
    List<Ships_List> queryShipsByUserID(Long ship_operator_id);
}

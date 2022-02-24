package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.TMS_Sea_Agent_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TMS_Sea_Agent_ListRepository extends CrudRepository<TMS_Sea_Agent_List, Long> {
    @Query(nativeQuery = true, value = "select CNT_ID, CNT_NAME from ZCRM.CONTRAGENT WHERE SEA_AGENT_ID = 1")
    List<TMS_Sea_Agent_List> queryTMSSeaAgentByUserID(Long user_id, Long role_id);;
}

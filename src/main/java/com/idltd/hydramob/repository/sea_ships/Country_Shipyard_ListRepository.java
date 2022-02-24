package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.Country_Shipyard_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Country_Shipyard_ListRepository extends CrudRepository<Country_Shipyard_List, Long> {
    @Query(nativeQuery = true, value = "select * from COUNTRY_SHIPYARDS WHERE COUNTRY_ID = ?1")
    List<Country_Shipyard_List> queryAllContryShipyardList(Long country_id);
}

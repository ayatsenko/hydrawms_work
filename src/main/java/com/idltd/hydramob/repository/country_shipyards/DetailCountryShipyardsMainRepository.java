package com.idltd.hydramob.repository.country_shipyards;

import com.idltd.hydramob.entity.country_shipyards.DetailCountryShipyardsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCountryShipyardsMainRepository extends CrudRepository<DetailCountryShipyardsMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_LISTS_VIEW.vCOUNTRY_SHIPYARDS_DETAIL(?1,?2,?3))")
    List<DetailCountryShipyardsMain> queryDetailCountryShipyardsMainByUserID(
            Long user_id, Long role_id, Long shipyard_id
    );
}
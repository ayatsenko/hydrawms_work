package com.idltd.hydramob.repository.country_shipyards;

import com.idltd.hydramob.entity.country_shipyards.MenuCountryShipyardsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCountryShipyardsMainRepository extends CrudRepository<MenuCountryShipyardsMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_LISTS_VIEW.vCOUNTRY_SHIPYARDS_MENU(?1,?2))")
    List<MenuCountryShipyardsMain> queryCountryShipyardsMainByUserID(
            Long user_id, Long role_id
    );
}
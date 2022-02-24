package com.idltd.hydramob.repository.country_airports;

import com.idltd.hydramob.entity.country_airports.MenuCountryAirportsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCountryAirportsMainRepository extends CrudRepository<MenuCountryAirportsMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_LISTS_VIEW.vCOUNTRY_AEROPORTS_MENU(?1,?2))")
    List<MenuCountryAirportsMain> queryCountryAirportsMainByUserID(Long user_id, Long role_id);
}
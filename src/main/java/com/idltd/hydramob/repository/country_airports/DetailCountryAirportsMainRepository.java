package com.idltd.hydramob.repository.country_airports;

import com.idltd.hydramob.entity.country_airports.DetailCountryAirportsMain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCountryAirportsMainRepository extends CrudRepository<DetailCountryAirportsMain, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_LISTS_VIEW.vCOUNTRY_AEROPORTS_DETAIL(?1,?2,?3))")
    List<DetailCountryAirportsMain> queryDetailCountryAirportsMainByUserID(Long user_id, Long role_id, Long aeroport_id);
}
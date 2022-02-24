package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query(nativeQuery = true, value = "select * from Locations where loc_partnership like ?1 or loc_partnership is null order by loc_id")
    List<Location> queryByLikePartnership(String loc_partnership);

    @Query(nativeQuery = true, value = "select loc.* from Locations loc, USERS us, USERS_LOCATIONS ul where us.USERNAME=?1 and ul.user_id = us.ID and loc.LOC_ID = ul.LOC_ID order by loc.loc_id")
    List<Location> queryByUserName(String username);

    @Query(nativeQuery = true, value = "select loc.* from Locations loc, USERS_LOCATIONS ul where ul.user_id = ?1 and loc.LOC_ID = ul.LOC_ID order by loc.loc_id")
    List<Location> queryByUserId(long user_id);

    @Query(nativeQuery = true, value = "select loc.* from Locations loc where loc.LOC_ID not in (select ul.LOC_ID from USERS_LOCATIONS ul where ul.user_id = ?1) order by loc.loc_id")
    List<Location> queryWithoutUserLocation(long user_id);

    @Query(nativeQuery = true, value = "SELECT loc.* FROM LOCATIONS loc, WORKPLACE wp where wp.wp_id=?1 and loc.loc_id = wp.loc_id")
    Location queryByWp_id(long wp_id);

    @Query(nativeQuery = true, value = "SELECT * FROM LOCATIONS WHERE loc_id = ?1")
    Location queryLocByID(long loc_id);
}

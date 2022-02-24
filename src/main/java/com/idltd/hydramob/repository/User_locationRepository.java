package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_locationRepository extends CrudRepository<User_location, Long> {

    @Query(nativeQuery = true, value = "select * from USERS_LOCATIONS where user_id=?1 and loc_id=?2")
    User_location queryByUser_idAndAndLoc_id(Long user_id, Long loc_id);
}

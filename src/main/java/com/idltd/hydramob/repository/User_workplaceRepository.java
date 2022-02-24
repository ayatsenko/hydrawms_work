package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_workplace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_workplaceRepository extends CrudRepository<User_workplace, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM USER_WORKPLACE uwp where uwp.user_id=?1 and uwp.WP_ID=?2")
    User_workplace queryByUser_idandLoc_id(Long user_id, Long loc_id);

}

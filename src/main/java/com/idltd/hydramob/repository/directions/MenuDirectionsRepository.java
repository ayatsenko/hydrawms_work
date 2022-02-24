package com.idltd.hydramob.repository.directions;

import com.idltd.hydramob.entity.directions.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDirectionsRepository extends CrudRepository<Direction, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_DIRECTIONS(?1,?2))")
    List<Direction> queryMenuDirectionByID(Long user_id, Long role_id);
}
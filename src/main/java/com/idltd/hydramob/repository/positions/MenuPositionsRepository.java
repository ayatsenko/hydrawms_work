package com.idltd.hydramob.repository.positions;

import com.idltd.hydramob.entity.positions.Positions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuPositionsRepository extends CrudRepository<Positions, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_POSITIONS(?1,?2))")
    List<Positions> queryMenuPositionByID(Long user_id, Long role_id);
}
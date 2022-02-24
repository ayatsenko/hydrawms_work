package com.idltd.hydramob.repository.service_types;

import com.idltd.hydramob.entity.service_types.MenuServiceTypeSystems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MenuServiceTypeSystemsRepository extends CrudRepository<MenuServiceTypeSystems, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_SERVICE_SYSTEM_MENU(?1,?2,?3))")
    List<MenuServiceTypeSystems> queryMenuServiceTypeSystemByID(Long user_id, Long role_id, Long ser_id);
}
package com.idltd.hydramob.repository.service_types;

import com.idltd.hydramob.entity.service_types.ServiceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailServiceTypeRepository extends CrudRepository<ServiceType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_SERVICE(?1,?2)) where SER_ID = ?3")
    List<ServiceType> queryDetailServiceTypeByID(Long user_id, Long role_id, Long ser_id);
}
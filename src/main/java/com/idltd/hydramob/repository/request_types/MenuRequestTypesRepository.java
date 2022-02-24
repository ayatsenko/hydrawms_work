package com.idltd.hydramob.repository.request_types;

import com.idltd.hydramob.entity.request_types.RequestType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestTypesRepository extends CrudRepository<RequestType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_REQUEST_TYPES(?1,?2))")
    List<RequestType> queryMenuRequestTypeByID(Long user_id, Long role_id);
}
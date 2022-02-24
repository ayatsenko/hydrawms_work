package com.idltd.hydramob.repository.request_statuses;

import com.idltd.hydramob.entity.request_statuses.RequestStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestStatusesRepository extends CrudRepository<RequestStatus, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_REQUEST_STATUSES(?1,?2))")
    List<RequestStatus> queryMenuRequestStatusesByID(Long user_id, Long role_id);
}
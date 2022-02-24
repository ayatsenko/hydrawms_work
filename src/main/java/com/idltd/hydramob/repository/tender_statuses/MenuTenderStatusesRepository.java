package com.idltd.hydramob.repository.tender_statuses;

import com.idltd.hydramob.entity.tender_statuses.TenderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTenderStatusesRepository extends CrudRepository<TenderStatus, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_USERS_VIEW.vCRM_TENDER_STATUSES(?1,?2))")
    List<TenderStatus> queryMenuTenderStatusesByID(Long user_id, Long role_id);
}
package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuClaimTransPointsTemp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimTransPointsTempRepository extends CrudRepository<MenuClaimTransPointsTemp, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_TRANS_TEMP(?1,?2))")
    List<MenuClaimTransPointsTemp> queryFLDClaimTransPointsTempMenuListByUserID(Long user_id, Long role_id);
}
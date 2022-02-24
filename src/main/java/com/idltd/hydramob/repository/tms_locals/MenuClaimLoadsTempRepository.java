package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuClaimLoadsTemp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimLoadsTempRepository extends CrudRepository<MenuClaimLoadsTemp, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_FLD_LOADS_TEMP_MENU(?1,?2))")
    List<MenuClaimLoadsTemp> queryFLDClaimLoadsTempMenuListByUserID(Long user_id, Long role_id);
}
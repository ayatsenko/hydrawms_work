package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuFLDClaimLoadsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimLoadsListRepository extends CrudRepository<MenuFLDClaimLoadsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_LOADS_MENU(?1,?2,?3))")
    List<MenuFLDClaimLoadsList> queryFLDClaimLoadsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
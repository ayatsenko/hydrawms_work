package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.MenuFLDClaimTransList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimTransListRepository extends CrudRepository<MenuFLDClaimTransList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_TRANS_MENU(?1,?2,?3))")
    List<MenuFLDClaimTransList> queryFLDClaimTransMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.full_world.MenuClaimErrorsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFLDClaimErrorsListRepository extends CrudRepository<MenuClaimErrorsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_ERROR_MENU(?1,?2,?3))")
    List<MenuClaimErrorsList> queryFLDClaimErrorsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
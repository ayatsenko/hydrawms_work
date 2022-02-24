package com.idltd.hydramob.repository.full_world;

import com.idltd.hydramob.entity.full_world.MenuClaimErrorsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuClaimErrorsListRepository extends CrudRepository<MenuClaimErrorsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_ERROR_MENU(?1,?2,?3))")
    List<MenuClaimErrorsList> queryClaimErrorsMenuListByUserID(Long user_id, Long role_id, Long clm_id);
}
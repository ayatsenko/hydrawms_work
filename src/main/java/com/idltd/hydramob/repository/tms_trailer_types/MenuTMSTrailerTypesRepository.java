package com.idltd.hydramob.repository.tms_trailer_types;

import com.idltd.hydramob.entity.tms_trailer_types.MenuTMSTrailerType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSTrailerTypesRepository extends CrudRepository<MenuTMSTrailerType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CONT_TRAILER_TYPE_MENU(?1,?2))")
    List<MenuTMSTrailerType> queryMenuTMSTrailerTypeByID(Long user_id, Long role_id);
}
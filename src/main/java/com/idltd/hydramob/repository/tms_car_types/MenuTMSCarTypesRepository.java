package com.idltd.hydramob.repository.tms_car_types;

import com.idltd.hydramob.entity.tms_car_types.MenuTMSCarType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSCarTypesRepository extends CrudRepository<MenuTMSCarType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CONT_CAR_TYPE_MENU(?1,?2))")
    List<MenuTMSCarType> queryMenuTMSCarTypeByID(Long user_id, Long role_id);
}
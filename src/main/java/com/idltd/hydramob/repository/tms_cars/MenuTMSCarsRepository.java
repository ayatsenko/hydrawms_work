package com.idltd.hydramob.repository.tms_cars;

import com.idltd.hydramob.entity.tms_cars.MenuTMSCars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSCarsRepository extends CrudRepository<MenuTMSCars, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_POLAND_VIEW.VTMS_CAR_MENU(?1,?2))")
    List<MenuTMSCars> queryMenuTMSCarsByID(Long user_id, Long role_id);
}
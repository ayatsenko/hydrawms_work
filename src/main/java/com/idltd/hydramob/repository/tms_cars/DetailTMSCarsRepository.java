package com.idltd.hydramob.repository.tms_cars;

import com.idltd.hydramob.entity.tms_cars.DetailTMSCars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSCarsRepository extends CrudRepository<DetailTMSCars, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_POLAND_VIEW.VTMS_CAR_DETAIL(?1,?2,?3))")
    List<DetailTMSCars> queryDetailTMSCarsByID(Long user_id, Long role_id, Long tms_car_id);
}
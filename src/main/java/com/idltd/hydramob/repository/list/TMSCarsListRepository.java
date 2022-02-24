package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMSCarsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TMSCarsListRepository extends CrudRepository<TMSCarsList, Long> {
    @Query(nativeQuery = true, value = "select * from TMS_CARS WHERE VT_ID <> 1")
    List<TMSCarsList> queryTMSCarsListGetAll();

    @Query(nativeQuery = true, value = "select * from TMS_CARS WHERE TMS_CAR_ID = ?1")
    List<TMSCarsList> queryTMSCarsListGetByID(Long tms_car_id);
}

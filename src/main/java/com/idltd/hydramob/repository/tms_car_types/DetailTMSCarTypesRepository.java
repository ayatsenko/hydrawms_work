package com.idltd.hydramob.repository.tms_car_types;

import com.idltd.hydramob.entity.tms_car_types.DetailTMSCarType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSCarTypesRepository extends CrudRepository<DetailTMSCarType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CONT_CAR_TYPE_DETAIL(?1,?2,?3))")
    List<DetailTMSCarType> queryDetailTMSCarTypeByID(Long user_id, Long role_id, Long clmc_type_id);
}
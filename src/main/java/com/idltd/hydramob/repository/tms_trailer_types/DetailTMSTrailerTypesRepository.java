package com.idltd.hydramob.repository.tms_trailer_types;

import com.idltd.hydramob.entity.tms_trailer_types.DetailTMSTrailerType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSTrailerTypesRepository extends CrudRepository<DetailTMSTrailerType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CONT_TRAILER_TYPE_DETAIL(?1,?2,?3))")
    List<DetailTMSTrailerType> queryDetailTMSTrailerTypeByID(Long user_id, Long role_id, Long cntt_type_id);
}
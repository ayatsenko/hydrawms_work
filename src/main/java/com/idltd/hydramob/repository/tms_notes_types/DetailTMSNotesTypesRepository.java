package com.idltd.hydramob.repository.tms_notes_types;

import com.idltd.hydramob.entity.tms_notes_types.DetailTMSNotesTypes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTMSNotesTypesRepository extends CrudRepository<DetailTMSNotesTypes, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_NOTES_TYPES_DETAIL(?1,?2,?3))")
    List<DetailTMSNotesTypes> queryDetailTMSNotesTypesByID(Long user_id, Long role_id, Long clm_notes_type_id);
}
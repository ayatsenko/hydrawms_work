package com.idltd.hydramob.repository.tms_notes_types;

import com.idltd.hydramob.entity.tms_notes_types.MenuTMSNotesTypes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTMSNotesTypesRepository extends CrudRepository<MenuTMSNotesTypes, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_TMS_VIEW.vTMS_CLAIMS_NOTES_TYPES_MENU(?1,?2))")
    List<MenuTMSNotesTypes> queryMenuTMSNotesTypesByID(Long user_id, Long role_id);
}
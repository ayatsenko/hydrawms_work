package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.TMS_Notes_Types_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TMS_Notes_Types_ListRepository extends CrudRepository<TMS_Notes_Types_List, Long> {
    @Query(nativeQuery = true,
            value = "select * from TMS_CLAIMS_NOTES_TYPES WHERE clm_notes_type_id not in (SELECT CLM_NOTES_TYPE_ID FROM TMS_CLAIMS_NOTES_LINK WHERE CLM_ID = ?1) AND VT_ID <> 1")
    List<TMS_Notes_Types_List> queryTMSNewNotesListByID(Long clm_id);

    @Query(nativeQuery = true,
            value = "select * from TMS_CLAIMS_NOTES_TYPES WHERE clm_notes_type_id = ?1")
    List<TMS_Notes_Types_List> queryTMSNotesListByID(Long clm_notes_type_id);
}

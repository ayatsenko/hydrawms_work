package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Trailers_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Trailers_Type_ListRepository extends CrudRepository<Trailers_Type_List, Long> {
    @Query(nativeQuery = true, value = "select CNTT_TYPE_ID, CNTT_TYPE_SNAME from CONTRAGENT_TRAILER_TYPE WHERE CNTT_TYPE_ID = ?1")
    List<Trailers_Type_List> queryClientTrailersTypeByID(Long cntt_type_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vTMS_TRAILERS_TYPE_LIST(?1,?2,?3))")
    List<Trailers_Type_List> queryCurClientTrailersTypeByID(Long user_id, Long role_id, Long cnt_id);
}

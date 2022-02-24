package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.DetailClientReferenceList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailClientReferenceListRepository extends CrudRepository<DetailClientReferenceList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_REFENCE_DETAIL(?1,?2,?3))")
    List<DetailClientReferenceList> queryClientDetailReferenceListByUserID(Long user_id, Long role_id, Long cnt_ref_id);
}
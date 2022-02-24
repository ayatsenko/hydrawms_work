package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Contragent_Status_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Contragent_Status_ListRepository extends CrudRepository<Contragent_Status_List, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT_STATUS where cs_id = ?1")
    List<Contragent_Status_List> queryGetCurrStatus(Long cs_id);

    @Query(nativeQuery = true, value = "select * from CONTRAGENT_STATUS where cs_id in (-4,-3,-2,0,?1)")
    List<Contragent_Status_List> queryGetAdminStatus(Long cs_id);

    @Query(nativeQuery = true, value = "select * from CONTRAGENT_STATUS")
    List<Contragent_Status_List> queryGetAllStatus();

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTRAGENT_STATUS_LIST(?1,?2,?3))")
    List<Contragent_Status_List> queryGetCurrStatusByUserID(Long user_id, Long role_id, Long cs_id);
}

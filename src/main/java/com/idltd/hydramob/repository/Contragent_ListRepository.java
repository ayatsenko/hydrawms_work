package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Contragent_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Contragent_ListRepository extends CrudRepository<Contragent_List, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT where cnt_id = ?1")
    List<Contragent_List> queryCntByID(Long cnt_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONT_USER_LINK_LIST(?1))")
    List<Contragent_List> queryCntByUserID(Long user_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONT_USER_LINK_NEW_LIST(?1))")
    List<Contragent_List> queryNewCntByUserID(Long user_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSERS_NEW_CLIENT_LIST(?1,?2,?3))")
    List<Contragent_List> queryUserListNewCntByUserID(Long user_id, Long role_id, Long cur_user_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSERS_CLIENT_ONE(?1,?2,?3))")
    List<Contragent_List> queryUserCntByUserID(Long user_id, Long role_id, Long cnt_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_NEW_SUB_LIST(?1,?2))")
    List<Contragent_List> queryCntSubList(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vEXCHANGE_SERVICE_WEB_CONTRAGENT_LIST(?1,?2))")
    List<Contragent_List> queryCntWMSServiceList(Long user_id, Long role_id);
}

package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ContragentContactList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContragentContactListRepository extends CrudRepository<ContragentContactList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_CONT_NEW_USER_LIST(?1,?2,?3,?4))")
    List<ContragentContactList> queryCntContactNewByID(Long user_id, Long role_id, Long cnt_id, Long pr_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTR_CONT_USER_LIST(?1,?2,?3,?4,?5))")
    List<ContragentContactList> queryCntContactByID(Long user_id, Long role_id, Long cnt_id, Long pr_id, Long prrl_id);
}

package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Mail_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Mail_ListRepository extends CrudRepository<Mail_List, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_MAIL.VSEND_MAIL(?1,?2))")
    List<Mail_List> queryRequstChatMail(Long type_id, Long req_chat_id);

    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_MAIL.VSEND_REQUEST_MAIL(?1,?2))")
    List<Mail_List> queryRequstMail(Long type_id, Long req_id);

    @Query(nativeQuery = true, value = "SELECT USER_ID, USER_EMAIL USER_MAIL, EMAIL_THEME MAIL_SUBJECT, EMAIL_TEXT MAIL_TEXT FROM SHEDULER_MAIL_DETAIL WHERE SEM_ID in (SELECT SE_FK_ID FROM SHEDULER_EVENT_LINK WHERE SE_ID = ?1) ORDER BY USER_ID")
    List<Mail_List> queryRequstAndTenderMail(Long se_id);
}

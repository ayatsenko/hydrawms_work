package com.idltd.hydramob.Sheduler.NP;

import javax.persistence.*;

@Entity
@Table( name = "EXCHANGE_SERVICE_NP_POST_LIST", schema = "HCOMM")
public class NPInternetDocumentArch {
    @Id
    @Column(name = "POST_LIST_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ", sequenceName = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_TYPE_FIL6_SEQ")
    public Long post_list_id;

    @Column(name = "DB_USER_ID", nullable = false)
    public Long db_user_id;

    @Column(name = "DB_ROLE_ID", nullable = false)
    public Long db_role_id;

    @Column(name = "ADD_DATE", nullable = false)
    public String add_date;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "POST_REF")
    public String post_ref;

    @Column(name = "DOC_NUM")
    public String doc_num;

    @Column(name = "DOC_DATE")
    public String doc_date;

    @Column(name = "REGIST_NUM")
    public String regist_num;

    @Column(name = "RECIPIENT_PHONE")
    public String recipient_phone;

    @Column(name = "RECIPIENT_PERSON")
    public String recipient_person;

    public Long getPost_list_id() {
        return post_list_id;
    }

    public void setPost_list_id(Long post_list_id) {
        this.post_list_id = post_list_id;
    }

    public Long getDb_user_id() {
        return db_user_id;
    }

    public void setDb_user_id(Long db_user_id) {
        this.db_user_id = db_user_id;
    }

    public Long getDb_role_id() {
        return db_role_id;
    }

    public void setDb_role_id(Long db_role_id) {
        this.db_role_id = db_role_id;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public Long getCnt_id() {
        return cnt_id;
    }

    public void setCnt_id(Long cnt_id) {
        this.cnt_id = cnt_id;
    }

    public String getPost_ref() {
        return post_ref;
    }

    public void setPost_ref(String post_ref) {
        this.post_ref = post_ref;
    }

    public String getDoc_num() {
        return doc_num;
    }

    public void setDoc_num(String doc_num) {
        this.doc_num = doc_num;
    }

    public String getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(String doc_date) {
        this.doc_date = doc_date;
    }

    public String getRegist_num() {
        return regist_num;
    }

    public void setRegist_num(String regist_num) {
        this.regist_num = regist_num;
    }

    public String getRecipient_phone() {
        return recipient_phone;
    }

    public void setRecipient_phone(String recipient_phone) {
        this.recipient_phone = recipient_phone;
    }

    public String getRecipient_person() {
        return recipient_person;
    }

    public void setRecipient_person(String recipient_person) {
        this.recipient_person = recipient_person;
    }
}

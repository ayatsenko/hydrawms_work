package com.idltd.hydramob.repository.phone_book;

import com.idltd.hydramob.entity.phone_book.PhoneBookMenuDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDetailPhoneBookRepository extends CrudRepository<PhoneBookMenuDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.VPHONE_BOOK_USER_DETAIL(?1,?2,?3))")
    List<PhoneBookMenuDetail> queryPhoneBookMenuDetailByUserID(Long user_id, Long cc_id, Long pb_type_id);
}
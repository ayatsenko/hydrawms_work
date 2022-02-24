package com.idltd.hydramob.repository.phone_book;

import com.idltd.hydramob.entity.phone_book.PhoneBookMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuPhoneBookRepository extends CrudRepository<PhoneBookMenu, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.VPHONE_BOOK_USER(?1))")
    List<PhoneBookMenu> queryPhoneBookByUserID(Long user_id);
}
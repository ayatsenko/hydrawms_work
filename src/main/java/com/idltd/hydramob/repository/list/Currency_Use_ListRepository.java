package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Currency_Use_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Currency_Use_ListRepository extends CrudRepository<Currency_Use_List, Long> {
    @Query(nativeQuery = true, value = "select * from CURRENCY WHERE CURRENCY_USE_ID = 1")
    List<Currency_Use_List> queryAllCurrencyListByID();

    @Query(nativeQuery = true, value = "select * from CURRENCY WHERE CURRENCY_ID = ?1")
    List<Currency_Use_List> queryOneCurrencyListByID(Long currency_id);
}

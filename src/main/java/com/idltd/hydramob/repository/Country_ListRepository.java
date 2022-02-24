package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Country_Full_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Country_ListRepository extends CrudRepository<Country_Full_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCOUNTRY_LIST()) order by COUNTRY_NAMEUA")
    List<Country_Full_List> queryAllContryList();

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCOUNTRY_LIST()) WHERE COUNTRY_ID not in (SELECT COUNTRY_ID FROM CONTRAGENT_COUNTRY_LINK WHERE CNT_ID = ?1)")
    List<Country_Full_List> queryNewTransportContryList(Long cnt_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCOUNTRY_LIST()) WHERE COUNTRY_ID in (SELECT DISTINCT COUNTRY_ID FROM CONTRAGENT_COUNTRY_LINK)")
    List<Country_Full_List> queryAllTransportContryList();

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vCOUNTRY_LIST()) WHERE COUNTRY_ID = ?1")
    List<Country_Full_List> queryCurrentContryList(Long country_id);
}

package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.MenuTransportCountryList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTransportCountryListRepository extends CrudRepository<MenuTransportCountryList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vTRANSPORT_COUNTRY_MENU(?1,?2,?3))")
    List<MenuTransportCountryList> queryTransportCountryMenuListByUserID(Long user_id, Long role_id, Long cnt_id);
}
package com.idltd.hydramob.repository.transports;

import com.idltd.hydramob.entity.transports.DetailTransportCountryList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTransportsCountryListRepository extends CrudRepository<DetailTransportCountryList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vTRANSPORT_COUNTRY_DETAIL(?1,?2,?3))")
    List<DetailTransportCountryList> queryTransportCountryListDetaulByUserID(Long user_id, Long role_id, Long cntcl_id);
}
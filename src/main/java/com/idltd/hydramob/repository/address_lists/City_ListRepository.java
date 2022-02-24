package com.idltd.hydramob.repository.address_lists;

import com.idltd.hydramob.entity.address_lists.City_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface City_ListRepository extends CrudRepository<City_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vCOUNTRY_CITIES_LIST(?1,?2,?3))")
    List<City_List> queryAllCityListByID(Long user_id, Long role_id, Long country_id);
}

package com.idltd.hydramob.repository.address_lists;

import com.idltd.hydramob.entity.address_lists.House_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface House_ListRepository extends CrudRepository<House_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vCOUNTRY_HOUSE_LIST(?1,?2,?3,?4,?5))")
    List<House_List> queryAllCityStreetHouseListByID(Long user_id, Long role_id, Long country_id, Long city_id, Long street_id);
}

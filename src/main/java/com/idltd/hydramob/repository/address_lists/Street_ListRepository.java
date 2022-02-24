package com.idltd.hydramob.repository.address_lists;

import com.idltd.hydramob.entity.address_lists.Street_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Street_ListRepository extends CrudRepository<Street_List, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_TMS_VIEW.vCOUNTRY_STREET_LIST(?1,?2,?3,?4))")
    List<Street_List> queryAllCityStreetListByID(Long user_id, Long role_id, Long country_id, Long city_id);
}

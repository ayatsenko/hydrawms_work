package com.idltd.hydramob.repository.sea_ships;

import com.idltd.hydramob.entity.sea_ships.Containers_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Containers_Type_ListRepository extends CrudRepository<Containers_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from ZCRM.TMS_CONTAINERS_TYPE ORDER BY CLM_CONTAINERS_SNAME")
    List<Containers_Type_List> queryContainersTypeByUserID();
}

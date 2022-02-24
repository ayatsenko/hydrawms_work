package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Cars_Weight_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cars_Weight_Type_ListRepository extends CrudRepository<Cars_Weight_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT_CAR_TYPE WHERE VT_ID <> 1")
    List<Cars_Weight_Type_List> queryClientCarsWeightTypeByID();
}

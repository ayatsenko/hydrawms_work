package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.WeightTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightTypeRepository extends CrudRepository<WeightTypeList, Long> {
    @Query(nativeQuery = true, value = "select * from WEIGHT_TYPES WHERE VT_ID <> 1")
    List<WeightTypeList> queryWeightTypeAll();
}

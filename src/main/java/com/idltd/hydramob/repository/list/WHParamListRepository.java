package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.WHParamList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WHParamListRepository extends CrudRepository<WHParamList, Long> {
    @Query(nativeQuery = true, value = "select * from WH_PARAMETERS WHERE WH_PARAM_ID = ?1")
    List<WHParamList> queryWHParamOneByID(Long wh_param_id);
}

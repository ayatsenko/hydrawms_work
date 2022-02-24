package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ClientClassList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientClassListRepository extends CrudRepository<ClientClassList, Long> {
    @Query(nativeQuery = true, value = "select * from CONTRAGENT_CLASS ORDER BY CNT_CLASS_ID")
    List<ClientClassList> queryAllClientClassListBy();
}

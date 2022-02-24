package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.OwnerShip_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerShip_Type_ListRepository extends CrudRepository<OwnerShip_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from VOWNERSHIP_TYPE ORDER BY OWT_NAME")
    List<OwnerShip_Type_List> queryGetOwnList();

    @Query(nativeQuery = true, value = "select * from VOWNERSHIP_TYPE WHERE OWT_ID = ?1 ORDER BY OWT_NAME")
    List<OwnerShip_Type_List> queryCurrentOwnList(Long owt_id);
}

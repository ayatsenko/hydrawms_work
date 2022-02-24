package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Address_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Address_Type_ListRepository extends CrudRepository<Address_Type_List, Long> {
    @Query(nativeQuery = true, value = "select ADD_TYPE_ID, ADD_TYPE_SNAME from ADDRESS_TYPE WHERE ADD_TYPE_ID = ?1")
    List<Address_Type_List> queryClientAddressTypeByID(Long add_type_id);

}

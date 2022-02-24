package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.Users_KPI_Type_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Users_KPI_Type_ListRepository extends CrudRepository<Users_KPI_Type_List, Long> {
    @Query(nativeQuery = true, value = "select * from USERS_KPI_TYPE")
    List<Users_KPI_Type_List> queryUsersKPITypeAll();
}

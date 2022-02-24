package com.idltd.hydramob.repository.aero_world;

import com.idltd.hydramob.entity.aero_world.Country_Aeroport_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Country_Aeroport_ListRepository extends CrudRepository<Country_Aeroport_List, Long> {
    @Query(nativeQuery = true, value = "select * from COUNTRY_AEROPORTS WHERE COUNTRY_ID = ?1")
    List<Country_Aeroport_List> queryAllContryAeroportList(Long country_id);
}

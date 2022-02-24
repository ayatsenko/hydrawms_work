package com.idltd.hydramob.Sheduler.repository;


import com.idltd.hydramob.Sheduler.entity.GlovoupInv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlovoupInvRepository extends CrudRepository<GlovoupInv, Long> {

    @Query(nativeQuery = true, value = "select * from GlovoupInv where gui_date = (select max(gui_date) from GlovoupInv)")
    List<GlovoupInv> getCurrentInv();
}

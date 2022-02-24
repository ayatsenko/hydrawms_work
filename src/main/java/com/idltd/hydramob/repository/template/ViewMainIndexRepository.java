package com.idltd.hydramob.repository.template;

import com.idltd.hydramob.entity.template.MainPageSign;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewMainIndexRepository extends CrudRepository<MainPageSign, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_GRAPH_VIEW.vMainIndexRoleNew(?1,?2,?3,?4))")
    List<MainPageSign> queryMainIndexByID(Long user_id, Long role_id, String start_date, String end_date);
}
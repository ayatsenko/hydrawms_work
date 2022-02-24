package com.idltd.hydramob.repository.main;

import com.idltd.hydramob.entity.template.FiveLineBarGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRequestBarGraphRepository extends CrudRepository<FiveLineBarGraph, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_GRAPH_VIEW.vRequestBar(?1,?2,?3,?4))")
    List<FiveLineBarGraph> queryRequestBarGraphByID(Long user_id, Long role_id, String start_date, String end_date);
}
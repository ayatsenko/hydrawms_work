package com.idltd.hydramob.repository.main;

import com.idltd.hydramob.entity.template.OneLineGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewIndexGraphRepository extends CrudRepository<OneLineGraph, Long> {
    @Query(nativeQuery = true, value = "SELECT RN, LABELS, DATA1 FROM TABLE(PKG_GRAPH_VIEW.vIndexGraphRole(?1,?2,?3,?4,?5))")
    List<OneLineGraph> queryIndexListGraphGraphByID(Long user_id, Long role_id, Long id, String start_date, String end_date);
}
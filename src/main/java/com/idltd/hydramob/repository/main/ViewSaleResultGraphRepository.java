package com.idltd.hydramob.repository.main;

import com.idltd.hydramob.entity.template.LineGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewSaleResultGraphRepository extends CrudRepository<LineGraph, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_GRAPH_VIEW.vSaleResultGraphRole(?1,?2,?3))")
    List<LineGraph> querySaleResultGraphGraphByID(Long user_id, Long role_id, Long year);
}
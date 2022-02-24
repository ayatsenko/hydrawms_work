package com.idltd.hydramob.repository.main;

import com.idltd.hydramob.entity.template.BarGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewProdResultGraphRepository extends CrudRepository<BarGraph, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_GRAPH_VIEW.vProdResultBarRoleNew(?1,?2,?3))")
    List<BarGraph> queryProdResultGraphByID(Long user_id, Long role_id, Long year);
}
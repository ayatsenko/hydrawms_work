package com.idltd.hydramob.repository.clients_classes;

import com.idltd.hydramob.entity.template.OneLineGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphClientClassesRepository extends CrudRepository<OneLineGraph, Long> {
    @Query(nativeQuery = true, value = "SELECT RN, LABELS, DATA1 FROM TABLE(PKG_GRAPH_VIEW.vClientClassesGraph(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10))")
    List<OneLineGraph> queryIndexListGraphGraphByID(
            Long user_id, Long role_id, Long id, Long cnt_id, String start_date, String end_date, Long filter_check, Long filters_user_id, Long filters_status_id, Long filters_class_id);
}
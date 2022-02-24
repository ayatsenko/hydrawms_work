package com.idltd.hydramob.repository.main;

import com.idltd.hydramob.entity.template.OneLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewContragentWorldMapRepository extends CrudRepository<OneLine, Long> {
    @Query(nativeQuery = true, value = "SELECT PKG_GRAPH_VIEW.vContWorldMapLine(?1) LABELS FROM DUAL")
    List<OneLine> queryContWorldMapByID(Long user_id);
}
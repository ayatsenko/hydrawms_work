package com.idltd.hydramob.repository.sale_funnel;

import com.idltd.hydramob.entity.sale_funnel.vSaleFunnel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ViewSaleFunnelRepository extends CrudRepository<vSaleFunnel, Long> {
    @Query(nativeQuery = true, value = "select RN, LABELS, DATA1 FROM TABLE(PKG_GRAPH_VIEW.vSaleFunnelRole(?1,?2,?3,?4))")
    List<vSaleFunnel> queryUserSaleFunnel(Long user_id, Long role_id, String startdate, String enddate);
}

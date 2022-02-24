package com.idltd.hydramob.repository.kpi_facts;

import com.idltd.hydramob.entity.kpi_facts.MenuKPIFactsBranch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsBranchRepository extends CrudRepository<MenuKPIFactsBranch, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vKPI_FACTS_BRANCH_MAIN_MENU(?1,?2,?3,?4))")
    List<MenuKPIFactsBranch> queryKPIFactsBrnachById(
            Long user_id, Long role_id, Long kpi_facts_year, Long dep_id
    );
}
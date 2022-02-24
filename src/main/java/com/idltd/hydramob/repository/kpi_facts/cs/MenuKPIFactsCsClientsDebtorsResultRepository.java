package com.idltd.hydramob.repository.kpi_facts.cs;

import com.idltd.hydramob.entity.kpi_facts.cs.MenuKPIFactsCsClientsResultDebtors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuKPIFactsCsClientsDebtorsResultRepository extends CrudRepository<MenuKPIFactsCsClientsResultDebtors, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_KPI_VIEW.vUSERS_KPI_CLIENTS_PERS_DEBT_RES_MENU(?1,?2,?3,?4,?5))")
    List<MenuKPIFactsCsClientsResultDebtors> queryMenuKPIFactsCsClientsDebtorsResultByID(
            Long user_id, Long role_id, Long cur_user_id, Long year, Long month
    );
}
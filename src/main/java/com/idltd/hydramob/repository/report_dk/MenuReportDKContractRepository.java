package com.idltd.hydramob.repository.report_dk;

import com.idltd.hydramob.entity.report_dk.MenuReportDKContract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportDKContractRepository extends CrudRepository<MenuReportDKContract, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_134_CONTRACT_MENU_NEW(?1,?2,?3,?4,?5))")
    List<MenuReportDKContract> queryGetRepDKContractMenu(
            Long user_id, Long role_id, Long dk_paid_type_id, Long dkm_id, Long dk_ser_id
    );
}
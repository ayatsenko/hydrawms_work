package com.idltd.hydramob.repository.report_dk;

import com.idltd.hydramob.entity.report_dk.MenuReportDKContractComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuReportDKContractCommentRepository extends CrudRepository<MenuReportDKContractComment, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_REPORTS_VIEW2.vREPORT_134_Contract_COMMENT(?1,?2,?3,?4,?5,?6))")
    List<MenuReportDKContractComment> queryGetRepDKContractComment(
            Long user_id, Long role_id, Long dkm_id, Long dkmd_id, Long cnt_id, Long ser_id
    );
}
package com.idltd.hydramob.controller.report_dk;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.DK_Paid_Type_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Report_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.DK_Paid_Type_ListRepository;
import com.idltd.hydramob.repository.report_dk.*;
import com.idltd.hydramob.repository.main_variable.*;
import com.idltd.hydramob.utils.JSONDatatable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report_dk")
public class ReportDKController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private DK_Paid_Type_ListRepository dk_Paid_Type_ListRepository;
    private MenuReportDKMainRepository menuReportDKMainRepository;
    private MenuReportDKMainCommentRepository menuReportDKMainCommentRepository;

    private MenuReportDKContractRepository menuReportDKContractRepository;
    private MenuReportDKContractCommentRepository menuReportDKContractCommentRepository;

    private MenuReportDKDocRepository menuReportDKDocRepository;
    private MenuReportDKDocCommentRepository menuReportDKDocCommentRepository;

    private MenuReportDKServiceRepository menuReportDKServiceRepository;
    private DetailDownloadDateRepository detailDownloadDateRepository;

    public ReportDKController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            DK_Paid_Type_ListRepository dk_Paid_Type_ListRepository,
            MenuReportDKMainRepository menuReportDKMainRepository,
            MenuReportDKMainCommentRepository menuReportDKMainCommentRepository,

            MenuReportDKContractRepository menuReportDKContractRepository,
            MenuReportDKContractCommentRepository menuReportDKContractCommentRepository,

            MenuReportDKDocRepository menuReportDKDocRepository,
            MenuReportDKDocCommentRepository menuReportDKDocCommentRepository,

            MenuReportDKServiceRepository menuReportDKServiceRepository,
            DetailDownloadDateRepository detailDownloadDateRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.dk_Paid_Type_ListRepository = dk_Paid_Type_ListRepository;
        this.menuReportDKMainRepository = menuReportDKMainRepository;
        this.menuReportDKMainCommentRepository = menuReportDKMainCommentRepository;

        this.menuReportDKContractRepository = menuReportDKContractRepository;
        this.menuReportDKContractCommentRepository = menuReportDKContractCommentRepository;

        this.menuReportDKDocRepository = menuReportDKDocRepository;
        this.menuReportDKDocCommentRepository = menuReportDKDocCommentRepository;

        this.menuReportDKServiceRepository = menuReportDKServiceRepository;
        this.detailDownloadDateRepository = detailDownloadDateRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();

        List<DK_Paid_Type_List> dk_Paid_Type_List;

        dk_Paid_Type_List = (List<DK_Paid_Type_List>) dk_Paid_Type_ListRepository.findAll();
        mav.addObject("dk_paid_type_list", dk_Paid_Type_List);
        mav.addObject("dk_paid_type_id", 2);

        mav.setViewName("report_dk/cover");
        return mav;
    }

    @PostMapping("/get_service_table")
    public JSONDatatable get_service_table(
            @RequestParam(value="dk_paid_type_id", defaultValue = "0") Long dk_paid_type_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dk_paid_type_id != null && dk_paid_type_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKServiceRepository.queryGetRepDKServiceDetails(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dk_paid_type_id
            ));
        }
        return result;
    }

    @PostMapping("/get_main_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="dk_paid_type_id", defaultValue = "0") Long dk_paid_type_id,
            @RequestParam(value="dk_ser_id") Long dk_ser_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dk_paid_type_id != null && dk_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKMainRepository.queryGetRepActionDetails(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dk_paid_type_id, dk_ser_id
            ));
        }
        return result;
    }

    @GetMapping("/get_main_comment")
    public JSONDatatable get_main_comment(
            @RequestParam(value="dkm_id") Long dkm_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="ser_id") Long ser_id

    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dkm_id != null && dkm_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKMainCommentRepository.queryGetRepDKMainComment(
                    user_List.get(0).id,user_Role_List.get(0).role_id, dkm_id, cnt_id, ser_id
            ));
        }
        return result;
    }

    @PostMapping("/add_dk_main_comment")
    public ModelAndView add_dk_main_comment(
            @RequestParam(name = "dkm_id", required = false) Long dkm_id,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "dk_com_id", required = false) Long dk_com_id,
            @RequestParam(name = "dk_com_text", required = false) String dk_com_text
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_ACTIONS.pr_AddDKMainComment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, ser_id)
                    .setParameter(5, dkm_id)
                    .setParameter(6, dk_com_id)
                    .setParameter(7, dk_com_text)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_contract_table")
    public JSONDatatable get_contract_table(
            @RequestParam(value="dk_paid_type_id", defaultValue = "0") Long dk_paid_type_id,
            @RequestParam(value="dkm_id", defaultValue = "0") Long dkm_id,
            @RequestParam(value="dk_ser_id", defaultValue = "0") Long dk_ser_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dk_paid_type_id != null && dk_ser_id != null && dk_paid_type_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKContractRepository.queryGetRepDKContractMenu(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dk_paid_type_id, dkm_id, dk_ser_id
            ));
        }
        return result;
    }

    @GetMapping("/get_contract_comment")
    public JSONDatatable get_contract_comment(
            @RequestParam(value="dkm_id") Long dkm_id,
            @RequestParam(value="dkmd_id") Long dkmd_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="ser_id") Long ser_id

    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dkm_id != null && dkm_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKContractCommentRepository.queryGetRepDKContractComment(
                    user_List.get(0).id,user_Role_List.get(0).role_id, dkm_id, dkmd_id, cnt_id, ser_id
            ));
        }
        return result;
    }

    @PostMapping("/add_dk_contract_comment")
    public ModelAndView add_dk_contract_comment(
            @RequestParam(name = "dkm_id", required = false) Long dkm_id,
            @RequestParam(name = "dkmd_id", required = false) Long dkmd_id,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "cnt_doc_name", required = false) String cnt_doc_name,
            @RequestParam(name = "dk_com_id", required = false) Long dk_com_id,
            @RequestParam(name = "dk_com_text", required = false) String dk_com_text
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_ACTIONS.pr_AddDKContractComment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, ser_id)
                    .setParameter(5, cnt_doc_name)
                    .setParameter(6, dkm_id)
                    .setParameter(7, dkmd_id)
                    .setParameter(8, dk_com_id)
                    .setParameter(9, dk_com_text)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_doc_table")
    public JSONDatatable get_doc_table(
            @RequestParam(value="dk_paid_type_id", defaultValue = "0") Long dk_paid_type_id,
            @RequestParam(value="dkm_id", defaultValue = "0") Long dkm_id,
            @RequestParam(value="dkmd_id", defaultValue = "0") Long dkmd_id,
            @RequestParam(value="dk_ser_id") Long dk_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (dk_paid_type_id != null && dk_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKDocRepository.queryGetRepDKDocMenu(
                    user_List.get(0).id, user_Role_List.get(0).role_id, dk_paid_type_id, dkm_id, dkmd_id, dk_ser_id
            ));
        }
        return result;
    }

    @PostMapping("/report_dk_block")
    public JSONDatatable users_kpi_plans_edit(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        if(cnt_id != null && cnt_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditUserKPIPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DKBlockClient")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    ;
            EditUserKPIPlanQuery.execute();
        }
        return null;
    }

    @GetMapping("/get_doc_comment")
    public JSONDatatable get_doc_comment(
            @RequestParam(value="dkm_id") Long dkm_id,
            @RequestParam(value="dkmd_id") Long dkmd_id,
            @RequestParam(value="dkmdd_id") Long dkmdd_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="ser_id") Long ser_id

    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(dkm_id != null && dkm_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportDKDocCommentRepository.queryGetRepDKDocComment(
                    user_List.get(0).id,user_Role_List.get(0).role_id, dkm_id, dkmd_id, dkmdd_id, cnt_id, ser_id
            ));
        }
        return result;
    }

    @PostMapping("/add_dk_doc_comment")
    public ModelAndView add_dk_doc_comment(
            @RequestParam(name = "dkm_id", required = false) Long dkm_id,
            @RequestParam(name = "dkmd_id", required = false) Long dkmd_id,
            @RequestParam(name = "dkmdd_id", required = false) Long dkmdd_id,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "cnt_doc_name", required = false) String cnt_doc_name,
            @RequestParam(name = "dk_com_id", required = false) Long dk_com_id,
            @RequestParam(name = "dk_com_text", required = false) String dk_com_text
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_ACTIONS.pr_AddDKDocComment")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, ser_id)
                    .setParameter(5, cnt_doc_name)
                    .setParameter(6, dkm_id)
                    .setParameter(7, dkmd_id)
                    .setParameter(8, dkmdd_id)
                    .setParameter(9, dk_com_id)
                    .setParameter(10, dk_com_text)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get_download_date")
    public JSONDatatable get_download_date(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null) {
            result.setData(detailDownloadDateRepository.queryDetailDownloadDateBy(
            ));
        }
        return result;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @GetMapping("/dk_report")
    public void dk_report(
            @RequestParam(name = "dk_paid_type_id", required = false, defaultValue = "2") Long dk_paid_type_id,
            @RequestParam(name = "sheduler_date", required = false, defaultValue = "18.08.2020") String sheduler_date,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws JRException, IOException, SQLException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<User_Role_List> user_Role_List;

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(User_List.get(0).id);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_35.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("ROLE_ID", user_Role_List.get(0).role_id.intValue());
        params.put("DK_PAID_TYPE_ID", dk_paid_type_id.intValue());
        params.put("SHEDULER_DATE", sheduler_date);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=WMS.Zammler.ReportDK.xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }
}

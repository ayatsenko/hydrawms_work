package com.idltd.hydramob.controller.report_employee_balance;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Report_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.main_variable.DetailFinanceDownloadDateRepository;
import com.idltd.hydramob.repository.report_employee_balance.MenuReportEmployeeBalanceDetailRepository;
import com.idltd.hydramob.repository.report_employee_balance.MenuReportEmployeeBalanceTabRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import net.sf.jasperreports.engine.*;
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
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report_employee_balance")
public class ReportEmployeeBalaceController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private MenuReportEmployeeBalanceDetailRepository menuReportEmployeeBalanceDetailRepository;
    private MenuReportEmployeeBalanceTabRepository menuReportEmployeeBalanceTabRepository;
    private DetailFinanceDownloadDateRepository detailFinanceDownloadDateRepository;

    public ReportEmployeeBalaceController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            MenuReportEmployeeBalanceDetailRepository menuReportEmployeeBalanceDetailRepository,
            MenuReportEmployeeBalanceTabRepository menuReportEmployeeBalanceTabRepository,
            DetailFinanceDownloadDateRepository detailFinanceDownloadDateRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.menuReportEmployeeBalanceDetailRepository = menuReportEmployeeBalanceDetailRepository;
        this.menuReportEmployeeBalanceTabRepository = menuReportEmployeeBalanceTabRepository;
        this.detailFinanceDownloadDateRepository = detailFinanceDownloadDateRepository;
    }

    @RequestMapping
    public ModelAndView index(
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "employee_balance_show_id", required = false, defaultValue = "0") Long employee_balance_show_id,
            @RequestParam(name = "employee_balance_start_date", required = false) String employee_balance_start_date,
            @RequestParam(name = "employee_balance_end_date", required = false) String employee_balance_end_date,
            @RequestParam(name = "employee_tab_id", required = false) Long employee_tab_id
    ){
        ModelAndView mav = new ModelAndView();
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_id == null && user_Role_List.get(0).role_id == 8) {
            mav.addObject("user_id", new Long(1));
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else if(user_id == null && user_Role_List.get(0).role_id != 8){
            mav.addObject("user_id", user_List.get(0).id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else{
            mav.addObject("user_id", user_id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }

        mav.addObject("employee_balance_show_id", employee_balance_show_id);
        mav.addObject("employee_balance_start_date", employee_balance_start_date);
        mav.addObject("employee_balance_end_date", employee_balance_end_date);

        mav.setViewName("report_employee_balance/cover");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_user_id") Long req_user_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date.length() > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
            String StartResult = "";
            String EndResult = "";
            Date Date1;
            Date Date2;

            Date1 = df.parse(start_date);
            StartResult = newdf.format(Date1);

            Date2 = df.parse(end_date);
            EndResult = newdf.format(Date2);

            StoredProcedureQuery FillAllQuery = entityManager
                    .createStoredProcedureQuery("PKG_REPORTS_VIEW2.pr_vREPORT_124_FILL_TEMP")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, StartResult)
                    .setParameter(2, EndResult)
                    .setParameter(3, user_List.get(0).id)
                    .setParameter(4, user_Role_List.get(0).role_id)
                    .setParameter(5, req_user_id)
                    ;
            FillAllQuery.execute();

            result.setData(menuReportEmployeeBalanceDetailRepository.queryGetAllRep124Temp(
                    StartResult, EndResult, user_List.get(0).id, user_Role_List.get(0).role_id, req_user_id
            ));
        }
        return result;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @GetMapping("/pdf_detail")
    public void getDclPdf(
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            HttpServletResponse response)
            throws JRException, IOException, SQLException, ParseException {

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_3.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("START_DATE", StartResult);
        params.put("END_DATE", EndResult);
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_USER_ID", req_user_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=3.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
    }

    @GetMapping("/xls_detail")
    public void reportxls(
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            HttpServletResponse response
    ) throws JRException, IOException, SQLException, ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_3_xls.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("START_DATE", StartResult);
        params.put("END_DATE", EndResult);
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_USER_ID", req_user_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=3.xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }

    @PostMapping("/get_month")
    public JSONDatatable meets_all(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_user_id") Long req_user_id,
            @RequestParam(value="month_id") Integer month_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(start_date.length() > 0 && req_user_id >= -1 && month_id >= 0) {
            result.setData(menuReportEmployeeBalanceTabRepository.queryGetRep124Month
                    (start_date, end_date, user_List.get(0).id,  user_Role_List.get(0).role_id, req_user_id, new Long(month_id))
            );
        }
        return result;
    }

    @GetMapping("/pdf_month")
    public void getDclPdfMonth(
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "month_id", required = false) Long month_id,
            HttpServletResponse response)
            throws JRException, IOException, SQLException, ParseException {

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_19.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("START_DATE", StartResult);
        params.put("END_DATE", EndResult);
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_USER_ID", req_user_id.intValue());
        params.put("MONTH", month_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=19.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
    }

    @GetMapping("/xls_month")
    public void reportXlsDetail(
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            @RequestParam(name = "month_id", required = false) Long month_id,
            HttpServletResponse response
    ) throws JRException, IOException, SQLException, ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_19_xls.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("START_DATE", StartResult);
        params.put("END_DATE", EndResult);
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_USER_ID", req_user_id.intValue());
        params.put("MONTH", month_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=19.xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }

    @GetMapping("/get_finance_download_date")
    public JSONDatatable get_finance_download_date(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null) {
            result.setData(detailFinanceDownloadDateRepository.queryDetailDownloadDateBy(
            ));
        }
        return result;
    }
}

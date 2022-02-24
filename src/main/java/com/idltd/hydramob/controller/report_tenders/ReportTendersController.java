package com.idltd.hydramob.controller.report_tenders;

import com.idltd.hydramob.entity.Service_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.Service_Type_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Report_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.report_tenders.MenuReportTendersDetailRepository;
import com.idltd.hydramob.repository.report_tenders.MenuReportTendersTabRepository;
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
@RequestMapping("/report_tenders")
public class ReportTendersController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private MenuReportTendersDetailRepository menuReportTendersDetailRepository;
    private MenuReportTendersTabRepository menuReportTendersTabRepository;
    private User_Report_ListRepository user_Report_ListRepository;

    public ReportTendersController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            MenuReportTendersDetailRepository menuReportTendersDetailRepository,
            MenuReportTendersTabRepository menuReportTendersTabRepository,
            User_Report_ListRepository user_Report_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.menuReportTendersDetailRepository = menuReportTendersDetailRepository;
        this.menuReportTendersTabRepository = menuReportTendersTabRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<Service_Type_List> service_Type_List;
        List<User_List> user_List;
        List<User_Report_List> user_Report_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("ser_id", 0);
        service_Type_List = service_Type_ListRepository.queryReportSerIDByUserID(user_List.get(0).id);
        mav.addObject("ser_list", service_Type_List);

        if(user_Role_List.get(0).role_id == 8) {
            mav.addObject("user_id", new Long(1));
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }
        else{
            mav.addObject("user_id", user_List.get(0).id);
            user_Report_List = user_Report_ListRepository.queryGetUserByChiefID(user_List.get(0).id);
            mav.addObject("user_list", user_Report_List);
        }

        mav.setViewName("report_tenders/cover");
        return mav;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id,
            @RequestParam(value="req_user_id") Long req_user_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date != null && end_date != null && req_ser_id != null && req_ser_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

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

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportTendersDetailRepository.queryGetAllRep129(StartResult, EndResult, user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, req_user_id));
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
            @RequestParam(name = "req_ser_id", required = false) Long req_ser_id,
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

        if(start_date != null && end_date != null && req_ser_id > 0 && req_user_id > 0) {
            List<User_List> User_List = user_ListRepository.queryUserByName(name);

            InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_8.jasper");
            Map<String, Object> params = new HashMap<>();
            params.put("START_DATE", StartResult);
            params.put("END_DATE", EndResult);
            params.put("USER_ID", User_List.get(0).id.intValue());
            params.put("REQ_SER_ID", req_ser_id.intValue());
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            try (Connection conn = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

                response.setContentType("application/x-pdf");
                response.setHeader("Content-disposition", "inline; filename=8.pdf");

                final OutputStream outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            }
        }
    }

    @GetMapping("/xls_detail")
    public void reportxls(
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_ser_id", required = false) Long req_ser_id,
            @RequestParam(name = "req_user_id", required = false) Long req_user_id,
            HttpServletResponse response
    ) throws JRException, IOException, SQLException, ParseException {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

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

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_29_xls.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("START_DATE", StartResult);
        params.put("END_DATE", EndResult);
        params.put("USER_ID", user_List.get(0).id.intValue());
        params.put("ROLE_ID", user_Role_List.get(0).role_id.intValue());
        params.put("REQ_SER_ID", req_ser_id.intValue());
        params.put("REQ_USER_ID", req_user_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=29.xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }

    @PostMapping("/get_tab_table")
    public JSONDatatable get_all(
            @RequestParam(value="start_date", required = false) String start_date,
            @RequestParam(value="end_date", required = false) String end_date,
            @RequestParam(value="req_ser_id", required = false) Long req_ser_id,
            @RequestParam(value="type_id", required = false) Long type_id,
            @RequestParam(value="req_user_id", required = false) Long req_user_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date != null && end_date != null && req_ser_id != null && req_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

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

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportTendersTabRepository.queryReportTenderTabByUserID(
                    StartResult, EndResult, user_List.get(0).id, user_Role_List.get(0).role_id, type_id, req_ser_id, req_user_id
            ));
        }
        return result;
    }

    @GetMapping("/xls_tabs")
    public void detail_reportxls(
            @RequestParam(name = "type_id", required = false, defaultValue = "01.01.2018") Integer type_id,
            @RequestParam(name = "start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(name = "end_date", required = false, defaultValue = "31.12.2018") String end_date,
            @RequestParam(name = "req_ser_id", required = false) Long req_ser_id,
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

        if(start_date != null && end_date != null && req_ser_id != null) {

            InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_26_xls.jasper");
            Map<String, Object> params = new HashMap<>();
            params.put("TYPE_ID", type_id);
            params.put("USER_ID", User_List.get(0).id.intValue());
            params.put("SER_ID", req_ser_id.intValue());
            params.put("START_DATE", StartResult);
            params.put("END_DATE", EndResult);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            try (Connection conn = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "inline; filename=26.xls");

                JRXlsExporter exporter = new JRXlsExporter();

                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setOnePagePerSheet(false);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
            }
        }
    }
}

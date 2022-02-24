package com.idltd.hydramob.controller.report_phonebook;

import com.idltd.hydramob.entity.Client_Report_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.Client_Report_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.report_phonebook.MenuReportPhoneBookDetailRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report_phonebook")
public class ReportPhoneBookController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Client_Report_ListRepository client_Report_ListRepository;
    private MenuReportPhoneBookDetailRepository menuReportPhoneBookDetailRepository;

    public ReportPhoneBookController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Client_Report_ListRepository client_Report_ListRepository,
            MenuReportPhoneBookDetailRepository menuReportPhoneBookDetailRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.client_Report_ListRepository = client_Report_ListRepository;
        this.menuReportPhoneBookDetailRepository = menuReportPhoneBookDetailRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<Client_Report_List> Client_Report_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);

        mav.addObject("cnt_id", 0);
        Client_Report_List = client_Report_ListRepository.queryGetClientByChiefID(user_List.get(0).id);
        mav.addObject("cnt_list", Client_Report_List);

        mav.setViewName("report_phonebook/cover");
        return mav;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="req_cnt_id") Long req_cnt_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(req_cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuReportPhoneBookDetailRepository.queryGetAllRep128(user_List.get(0).id, req_cnt_id));
        }
        return result;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @GetMapping("/pdf_detail")
    public void getDclPdf(
            @RequestParam(name = "req_cnt_id", required = false) Long req_cnt_id,
            HttpServletResponse response)
            throws JRException, IOException, SQLException, ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_21.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_CNT_ID", req_cnt_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=21.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
    }

    @GetMapping("/xls_detail")
    public void reportxls(
            @RequestParam(name = "req_cnt_id", required = false) Long req_cnt_id,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws JRException, IOException, SQLException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_21_xls.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("REQ_CNT_ID", req_cnt_id.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=21.xls");

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

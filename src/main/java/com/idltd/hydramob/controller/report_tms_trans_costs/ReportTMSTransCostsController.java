package com.idltd.hydramob.controller.report_tms_trans_costs;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Report_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.TMS_Managers_Report_List;
import com.idltd.hydramob.entity.list.TMS_Transport_Report_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Managers_Report_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Transport_Report_ListRepository;
import com.idltd.hydramob.repository.report_tms_trans_costs.MenuReportTMSTransCostsDetailRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/report_tms_trans_costs")
public class ReportTMSTransCostsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuReportTMSTransCostsDetailRepository menuReportTMSTransCostsDetailRepository;
    private TMS_Transport_Report_ListRepository tms_Transport_Report_ListRepository;
    private TMS_Managers_Report_ListRepository tms_Managers_Report_ListRepository;
    public ReportTMSTransCostsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuReportTMSTransCostsDetailRepository menuReportTMSTransCostsDetailRepository,
            TMS_Transport_Report_ListRepository tms_Transport_Report_ListRepository,
            TMS_Managers_Report_ListRepository tms_Managers_Report_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuReportTMSTransCostsDetailRepository = menuReportTMSTransCostsDetailRepository;
        this.tms_Transport_Report_ListRepository = tms_Transport_Report_ListRepository;
        this.tms_Managers_Report_ListRepository = tms_Managers_Report_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(
    ){
        ModelAndView mav = new ModelAndView();
        List<TMS_Transport_Report_List> trans_Report_List;
        List<User_List> user_List;
        List<User_Report_List> user_Report_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Managers_Report_List> tms_Managers_Report_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("cnt_id", 0);
        trans_Report_List = tms_Transport_Report_ListRepository.queryTMSTransportReportListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
        mav.addObject("trans_list", trans_Report_List);

        mav.addObject("user_id", 0);
        tms_Managers_Report_List = tms_Managers_Report_ListRepository.queryTMSManagersReportListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
        mav.addObject("ops_list", tms_Managers_Report_List);

        mav.setViewName("report_tms_trans_costs/cover");
        return mav;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_cnt_id") Long req_cnt_id,
            @RequestParam(value="req_user_id") Long req_user_id
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(start_date != null && end_date != null && req_cnt_id != null) {
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

            result.setData(menuReportTMSTransCostsDetailRepository.queryGetRepTMSTransCostsDetail(user_List.get(0).id, user_Role_List.get(0).role_id, StartResult, EndResult, req_cnt_id, req_user_id));
        }
        return result;
    }
}

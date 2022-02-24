package com.idltd.hydramob.controller.fin_result;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.fin_result.DetailFinResultErrorClient;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.fin_result.DetailFinResultErrorClientRepository;
import com.idltd.hydramob.repository.fin_result.MenuFinResultDetailRepository;
import com.idltd.hydramob.repository.fin_result.MenuFinResultErrorRepository;
import com.idltd.hydramob.repository.fin_result.MenuFinResultFinanceRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/fin_result")
public class FinResultController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;

    private MenuFinResultDetailRepository menuFinResultDetailRepository;

    private MenuFinResultFinanceRepository menuFinResultFinanceRepository;

    private MenuFinResultErrorRepository menuFinResultErrorRepository;

    private DetailFinResultErrorClientRepository detailFinResultErrorClientRepository;
    private Contragent_Status_ListRepository contragent_Status_ListRepository;
    private OwnerShip_Type_ListRepository ownerShip_Type_ListRepository;
    private Contragent_Source_ListRepository contragent_Source_ListRepository;
    private Country_ListRepository country_ListRepository;
    private Contragent_ListRepository contragent_ListRepository;

    public FinResultController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,

            MenuFinResultDetailRepository menuFinResultDetailRepository,
            MenuFinResultFinanceRepository menuFinResultFinanceRepository,
            MenuFinResultErrorRepository menuFinResultErrorRepository,

            DetailFinResultErrorClientRepository detailFinResultErrorClientRepository,
            Contragent_Status_ListRepository contragent_Status_ListRepository,
            OwnerShip_Type_ListRepository ownerShip_Type_ListRepository,
            Contragent_Source_ListRepository contragent_Source_ListRepository,
            Country_ListRepository country_ListRepository,
            Contragent_ListRepository contragent_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;

        this.menuFinResultDetailRepository = menuFinResultDetailRepository;
        this.menuFinResultFinanceRepository = menuFinResultFinanceRepository;
        this.menuFinResultErrorRepository = menuFinResultErrorRepository;

        this.detailFinResultErrorClientRepository = detailFinResultErrorClientRepository;
        this.contragent_Status_ListRepository = contragent_Status_ListRepository;
        this.ownerShip_Type_ListRepository = ownerShip_Type_ListRepository;
        this.contragent_Source_ListRepository = contragent_Source_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.contragent_ListRepository = contragent_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(
            @RequestParam(name = "sfad_id", required = false) Long sfad_id,
            @RequestParam(name = "fin_result_show", required = false) Long fin_result_show,

            @RequestParam(name = "fin_result_start_date", required = false) String fin_result_start_date,
            @RequestParam(name = "fin_result_end_date", required = false) String fin_result_end_date,
            @RequestParam(name = "req_ser_id", required = false) Long req_ser_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id
    ){
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Service_Type_List> service_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("ser_id", 0);
        service_Type_List = service_Type_ListRepository.queryReportSerIDByUserID(user_List.get(0).id);
        mav.addObject("service_list", service_Type_List);

        mav.addObject("sfad_id", sfad_id);

        mav.addObject("fin_result_show", fin_result_show);

        mav.addObject("fin_result_start_date", fin_result_start_date);
        mav.addObject("fin_result_end_date", fin_result_end_date);
        mav.addObject("req_ser_id", req_ser_id);
        mav.addObject("ser_id", ser_id);

        mav.setViewName("fin_result/cover");
        return mav;
    }

    @PostMapping("/get_detail_table")
    public JSONDatatable get_detail_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id
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

            result.setData(menuFinResultDetailRepository.queryGetFinResultDetail(user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, StartResult, EndResult));
        }
        return result;
    }

    @PostMapping("/get_finance_table")
    public JSONDatatable get_finance_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id
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

            result.setData(menuFinResultFinanceRepository.queryGetFinResultFinance(user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, StartResult, EndResult));
        }
        return result;
    }

    @PostMapping("/get_error_table")
    public JSONDatatable get_error_table(
            @RequestParam(value="start_date") String start_date,
            @RequestParam(value="end_date") String end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id
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

            result.setData(menuFinResultErrorRepository.queryGetFinResultError(
                    user_List.get(0).id, user_Role_List.get(0).role_id, req_ser_id, StartResult, EndResult
            ));
        }
        return result;
    }

    @RequestMapping("/client_error_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="sfad_id") Long sfad_id,

                                   @RequestParam(value="fin_result_error_main_table_order_column") Long order_column,
                                   @RequestParam(value="fin_result_error_main_table_order_type") String order_type,
                                   @RequestParam(value="fin_result_error_main_table_search") String table_search,
                                   @RequestParam(value="fin_result_error_main_table_pagelen") Long pagelen,
                                   @RequestParam(value="fin_result_error_main_table_page") Long page,

                                   @RequestParam(value="fin_result_start_date") String fin_result_start_date,
                                   @RequestParam(value="fin_result_end_date") String fin_result_end_date,
                                   @RequestParam(value="req_ser_id") Long req_ser_id,
                                   @RequestParam(value="ser_id") Long ser_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<OwnerShip_Type_List> OwnerShip_Type_List;
        List<Contragent_Status_List> cs_list;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_Source_List> contragent_Source_List;
        List<User_List> all_User_List;
        List<Contragent_List> contragent_List;

        List<DetailFinResultErrorClient> detailFinResultErrorClient;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        detailFinResultErrorClient = detailFinResultErrorClientRepository.queryGetFinResultErrorClientDetail(user_List.get(0).id, user_Role_List.get(0).role_id, sfad_id);

        mav.addObject("sfad_id", sfad_id);

        mav.addObject("cnt_name", detailFinResultErrorClient.get(0).cnt_name);

        if(detailFinResultErrorClient.get(0).cnt_identifycode != null) {
            mav.addObject("cnt_resident", 1);
        }
        else{
            mav.addObject("cnt_resident", 0);
        }
        mav.addObject("cnt_identifycode", detailFinResultErrorClient.get(0).cnt_identifycode);

        mav.addObject("cs_id", new Long(0));
        cs_list = contragent_Status_ListRepository.queryGetCurrStatus(new Long(0));
        mav.addObject("cs_list", cs_list);

        mav.addObject("owt_id",  new Long(45));
        OwnerShip_Type_List = ownerShip_Type_ListRepository.queryGetOwnList();
        mav.addObject("own_list", OwnerShip_Type_List);

        mav.addObject("cnt_source_id",  new Long(5));
        contragent_Source_List = contragent_Source_ListRepository.queryEditContragentSource();
        mav.addObject("cnt_source_list", contragent_Source_List);

        mav.addObject("country_id",  new Long(2));
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        if(detailFinResultErrorClient.get(0).user_id != null) {
            mav.addObject("user_id", detailFinResultErrorClient.get(0).user_id);
            all_User_List = user_ListRepository.queryUserAllOrderDetail();
            mav.addObject("user_list", all_User_List);
        }else {
            all_User_List = user_ListRepository.queryUserAllOrderDetail();
            mav.addObject("user_list", all_User_List);
        }

        contragent_List = contragent_ListRepository.queryCntByUserID(user_List.get(0).id);
        mav.addObject("cnt_list", contragent_List);

        mav.addObject("fin_result_error_main_table_order_column", order_column);
        mav.addObject("fin_result_error_main_table_order_type", order_type);
        mav.addObject("fin_result_error_main_table_search", table_search);
        mav.addObject("fin_result_error_main_table_pagelen", pagelen);
        mav.addObject("fin_result_error_main_table_page", page);

        mav.addObject("fin_result_start_date", fin_result_start_date);
        mav.addObject("fin_result_end_date", fin_result_end_date);
        mav.addObject("req_ser_id", req_ser_id);
        mav.addObject("ser_id", ser_id);

        mav.setViewName("/fin_result/error_cnt_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/client_error_model")
    public ModelAndView model(
            @RequestParam(value = "mode") Long mode,
            @RequestParam(name = "sfad_id", required = false) Long sfad_id,

            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_sname", required = false) String cnt_sname,

            @RequestParam(name = "cnt_identifycode", required = false, defaultValue = "0") String cnt_identifycode,
            @RequestParam(name = "owt_id", required = false) Long owt_id,
            @RequestParam(name = "cs_id", required = false) Long cs_id,
            @RequestParam(name = "user_id", required = false, defaultValue = "0") Long user_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Boolean cnt_resident,
            @RequestParam(name = "cnt_source_id", required = false, defaultValue = "0") Long cnt_source_id,
            @RequestParam(name = "country_id", required = false, defaultValue = "0") Long country_id,

            @RequestParam(name = "cnt_old", required = false) Boolean cnt_old,
            @RequestParam(name = "cnt_id", required = false, defaultValue = "0") Long cnt_id,

            @RequestParam(name = "fin_result_error_main_table_order_column", required = false) Long order_column,
            @RequestParam(name = "fin_result_error_main_table_order_type", required = false) String order_type,
            @RequestParam(name = "fin_result_error_main_table_search", required = false) String table_search,
            @RequestParam(name = "fin_result_error_main_table_pagelen", required = false) Long pagelen,
            @RequestParam(name = "fin_result_error_main_table_page", required = false) Long page,

            @RequestParam(value="fin_result_start_date") String fin_result_start_date,
            @RequestParam(value="fin_result_end_date") String fin_result_end_date,
            @RequestParam(value="req_ser_id") Long req_ser_id,
            @RequestParam(value="ser_id") Long ser_id
    ) {
        ModelAndView mav = new ModelAndView();

        Long CntResidence;
        Long CntOld;

        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(cnt_resident == null){ CntResidence = new Long("0");}
        else { CntResidence = new Long("1");}

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(cnt_old == null){ CntOld = new Long("0");}
        else { CntOld = new Long("1");}

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClientErrorQuery = entityManager
                            .createStoredProcedureQuery("PKG_ADMIN.pr_EditContragentError")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                            .setParameter(1, sfad_id)
                            .setParameter(2, new Long(1))
                            .setParameter(3, cnt_name)
                            .setParameter(4, cnt_sname)
                            .setParameter(5, cnt_identifycode)
                            .setParameter(6, owt_id)
                            .setParameter(7, cs_id)
                            .setParameter(8, cnt_note)
                            .setParameter(9, CntResidence)
                            .setParameter(10, user_id)
                            .setParameter(11, user_Role_List.get(0).role_id)
                            .setParameter(12, cnt_source_id)
                            .setParameter(13, country_id)
                            .setParameter(14, CntOld)
                            .setParameter(15, cnt_id)
                            ;
                    AddClientErrorQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("sfad_id", sfad_id);

        mav.addObject("fin_result_show", 1);

        mav.addObject("fin_result_error_main_table_order_column", order_column);
        mav.addObject("fin_result_error_main_table_order_type", order_type);
        mav.addObject("fin_result_error_main_table_search", table_search);
        mav.addObject("fin_result_error_main_table_pagelen", pagelen);
        mav.addObject("fin_result_error_main_table_page", page);

        mav.addObject("fin_result_start_date", fin_result_start_date);
        mav.addObject("fin_result_end_date", fin_result_end_date);
        mav.addObject("req_ser_id", req_ser_id);
        mav.addObject("ser_id", ser_id);

        mav.setViewName("/fin_result/error_cnt_detail");
        return mav;
    }
}

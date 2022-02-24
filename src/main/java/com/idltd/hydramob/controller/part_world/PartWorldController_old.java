package com.idltd.hydramob.controller.part_world;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.full_world.*;
import com.idltd.hydramob.entity.list.*;
import com.idltd.hydramob.entity.part_world_old.ListClaimTransWays;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.full_world.*;
import com.idltd.hydramob.repository.list.*;
import com.idltd.hydramob.repository.part_world_old.ListClaimTransWaysRepository;
import com.idltd.hydramob.repository.part_world_old.MenuClaimTransLinkListRepository;
import com.idltd.hydramob.repository.part_world_old.MenuClaimTransWaysListRepository;
import com.idltd.hydramob.repository.tms_ua.MenuClaimsListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/part_world_old")
public class PartWorldController_old {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClaimsListRepository menuClaimsListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Claims_Status_ListRepository claims_Status_ListRepository;
    private Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository;
    private DetailClaimsListRepository detailClaimsListRepository;
    private Cars_Type_ListRepository cars_Type_ListRepository;
    private Trailers_Type_ListRepository trailers_Type_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;

    private MenuClaimsTimeLogsRepository menuClaimsTimeLogsRepository;

    private MenuClaimTasksListRepository menuClaimTasksListRepository;
    private Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository;
    private DetailClaimTasksListRepository detailClaimTasksListRepository;

    private MenuClaimWaysListRepository menuClaimWaysListRepository;
    private MenuClaimWaysTabListRepository menuClaimWaysTabListRepository;
    private Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository;
    private Country_ListRepository country_ListRepository;

    private MenuClaimLoadsListRepository menuClaimLoadsListRepository;
    private DetailClaimLoadsListRepository detailClaimLoadsListRepository;
    private WeightTypeRepository weightTypeRepository;
    private PalletListRepository palletListRepository;

    private MenuClaimTransListRepository menuClaimTransListRepository;
    private Transport_ListRepository transport_listRepository;
    private TMS_Cars_ListRepository tms_Cars_ListRepository;
    private TMS_Trailers_ListRepository tms_Trailers_ListRepository;
    private TMS_Drivers_ListRepository tms_Drivers_ListRepository;
    private DetailClaimTransListRepository detailClaimTransListRepository;

    private MenuClaimTransLinkListRepository menuClaimTransLinkListRepository;
    private MenuClaimTransWaysListRepository menuClaimTransWaysListRepository;
    private ListClaimTransWaysRepository listClaimTransWaysRepository;

    private MenuClaimCostsListRepository menuClaimCostsListRepository;
    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;
    private DetailClaimCostsListRepository detailClaimCostsListRepository;

    private TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository;
    private DetailClaimWaysTabRepository detailClaimWaysTabRepository;

    private MenuClaimErrorsListRepository menuClaimErrorsListRepository;

    public PartWorldController_old(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClaimsListRepository menuClaimsListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Claims_Status_ListRepository claims_Status_ListRepository,
            Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository,
            DetailClaimsListRepository detailClaimsListRepository,
            Cars_Type_ListRepository cars_Type_ListRepository,
            Trailers_Type_ListRepository trailers_Type_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,

            MenuClaimsTimeLogsRepository menuClaimsTimeLogsRepository,

            MenuClaimTasksListRepository menuClaimTasksListRepository,
            Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository,
            DetailClaimTasksListRepository detailClaimTasksListRepository,

            MenuClaimWaysListRepository menuClaimWaysListRepository,
            MenuClaimWaysTabListRepository menuClaimWaysTabListRepository,
            Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository,
            Country_ListRepository country_ListRepository,

            MenuClaimLoadsListRepository menuClaimLoadsListRepository,
            DetailClaimLoadsListRepository detailClaimLoadsListRepository,
            WeightTypeRepository weightTypeRepository,
            PalletListRepository palletListRepository,

            MenuClaimTransListRepository menuClaimTransListRepository,
            Transport_ListRepository transport_listRepository,
            TMS_Cars_ListRepository tms_Cars_ListRepository,
            TMS_Trailers_ListRepository tms_Trailers_ListRepository,
            TMS_Drivers_ListRepository tms_Drivers_ListRepository,
            DetailClaimTransListRepository detailClaimTransListRepository,

            MenuClaimTransLinkListRepository menuClaimTransLinkListRepository,
            MenuClaimTransWaysListRepository menuClaimTransWaysListRepository,
            ListClaimTransWaysRepository listClaimTransWaysRepository,

            MenuClaimCostsListRepository menuClaimCostsListRepository,
            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,
            DetailClaimCostsListRepository detailClaimCostsListRepository,

            TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository,
            DetailClaimWaysTabRepository detailClaimWaysTabRepository,

            MenuClaimErrorsListRepository menuClaimErrorsListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClaimsListRepository = menuClaimsListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.claims_Status_ListRepository = claims_Status_ListRepository;
        this.claims_Aim_Type_ListRepository = claims_Aim_Type_ListRepository;
        this.detailClaimsListRepository = detailClaimsListRepository;
        this.cars_Type_ListRepository = cars_Type_ListRepository;
        this.trailers_Type_ListRepository = trailers_Type_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;

        this.menuClaimsTimeLogsRepository = menuClaimsTimeLogsRepository;

        this.menuClaimTasksListRepository = menuClaimTasksListRepository;
        this.claims_Tasks_Type_ListRepository = claims_Tasks_Type_ListRepository;
        this.detailClaimTasksListRepository = detailClaimTasksListRepository;

        this.menuClaimWaysListRepository = menuClaimWaysListRepository;
        this.menuClaimWaysTabListRepository = menuClaimWaysTabListRepository;
        this.claims_Ways_Type_ListRepository = claims_Ways_Type_ListRepository;
        this.country_ListRepository = country_ListRepository;

        this.menuClaimLoadsListRepository = menuClaimLoadsListRepository;
        this.detailClaimLoadsListRepository = detailClaimLoadsListRepository;
        this.weightTypeRepository = weightTypeRepository;
        this.palletListRepository = palletListRepository;

        this.menuClaimTransListRepository = menuClaimTransListRepository;
        this.transport_listRepository = transport_listRepository;
        this.tms_Cars_ListRepository = tms_Cars_ListRepository;
        this.tms_Trailers_ListRepository = tms_Trailers_ListRepository;
        this.tms_Drivers_ListRepository = tms_Drivers_ListRepository;
        this.detailClaimTransListRepository = detailClaimTransListRepository;

        this.menuClaimTransLinkListRepository = menuClaimTransLinkListRepository;
        this.menuClaimTransWaysListRepository = menuClaimTransWaysListRepository;
        this.listClaimTransWaysRepository = listClaimTransWaysRepository;

        this.menuClaimCostsListRepository = menuClaimCostsListRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;
        this.detailClaimCostsListRepository = detailClaimCostsListRepository;

        this.tms_Claims_Ways_Type_ListRepository = tms_Claims_Ways_Type_ListRepository;
        this.detailClaimWaysTabRepository = detailClaimWaysTabRepository;

        this.menuClaimErrorsListRepository = menuClaimErrorsListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id,

                              @RequestParam(name = "part_world_filter_id", required = false, defaultValue = "0") Long part_world_filter_id,
                              @RequestParam(name = "part_world_filter_start_date", required = false) String part_world_filter_start_date,
                              @RequestParam(name = "part_world_filter_end_date", required = false) String part_world_filter_end_date,

                              @RequestParam(name = "part_world_main_table_order_column", required = false) Long part_world_main_table_order_column,
                              @RequestParam(name = "part_world_main_table_order_type", required = false) String part_world_main_table_order_type,
                              @RequestParam(name = "part_world_main_table_search", required = false) String part_world_main_table_search,
                              @RequestParam(name = "part_world_main_table_pagelen", required = false) Long part_world_main_table_pagelen,
                              @RequestParam(name = "part_world_main_table_page", required = false) Long part_world_main_table_page,

                              @RequestParam(name = "part_world_tab_name", required = false) String tab_name,
                              @RequestParam(name = "clmtl_id", required = false) Long clmtl_id,
                              @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id
    ){
        model.addObject("clm_id", clm_id);

        model.addObject("part_world_main_table_order_column", part_world_main_table_order_column);
        model.addObject("part_world_main_table_order_type", part_world_main_table_order_type);
        model.addObject("part_world_main_table_search", part_world_main_table_search);
        model.addObject("part_world_main_table_pagelen", part_world_main_table_pagelen);
        model.addObject("part_world_main_table_page", part_world_main_table_page);

        model.addObject("part_world_filter_id", part_world_filter_id);
        model.addObject("part_world_filter_start_date", part_world_filter_start_date);
        model.addObject("part_world_filter_end_date", part_world_filter_end_date);

        model.addObject("part_world_tab_name", tab_name);

        model.addObject("clmtl_id", clmtl_id);
        model.addObject("clmtrl_id", clmtrl_id);
        model.setViewName("part_world/cover");
        return model;
    }

    @PostMapping("/part_world")
    public JSONDatatable part_world(
            @RequestParam(name = "part_world_filter_id", required = false, defaultValue = "0") Long part_world_filter_id,
            @RequestParam(name = "part_world_filter_start_date", required = false) String part_world_filter_start_date,
            @RequestParam(name = "part_world_filter_end_date", required = false) String part_world_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClaimsListRepository.queryClaimsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, part_world_filter_id, part_world_filter_start_date, part_world_filter_end_date, new Long(4)));

        return result;
    }

    @RequestMapping("/main_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="clm_id") Long clm_id,

                                   @RequestParam(value="part_world_main_table_order_column") Long order_column,
                                   @RequestParam(value="part_world_main_table_order_type") String order_type,
                                   @RequestParam(value="part_world_main_table_search") String table_search,
                                   @RequestParam(value="part_world_main_table_pagelen") Long pagelen,
                                   @RequestParam(value="part_world_main_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_List> ops_User_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Client_List> tms_Client_List;
        List<Service_Type_List> service_Type_List;
        List<Claims_Status_List> claims_Status_List;
        List<Claims_Aim_Type_List> claims_Aim_Type_List;
        List<DetailClaimsList> detailClaimsList;
        List<Currency_Use_List> clm_Sum_Currency_List;
        List<Currency_Use_List> clm_Rate_Currency_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("user_id", user_List.get(0).id);
            ops_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("user_list", ops_User_List);

            tms_Client_List = tms_Client_ListRepository.queryTMSClientByUserID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("transport_list", tms_Client_List);

            mav.addObject("ser_id", new Long(4));
            service_Type_List = service_Type_ListRepository.queryGetByID(new Long(4));
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("clm_sum", "0,00");

            mav.addObject("clm_sum_currency_id", 978);
            clm_Sum_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("clm_sum_currency_list", clm_Sum_Currency_List);

            mav.addObject("clm_rate", "0,00");

            mav.addObject("clm_rate_currency_id", 978);
            clm_Rate_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("clm_rate_currency_list", clm_Rate_Currency_List);

            mav.addObject("clm_status_id", 0);
            claims_Status_List = (List<Claims_Status_List>) claims_Status_ListRepository.findAll();
            mav.addObject("claims_status_list", claims_Status_List);

            claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
            mav.addObject("clm_aim_type_list", claims_Aim_Type_List);
        }
        else if(mode == 1){
            detailClaimsList = detailClaimsListRepository.queryClaimsDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id);

            mav.addObject("clm_id", detailClaimsList.get(0).clm_id);

            mav.addObject("clm_num", detailClaimsList.get(0).clm_num);

            mav.addObject("clm_date", detailClaimsList.get(0).clm_date);

            mav.addObject("user_id", detailClaimsList.get(0).user_id);
            ops_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("user_list", ops_User_List);

            mav.addObject("cnt_id", detailClaimsList.get(0).cnt_id);
            tms_Client_List = tms_Client_ListRepository.queryTMSClientByUserID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("transport_list", tms_Client_List);

            mav.addObject("ser_id", detailClaimsList.get(0).ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(detailClaimsList.get(0).ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("clm_sum", detailClaimsList.get(0).clm_sum);

            mav.addObject("clm_sum_currency_id", detailClaimsList.get(0).clm_sum_currency_id);
            clm_Sum_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("clm_sum_currency_list", clm_Sum_Currency_List);

            mav.addObject("clm_sum_currency_date", detailClaimsList.get(0).clm_sum_currency_date);

            mav.addObject("clm_rate", detailClaimsList.get(0).clm_rate);

            mav.addObject("clm_rate_currency_id", detailClaimsList.get(0).clm_rate_currency_id);
            clm_Rate_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("clm_rate_currency_list", clm_Rate_Currency_List);

            mav.addObject("clm_rate_currency_date", detailClaimsList.get(0).clm_rate_currency_date);

            mav.addObject("clm_status_id", detailClaimsList.get(0).clm_status_id);
            claims_Status_List = (List<Claims_Status_List>) claims_Status_ListRepository.findAll();
            mav.addObject("claims_status_list", claims_Status_List);

            mav.addObject("clm_aim_type_id", detailClaimsList.get(0).clm_aim_type_id);
            claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
            mav.addObject("clm_aim_type_list", claims_Aim_Type_List);

            mav.addObject("clm_note", detailClaimsList.get(0).clm_note);
        }
        else{
            detailClaimsList = detailClaimsListRepository.queryClaimsDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id);

            mav.addObject("clm_id", detailClaimsList.get(0).clm_id);

            mav.addObject("clm_num", detailClaimsList.get(0).clm_num);

            mav.addObject("clm_date", detailClaimsList.get(0).clm_date);

            mav.addObject("user_id", detailClaimsList.get(0).user_id);
            ops_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("user_list", ops_User_List);

            mav.addObject("cnt_id", detailClaimsList.get(0).cnt_id);
            tms_Client_List = tms_Client_ListRepository.queryTMSOneClientByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimsList.get(0).cnt_id);
            mav.addObject("transport_list", tms_Client_List);

            mav.addObject("ser_id", detailClaimsList.get(0).ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(detailClaimsList.get(0).ser_id);
            mav.addObject("ser_list", service_Type_List);

            mav.addObject("clm_sum", detailClaimsList.get(0).clm_sum);

            mav.addObject("clm_sum_currency_id", detailClaimsList.get(0).clm_sum_currency_id);
            clm_Sum_Currency_List = currency_Use_ListRepository.queryOneCurrencyListByID(detailClaimsList.get(0).clm_sum_currency_id);
            mav.addObject("clm_sum_currency_list", clm_Sum_Currency_List);

            mav.addObject("clm_sum_currency_date", detailClaimsList.get(0).clm_sum_currency_date);

            mav.addObject("clm_rate", detailClaimsList.get(0).clm_rate);

            mav.addObject("clm_rate_currency_id", detailClaimsList.get(0).clm_rate_currency_id);
            clm_Rate_Currency_List = currency_Use_ListRepository.queryOneCurrencyListByID(detailClaimsList.get(0).clm_rate_currency_id);
            mav.addObject("clm_rate_currency_list", clm_Rate_Currency_List);

            mav.addObject("clm_rate_currency_date", detailClaimsList.get(0).clm_rate_currency_date);

            mav.addObject("clm_status_id", detailClaimsList.get(0).clm_status_id);
            claims_Status_List = (List<Claims_Status_List>) claims_Status_ListRepository.findAll();
            mav.addObject("claims_status_list", claims_Status_List);

            mav.addObject("clm_aim_type_id", detailClaimsList.get(0).clm_aim_type_id);
            claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
            mav.addObject("clm_aim_type_list", claims_Aim_Type_List);

            mav.addObject("clm_note", detailClaimsList.get(0).clm_note);
        }

        mav.addObject("part_world_main_table_order_column", order_column);
        mav.addObject("part_world_main_table_order_type", order_type);
        mav.addObject("part_world_main_table_search", table_search);
        mav.addObject("part_world_main_table_pagelen", pagelen);
        mav.addObject("part_world_main_table_page", page);

        mav.setViewName("/part_world/main_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/main_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,

            @RequestParam(value="clm_date", required = false) String clm_date,
            @RequestParam(value="clm_num", required = false) String clm_num,
            @RequestParam(value="cnt_id", required = false) Long cnt_id,
            @RequestParam(value="ser_id", required = false) Long ser_id,
            @RequestParam(value="user_id", required = false) Long user_id,
            @RequestParam(value="clm_sum", required = false) String clm_sum,
            @RequestParam(value="clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(value="clm_sum_currency_date", required = false) String clm_sum_currency_date,

            @RequestParam(value="clm_rate", required = false) String clm_rate,
            @RequestParam(value="clm_rate_currency_id", required = false) Long clm_rate_currency_id,
            @RequestParam(value="clm_rate_currency_date", required = false) String clm_rate_currency_date,

            @RequestParam(value="clm_aim_type_id", required = false) Long clm_aim_type_id,
            @RequestParam(value="clm_status_id", required = false) Long clm_status_id,
            @RequestParam(value="clm_note", required = false) String clm_note,

            @RequestParam(value="part_world_main_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_main_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_main_table_search", required = false) String table_search,
            @RequestParam(value="part_world_main_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_main_table_page", required = false) Long page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> ops_User_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Client_List> tms_Client_List;
        List<Service_Type_List> service_Type_List;
        List<Claims_Status_List> claims_Status_List;
        List<Claims_Aim_Type_List> claims_Aim_Type_List;
        List<Currency_Use_List> clm_Sum_Currency_List;
        List<Currency_Use_List> clm_Rate_Currency_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatSumDate = "";
        Date Date1 = null;

        String FormatRateDate = "";
        Date Date2 = null;

        if (clm_sum_currency_date != "" && clm_sum_currency_date != null) {
            FormatSumDate = clm_sum_currency_date;
            Date1 = df.parse(FormatSumDate);
            FormatSumDate = newdf.format(Date1);
        }

        if (clm_rate_currency_date != "" && clm_rate_currency_date != null) {
            FormatRateDate = clm_rate_currency_date;
            Date2 = df.parse(FormatRateDate);
            FormatRateDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaims")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_date)
                            .setParameter(4, clm_num)
                            .setParameter(5, cnt_id)
                            .setParameter(6, ser_id)
                            .setParameter(7, user_id)
                            .setParameter(8, clm_sum)
                            .setParameter(9, clm_rate)
                            .setParameter(10, clm_aim_type_id)
                            .setParameter(11, clm_note)
                            .setParameter(12, clm_sum_currency_id)
                            .setParameter(13, FormatSumDate)
                            .setParameter(14, clm_rate_currency_id)
                            .setParameter(15, FormatRateDate)
                            ;
                    AddClmFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaims")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_date)
                            .setParameter(5, clm_num)
                            .setParameter(6, cnt_id)
                            .setParameter(7, ser_id)
                            .setParameter(8, user_id)
                            .setParameter(9, clm_sum)
                            .setParameter(10, clm_rate)
                            .setParameter(11, clm_aim_type_id)
                            .setParameter(12, clm_note)
                            .setParameter(13, clm_sum_currency_id)
                            .setParameter(14, FormatSumDate)
                            .setParameter(15, clm_rate_currency_id)
                            .setParameter(16, FormatRateDate)
                            ;
                    EditClmFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaims")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id);
                    DelClmFLCQuery.execute();
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

        mav.addObject("clm_id", clm_id);

        mav.addObject("clm_num", clm_num);

        mav.addObject("clm_date", clm_date);

        if(user_id != null) {
            mav.addObject("user_id", user_id);
            ops_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("user_list", ops_User_List);
        }

        if(cnt_id != null) {
            mav.addObject("cnt_id", cnt_id);
            tms_Client_List = tms_Client_ListRepository.queryTMSOneClientByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id);
            mav.addObject("transport_list", tms_Client_List);
        }

        if(ser_id != null) {
            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);
        }

        mav.addObject("clm_sum", clm_sum);

        mav.addObject("clm_sum_currency_id", clm_sum_currency_id);
        clm_Sum_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
        mav.addObject("clm_sum_currency_list", clm_Sum_Currency_List);

        mav.addObject("clm_sum_currency_date", clm_sum_currency_date);

        mav.addObject("clm_rate", clm_rate);

        mav.addObject("clm_rate_currency_id", clm_rate_currency_id);
        clm_Rate_Currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
        mav.addObject("clm_rate_currency_list", clm_Rate_Currency_List);

        mav.addObject("clm_rate_currency_date", clm_rate_currency_date);

        mav.addObject("clm_status_id", clm_status_id);
        claims_Status_List = (List<Claims_Status_List>) claims_Status_ListRepository.findAll();
        mav.addObject("claims_status_list", claims_Status_List);

        mav.addObject("clm_aim_type_id", clm_aim_type_id);
        claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
        mav.addObject("clm_aim_type_list", claims_Aim_Type_List);

        mav.addObject("clm_note", clm_note);

        mav.addObject("part_world_main_table_order_column", order_column);
        mav.addObject("part_world_main_table_order_type", order_type);
        mav.addObject("part_world_main_table_search", table_search);
        mav.addObject("part_world_main_table_pagelen", pagelen);
        mav.addObject("part_world_main_table_page", page);

        mav.setViewName("/part_world/main_detail");
        return mav;
    }

    @PostMapping("/main_clone")
    public ModelAndView main_clone(
            @RequestParam(name = "clm_id") long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_CloneTMSClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_check")
    public ModelAndView main_check(
            @RequestParam(name = "clm_id") long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_CheckTMSClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_full_check")
    public ModelAndView main_full_check(
            @RequestParam(name = "clm_id") Long clm_id,
            @RequestParam(name = "clm_status_id") Long clm_status_id,
            @RequestParam(name = "load_date", required = false) String load_date,
            @RequestParam(name = "loaded_date", required = false) String loaded_date,
            @RequestParam(name = "border_date", required = false) String border_date,
            @RequestParam(name = "custom_date", required = false) String custom_date,
            @RequestParam(name = "unload_date", required = false) String unload_date,
            @RequestParam(name = "unloaded_date", required = false) String unloaded_date,
            @RequestParam(name = "close_date", required = false) String close_date
    ) throws ParseException {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatLoadDate = "", FormatLoadedDate = "", FormatBorderDate = "", FormatCustomDate = "", FormatUnloadDate = "", FormatUnloadedDate = "", FormatCloseDate  = "";
        Date DateLoad = null, DateLoaded = null, DateBorder = null, DateCustom = null, DateUnload = null, DateUnloaded = null, DateClose = null;

        if (load_date != "" && load_date != null) {
            DateLoad = df.parse(load_date);
            FormatLoadDate = newdf.format(DateLoad);
        }
        /*Load Date*/
        if (loaded_date != "" && loaded_date != null) {
            DateLoaded = df.parse(loaded_date);
            FormatLoadedDate = newdf.format(DateLoaded);
        }
        /*Loaded Date*/
        if (border_date != "" && border_date != null) {
            DateBorder = df.parse(border_date);
            FormatBorderDate = newdf.format(DateBorder);
        }
        /*Border Date*/
        if (custom_date != "" && custom_date != null) {
            DateCustom = df.parse(custom_date);
            FormatCustomDate= newdf.format(DateCustom);
        }
        /*Custom Date*/
        if (unload_date != "" && unload_date != null) {
            DateUnload = df.parse(unload_date);
            FormatUnloadDate= newdf.format(DateUnload);
        }
        /*Unload Date*/
        if (unloaded_date != "" && unloaded_date != null) {
            DateUnloaded = df.parse(unloaded_date);
            FormatUnloadedDate= newdf.format(DateUnloaded);
        }
        /*Unloaded Date*/
        if (close_date != "" && close_date != null) {
            DateClose = df.parse(close_date);
            FormatCloseDate= newdf.format(DateClose);
        }
        /*Close Date*/

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_FullCheckTMSClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, FormatLoadDate)
                .setParameter(5, FormatLoadedDate)
                .setParameter(6, FormatBorderDate)
                .setParameter(7, FormatCustomDate)
                .setParameter(8, FormatUnloadDate)
                .setParameter(9, FormatUnloadedDate)
                .setParameter(10, FormatCloseDate)
                ;
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_uncheck")
    public ModelAndView main_uncheck(
            @RequestParam(name = "clm_id") long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainUnCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_UnCheckTMSClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        MainUnCheckQuery.execute();
        return null;
    }

    @PostMapping("/get_timelog_table")
    public JSONDatatable get_timelog_table(
            @RequestParam(name = "clm_id") Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(clm_id != null && clm_id > 0) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimsTimeLogsRepository.queryClaimsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/get_tasks_table")
    public JSONDatatable get_tasks_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimTasksListRepository.queryClaimTasksMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @RequestMapping("/tasks_detail")
    public ModelAndView tasks_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="clm_id") Long clm_id,
                                     @RequestParam(value="clm_num") String clm_num,
                                     @RequestParam(value="cnt_name") String cnt_name,

                                     @RequestParam(value="clmtl_id") Long clmtl_id,

                                     @RequestParam(value="part_world_tasks_table_order_column") Long order_column,
                                     @RequestParam(value="part_world_tasks_table_order_type") String order_type,
                                     @RequestParam(value="part_world_tasks_table_search") String table_search,
                                     @RequestParam(value="part_world_tasks_table_pagelen") Long pagelen,
                                     @RequestParam(value="part_world_tasks_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Claims_Tasks_Type_List> claims_Tasks_Type_List;
        List<User_List> start_User_List;
        List<User_List> end_User_List;
        List<DetailClaimTasksList> detailClaimTasksList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        if(mode == 0){
            claims_Tasks_Type_List = claims_Tasks_Type_ListRepository.queryGetNewTasksListByClaimID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id);
            mav.addObject("clm_task_list", claims_Tasks_Type_List);

            mav.addObject("start_user_id", user_List.get(0).id);
            start_User_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("start_user_list", start_User_List);

            end_User_List = user_ListRepository.queryUserAndSubsAllOrderDetail(user_List.get(0).id);
            mav.addObject("end_user_list", end_User_List);
        }
        else if(mode == 1){
            mav.addObject("clmtl_id", clmtl_id);

            detailClaimTasksList = detailClaimTasksListRepository.queryClaimTasksDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtl_id);

            mav.addObject("clm_task_id", detailClaimTasksList.get(0).clm_task_id);
            claims_Tasks_Type_List = claims_Tasks_Type_ListRepository.queryGetEditTasksListByClaimID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTasksList.get(0).clm_task_id);
            mav.addObject("clm_task_list", claims_Tasks_Type_List);

            mav.addObject("start_user_id", detailClaimTasksList.get(0).start_user_id);
            start_User_List = user_ListRepository.queryUserByID(detailClaimTasksList.get(0).start_user_id);
            mav.addObject("start_user_list", start_User_List);

            mav.addObject("start_date", detailClaimTasksList.get(0).start_date);

            if(detailClaimTasksList.get(0).end_user_id != null) {
                mav.addObject("end_user_id", detailClaimTasksList.get(0).end_user_id);
                end_User_List = user_ListRepository.queryUserAndSubsAllOrderDetail(user_List.get(0).id);
                mav.addObject("end_user_list", end_User_List);
            }else{
                end_User_List = user_ListRepository.queryUserAndSubsAllOrderDetail(user_List.get(0).id);
                mav.addObject("end_user_list", end_User_List);
            }

            mav.addObject("end_date", detailClaimTasksList.get(0).end_date);

            mav.addObject("clmtl_note", detailClaimTasksList.get(0).clmtl_note);

            mav.addObject("deadline_date", detailClaimTasksList.get(0).deadline_date);
        }
        else{
            mav.addObject("clmtl_id", clmtl_id);

            detailClaimTasksList = detailClaimTasksListRepository.queryClaimTasksDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtl_id);

            mav.addObject("clm_task_id", detailClaimTasksList.get(0).clm_task_id);
            claims_Tasks_Type_List = claims_Tasks_Type_ListRepository.queryGetOneTaskListByClaimID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTasksList.get(0).clm_task_id);
            mav.addObject("clm_task_list", claims_Tasks_Type_List);

            mav.addObject("start_user_id", detailClaimTasksList.get(0).start_user_id);
            start_User_List = user_ListRepository.queryUserByID(detailClaimTasksList.get(0).start_user_id);
            mav.addObject("start_user_list", start_User_List);

            mav.addObject("start_date", detailClaimTasksList.get(0).start_date);

            if(detailClaimTasksList.get(0).end_user_id != null) {
                mav.addObject("end_user_id", detailClaimTasksList.get(0).end_user_id);
                end_User_List = user_ListRepository.queryUserByID(detailClaimTasksList.get(0).end_user_id);
                mav.addObject("end_user_list", end_User_List);
            }else{

            }

            mav.addObject("end_date", detailClaimTasksList.get(0).end_date);

            mav.addObject("clmtl_note", detailClaimTasksList.get(0).clmtl_note);

            mav.addObject("deadline_date", detailClaimTasksList.get(0).deadline_date);
        }

        mav.addObject("part_world_tasks_table_order_column", order_column);
        mav.addObject("part_world_tasks_table_order_type", order_type);
        mav.addObject("part_world_tasks_table_search", table_search);
        mav.addObject("part_world_tasks_table_pagelen", pagelen);
        mav.addObject("part_world_tasks_table_page", page);

        mav.addObject("part_world_tab_name", "tab-2");

        mav.setViewName("/part_world/tasks_detail");
        return mav;
    }

    @PostMapping("/tasks_model")
    public ModelAndView tasks_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmtl_id", required = false) Long clmtl_id,

            @RequestParam(value="clm_task_id", required = false) Long clm_task_id,
            @RequestParam(value="start_user_id", required = false) Long start_user_id,
            @RequestParam(value="end_user_id", required = false, defaultValue = "0") Long end_user_id,
            @RequestParam(value="clmtl_note", required = false) String clmtl_note,

            @RequestParam(value="deadline_date", required = false) String deadline_date,

            @RequestParam(value="part_world_tasks_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_tasks_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_tasks_table_search", required = false) String table_search,
            @RequestParam(value="part_world_tasks_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_tasks_table_page", required = false) Long page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        String DeadLineDate = "";
        Date Date1 = null;

        if (deadline_date != "" && deadline_date != null) {
            DeadLineDate = deadline_date;
            Date1 = df.parse(DeadLineDate);
            DeadLineDate = newdf.format(Date1);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTaskFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimTask")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_task_id)
                            .setParameter(5, start_user_id)
                            .setParameter(6, end_user_id)
                            .setParameter(7, clmtl_note)
                            .setParameter(8, DeadLineDate)
                            ;
                    AddClmTaskFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTaskClmFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimTask")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmtl_id)
                            .setParameter(4, clm_task_id)
                            .setParameter(5, start_user_id)
                            .setParameter(6, end_user_id)
                            .setParameter(7, clmtl_note)
                            .setParameter(8, DeadLineDate)
                            ;
                    EditTaskClmFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimTask")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmtl_id);
                    DelClmFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);

        mav.addObject("part_world_tasks_table_order_column", order_column);
        mav.addObject("part_world_tasks_table_order_type", order_type);
        mav.addObject("part_world_tasks_table_search", table_search);
        mav.addObject("part_world_tasks_table_pagelen", pagelen);
        mav.addObject("part_world_tasks_table_page", page);

        mav.addObject("part_world_tab_name", "tab-2");

        mav.setViewName("/part_world/tasks_detail");
        return mav;
    }

    @PostMapping("/task_check")
    public ModelAndView task_check(
            @RequestParam(name = "clmtl_id") long clmtl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery TaskCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_CheckTMSClaimTask")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmtl_id)
                .setParameter(4, user_List.get(0).id);
        TaskCheckQuery.execute();
        return null;
    }

    @PostMapping("/task_uncheck")
    public ModelAndView task_uncheck(
            @RequestParam(name = "clmtl_id") long clmtl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery TaskUnCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_UnCheckTMSClaimTask")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmtl_id);
        TaskUnCheckQuery.execute();
        return null;
    }

    @PostMapping("/get_ways_table")
    public JSONDatatable get_ways_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimWaysListRepository.queryClaimWaysMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/fill_temp")
    public JSONDatatable fill_temp(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery FillClmWayFLCTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS_VIEW.pr_FillClimsWayTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id);
            FillClmWayFLCTempQuery.execute();
        }
        return result;
    }

    @RequestMapping("/ways_detail")
    public ModelAndView ways_detail(@RequestParam(value="mode") Long mode,
                                    @RequestParam(value="clm_id") Long clm_id,
                                    @RequestParam(value="clm_num") String clm_num,
                                    @RequestParam(value="cnt_name") String cnt_name,

                                    @RequestParam(value="clmwl_id") Long clmwl_id,

                                    @RequestParam(value="part_world_ways_table_order_column") Long part_world_ways_table_order_column,
                                    @RequestParam(value="part_world_ways_table_order_type") String part_world_ways_table_order_type,
                                    @RequestParam(value="part_world_ways_table_search") String part_world_ways_table_search,
                                    @RequestParam(value="part_world_ways_table_pagelen") Long part_world_ways_table_pagelen,
                                    @RequestParam(value="part_world_ways_table_page") Long part_world_ways_table_page,

                                    @RequestParam(value="part_world_table_order_column") Long part_world_table_order_column,
                                    @RequestParam(value="part_world_table_order_type") String part_world_table_order_type,
                                    @RequestParam(value="part_world_table_search") String part_world_table_search,
                                    @RequestParam(value="part_world_table_pagelen") Long part_world_table_pagelen,
                                    @RequestParam(value="part_world_table_page") Long part_world_table_page,

                                    @RequestParam(value="download_rn", required = false) Long download_rn

    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.addObject("download_rn", download_rn);

        mav.setViewName("/part_world/ways_detail");
        return mav;
    }

    @PostMapping("/get_way_tab_table")
    public JSONDatatable get_way_tab_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_way_id", required = false) Long clm_way_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0 && clm_way_id != null) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimWaysTabListRepository.queryClaimWaysTabMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clm_way_id));
        }
        return result;
    }

    @PostMapping("/ways_model")
    public ModelAndView ways_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmwl_id", required = false) Long clmwl_id,

            @RequestParam(value="clm_way_id", required = false) Long clm_way_id,
            @RequestParam(value="country_id", required = false) Long country_id,
            @RequestParam(value="clm_way_city", required = false) String clm_way_city,
            @RequestParam(value="clm_way_street", required = false) String clm_way_street,
            @RequestParam(value="clm_way_home", required = false) String clm_way_home,
            @RequestParam(value="clm_way_date", required = false) String clm_way_date,
            @RequestParam(value="clm_way_fact_date", required = false) String clm_way_fact_date,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmWayFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWay")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, clm_way_date);
                    AddClmWayFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmWayFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWay")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmwl_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, clm_way_date);
                    EditClmWayFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmWayFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWay")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmwl_id);
                    DelClmWayFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);

        mav.addObject("part_world_ways_table_order_column", order_column);
        mav.addObject("part_world_ways_table_order_type", order_type);
        mav.addObject("part_world_ways_table_search", table_search);
        mav.addObject("part_world_ways_table_pagelen", pagelen);
        mav.addObject("part_world_ways_table_page", page);

        mav.setViewName("/part_world/ways_detail");
        return mav;
    }

    @PostMapping("/get_loads_table")
    public JSONDatatable get_loads_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @RequestMapping("/loads_detail")
    public ModelAndView loads_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="clm_id") Long clm_id,
                                     @RequestParam(value="clm_num") String clm_num,
                                     @RequestParam(value="cnt_name") String cnt_name,

                                     @RequestParam(value="clmll_id") Long clmll_id,

                                     @RequestParam(value="part_world_loads_table_order_column") Long order_column,
                                     @RequestParam(value="part_world_loads_table_order_type") String order_type,
                                     @RequestParam(value="part_world_loads_table_search") String table_search,
                                     @RequestParam(value="part_world_loads_table_pagelen") Long pagelen,
                                     @RequestParam(value="part_world_loads_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailClaimLoadsList> detailClaimLoadsList;
        List<WeightTypeList> weightType;
        List<PalletList> palletList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        if(mode == 0){
            mav.addObject("weight_type_id", 1);
            weightType = weightTypeRepository.queryWeightTypeAll();
            mav.addObject("weightType", weightType);

            palletList = palletListRepository.queryPalletTypeListBy();
            mav.addObject("pal_type_list", palletList);

            mav.addObject("clm_lenght", 0);
            mav.addObject("clm_width", 0);
            mav.addObject("clm_height", 0);
        }
        else if(mode == 1){
            mav.addObject("clmll_id", clmll_id);

            detailClaimLoadsList = detailClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id);

            mav.addObject("clm_load_name", detailClaimLoadsList.get(0).clm_load_name);

            mav.addObject("clm_load_weight", detailClaimLoadsList.get(0).clm_load_weight);

            mav.addObject("weight_type_id", detailClaimLoadsList.get(0).weight_type_id);
            weightType = weightTypeRepository.queryWeightTypeAll();
            mav.addObject("weightType", weightType);

            mav.addObject("clm_load_dims", detailClaimLoadsList.get(0).clm_load_dims);

            mav.addObject("clm_load_notes", detailClaimLoadsList.get(0).clm_load_notes);

            palletList = palletListRepository.queryPalletTypeListBy();
            mav.addObject("pal_type_list", palletList);

            mav.addObject("clm_lenght", detailClaimLoadsList.get(0).clm_lenght);
            mav.addObject("clm_width", detailClaimLoadsList.get(0).clm_width);
            mav.addObject("clm_height", detailClaimLoadsList.get(0).clm_height);
        }
        else{
            mav.addObject("clmll_id", clmll_id);

            detailClaimLoadsList = detailClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id);

            mav.addObject("clm_load_name", detailClaimLoadsList.get(0).clm_load_name);

            mav.addObject("clm_load_weight", detailClaimLoadsList.get(0).clm_load_weight);

            mav.addObject("weight_type_id", detailClaimLoadsList.get(0).weight_type_id);
            weightType = weightTypeRepository.queryWeightTypeAll();
            mav.addObject("weightType", weightType);

            mav.addObject("clm_load_dims", detailClaimLoadsList.get(0).clm_load_dims);

            mav.addObject("clm_load_notes", detailClaimLoadsList.get(0).clm_load_notes);

            palletList = palletListRepository.queryPalletTypeListBy();
            mav.addObject("pal_type_list", palletList);

            mav.addObject("clm_lenght", detailClaimLoadsList.get(0).clm_lenght);
            mav.addObject("clm_width", detailClaimLoadsList.get(0).clm_width);
            mav.addObject("clm_height", detailClaimLoadsList.get(0).clm_height);
        }

        mav.addObject("part_world_loads_table_order_column", order_column);
        mav.addObject("part_world_loads_table_order_type", order_type);
        mav.addObject("part_world_loads_table_search", table_search);
        mav.addObject("part_world_loads_table_pagelen", pagelen);
        mav.addObject("part_world_loads_table_page", page);

        mav.addObject("part_world_tab_name", "tab-4");

        mav.setViewName("/part_world/loads_detail");
        return mav;
    }

    @PostMapping("/loads_model")
    public ModelAndView loads_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmll_id") Long clmll_id,

            @RequestParam(value="clm_load_name", required = false) String clm_load_name,
            @RequestParam(value="clm_load_weight", required = false) String clm_load_weight,
            @RequestParam(value="clm_load_dims", required = false) String clm_load_dims,
            @RequestParam(value="clm_load_notes", required = false) String clm_load_notes,

            @RequestParam(value="weight_type_id", required = false) Long weight_type_id,
            @RequestParam(value="clm_lenght", required = false, defaultValue = "0") Long clm_lenght,
            @RequestParam(value="clm_width", required = false, defaultValue = "0") Long clm_width,
            @RequestParam(value="clm_height", required = false, defaultValue = "0") Long clm_height,

            @RequestParam(value="part_world_loads_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_loads_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_loads_table_search", required = false) String table_search,
            @RequestParam(value="part_world_loads_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_loads_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<WeightTypeList> weightType;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmLoadFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimLoad")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_load_name)
                            .setParameter(5, clm_load_weight)
                            .setParameter(6, clm_load_dims)
                            .setParameter(7, clm_load_notes)
                            .setParameter(8, weight_type_id)
                            .setParameter(9, clm_lenght)
                            .setParameter(10, clm_width)
                            .setParameter(11, clm_height)
                            ;
                    AddClmLoadFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmLoadFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimLoad")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmll_id)
                            .setParameter(4, clm_load_name)
                            .setParameter(5, clm_load_weight)
                            .setParameter(6, clm_load_dims)
                            .setParameter(7, clm_load_notes)
                            .setParameter(8, weight_type_id)
                            .setParameter(9, clm_lenght)
                            .setParameter(10, clm_width)
                            .setParameter(11, clm_height)
                            ;
                    EditClmLoadFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmLoadFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimLoad")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmll_id);
                    DelClmLoadFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);

        mav.addObject("clmll_id", clmll_id);

        mav.addObject("clm_load_name", clm_load_name);

        mav.addObject("clm_load_weight", clm_load_weight);

        mav.addObject("weight_type_id", weight_type_id);
        weightType = weightTypeRepository.queryWeightTypeAll();
        mav.addObject("weightType", weightType);

        mav.addObject("clm_load_dims", clm_load_dims);

        mav.addObject("clm_load_notes", clm_load_notes);

        mav.addObject("clm_lenght", clm_lenght);
        mav.addObject("clm_width", clm_width);
        mav.addObject("clm_height", clm_height);

        mav.addObject("part_world_loads_table_order_column", order_column);
        mav.addObject("part_world_loads_table_order_type", order_type);
        mav.addObject("part_world_loads_table_search", table_search);
        mav.addObject("part_world_loads_table_pagelen", pagelen);
        mav.addObject("part_world_loads_table_page", page);

        mav.addObject("part_world_tab_name", "tab-4");

        mav.setViewName("/part_world/loads_detail");
        return mav;
    }

    @PostMapping("/loads_clone")
    public ModelAndView loads_clone(
            @RequestParam(name = "clmll_id") Long clmll_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery LoadsCloneQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_CloneTMSClaimLoad")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmll_id);
        LoadsCloneQuery.execute();
        return null;
    }

    @PostMapping("/get_trans_table")
    public JSONDatatable get_trans_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimTransListRepository.queryClaimTransMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @RequestMapping("/trans_detail")
    public ModelAndView trans_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="clm_id") Long clm_id,
                                     @RequestParam(value="clm_num") String clm_num,
                                     @RequestParam(value="cnt_name") String cnt_name,

                                     @RequestParam(value="clmtrl_id") Long clmtrl_id,

                                     @RequestParam(value="part_world_trans_table_order_column") Long order_column,
                                     @RequestParam(value="part_world_trans_table_order_type") String order_type,
                                     @RequestParam(value="part_world_trans_table_search") String table_search,
                                     @RequestParam(value="part_world_trans_table_pagelen") Long pagelen,
                                     @RequestParam(value="part_world_trans_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Transport_List> transport_list;
        List<TMS_Cars_List> tms_Cars_List;
        List<TMS_Trailers_List> tms_Trailers_List;
        List<TMS_Drivers_List> tms_Drivers_List;
        List<DetailClaimTransList> detailClaimTransList;
        List<Cars_Type_List> cars_Type_List;
        List<Trailers_Type_List> trailers_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        if(mode == 0){
            Long currClientID = new Long(5577);

            mav.addObject("cnt_id", currClientID);
            transport_list = transport_listRepository.queryTransportByUserID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("transport_list", transport_list);

            cars_Type_List = cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, currClientID);
            mav.addObject("cntc_type_list", cars_Type_List);

            trailers_Type_List = trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, currClientID);
            mav.addObject("cntt_type_list", trailers_Type_List);

            tms_Drivers_List = tms_Drivers_ListRepository.queryTMSClientDriversListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, currClientID);
            mav.addObject("drivers_list", tms_Drivers_List);
        }
        else if(mode == 1){
            mav.addObject("clmtrl_id", clmtrl_id);
            detailClaimTransList = detailClaimTransListRepository.queryClaimsDetailTransListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtrl_id);

            mav.addObject("cnt_id", detailClaimTransList.get(0).cnt_id);
            transport_list = transport_listRepository.queryTransportByUserID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("transport_list", transport_list);

            if(detailClaimTransList.get(0).cntc_type_id != null && detailClaimTransList.get(0).cntc_type_id >0) {
                mav.addObject("cntc_type_id", detailClaimTransList.get(0).cntc_type_id);
                cars_Type_List = cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntc_type_list", cars_Type_List);

                mav.addObject("cntc_id", detailClaimTransList.get(0).cntc_id);
                tms_Cars_List = tms_Cars_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id, detailClaimTransList.get(0).cntc_type_id);
                mav.addObject("auto_list", tms_Cars_List);
            }else {
                cars_Type_List = cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntc_type_list", cars_Type_List);
            }

            if(detailClaimTransList.get(0).cntt_type_id != null && detailClaimTransList.get(0).cntt_type_id > 0) {
                mav.addObject("cntt_type_id", detailClaimTransList.get(0).cntt_type_id);
                trailers_Type_List = trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntt_type_list", trailers_Type_List);

                mav.addObject("cntt_id", detailClaimTransList.get(0).cntt_id);
                tms_Trailers_List = tms_Trailers_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id, detailClaimTransList.get(0).cntt_type_id);
                mav.addObject("trailers_list", tms_Trailers_List);
            }else{
                trailers_Type_List = trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntt_type_list", trailers_Type_List);
            }

            mav.addObject("cntd_id", detailClaimTransList.get(0).cntd_id);
            tms_Drivers_List = tms_Drivers_ListRepository.queryTMSClientDriversListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
            mav.addObject("drivers_list", tms_Drivers_List);
        }
        else{
            mav.addObject("clmtrl_id", clmtrl_id);
            detailClaimTransList = detailClaimTransListRepository.queryClaimsDetailTransListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtrl_id);

            mav.addObject("cnt_id", detailClaimTransList.get(0).cnt_id);
            transport_list = transport_listRepository.queryTransportByUserID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("transport_list", transport_list);

            if(detailClaimTransList.get(0).cntc_type_id != null && detailClaimTransList.get(0).cntc_type_id >0) {
                mav.addObject("cntc_type_id", detailClaimTransList.get(0).cntc_type_id);
                cars_Type_List = cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntc_type_list", cars_Type_List);

                mav.addObject("cntc_id", detailClaimTransList.get(0).cntc_id);
                tms_Cars_List = tms_Cars_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id, detailClaimTransList.get(0).cntc_type_id);
                mav.addObject("auto_list", tms_Cars_List);
            }else {
                cars_Type_List = cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntc_type_list", cars_Type_List);
            }

            if(detailClaimTransList.get(0).cntt_type_id != null && detailClaimTransList.get(0).cntt_type_id > 0) {
                mav.addObject("cntt_type_id", detailClaimTransList.get(0).cntt_type_id);
                trailers_Type_List = trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntt_type_list", trailers_Type_List);

                mav.addObject("cntt_id", detailClaimTransList.get(0).cntt_id);
                tms_Trailers_List = tms_Trailers_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id, detailClaimTransList.get(0).cntt_type_id);
                mav.addObject("trailers_list", tms_Trailers_List);
            }else{
                trailers_Type_List = trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
                mav.addObject("cntt_type_list", trailers_Type_List);
            }

            mav.addObject("cntd_id", detailClaimTransList.get(0).cntd_id);
            tms_Drivers_List = tms_Drivers_ListRepository.queryTMSClientDriversListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, detailClaimTransList.get(0).cnt_id);
            mav.addObject("drivers_list", tms_Drivers_List);
        }

        mav.addObject("part_world_trans_table_order_column", order_column);
        mav.addObject("part_world_trans_table_order_type", order_type);
        mav.addObject("part_world_trans_table_search", table_search);
        mav.addObject("part_world_trans_table_pagelen", pagelen);
        mav.addObject("part_world_trans_table_page", page);

        mav.addObject("part_world_tab_name", "tab-5");

        mav.setViewName("/part_world/trans_detail");
        return mav;
    }

    @GetMapping("/get_cars_type")
    public JSONDatatable get_cars_type(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(cars_Type_ListRepository.queryCurClientCarsTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @GetMapping("/get_cars")
    public JSONDatatable get_cars(
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="cntc_type_id") Long cntc_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cntc_type_id != null && cnt_id > 0 && cntc_type_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(tms_Cars_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, cntc_type_id));
        }
        return result;
    }

    @GetMapping("/get_trailers_type")
    public JSONDatatable get_trailers_type(
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(trailers_Type_ListRepository.queryCurClientTrailersTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @GetMapping("/get_trailers")
    public JSONDatatable get_trailers(
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id,
            @RequestParam(value="cntt_type_id") Long cntt_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cntt_type_id != null && cnt_id > 0 && cntt_type_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(tms_Trailers_ListRepository.queryTMSClientCarListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, cntt_type_id));
        }
        return result;
    }

    @GetMapping("/get_drivers")
    public JSONDatatable get_drivers(
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(tms_Drivers_ListRepository.queryTMSClientDriversListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @PostMapping("/trans_model")
    public ModelAndView trans_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmtrl_id") Long clmtrl_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="cntc_id", defaultValue = "0") Long cntc_id,
            @RequestParam(value="cntt_id", defaultValue = "0") Long cntt_id,
            @RequestParam(value="cntd_id", defaultValue = "0") Long cntd_id,

            @RequestParam(value="part_world_loads_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_loads_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_loads_table_search", required = false) String table_search,
            @RequestParam(value="part_world_loads_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_loads_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTransFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimTransport")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, cnt_id)
                            .setParameter(5, cntc_id)
                            .setParameter(6, cntt_id)
                            .setParameter(7, cntd_id);
                    AddClmTransFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTransFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimTransport")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmtrl_id)
                            .setParameter(4, cnt_id)
                            .setParameter(5, cntc_id)
                            .setParameter(6, cntt_id)
                            .setParameter(7, cntd_id);
                    EditClmTransFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTransFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimTransport")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmtrl_id);
                    DelClmTransFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);

        mav.addObject("part_world_loads_table_order_column", order_column);
        mav.addObject("part_world_loads_table_order_type", order_type);
        mav.addObject("part_world_loads_table_search", table_search);
        mav.addObject("part_world_loads_table_pagelen", pagelen);
        mav.addObject("part_world_loads_table_page", page);

        mav.addObject("part_world_tab_name", "tab-5");

        mav.setViewName("/part_world/trans_detail");
        return mav;
    }

    @PostMapping("/get_trans_link_table")
    public JSONDatatable get_trans_link_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimTransLinkListRepository.queryClaimTransMenuLinkListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/get_trans_ways_table")
    public JSONDatatable get_trans_ways_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0 && clmtrl_id != null && clmtrl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimTransWaysListRepository.queryClaimTransWaysMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id,clmtrl_id));
        }
        return result;
    }

    @PostMapping("/trans_ways_down")
    public ModelAndView trans_ways_down(
            @RequestParam(name = "clm_id") Long clm_id,
            @RequestParam(name = "clmtrl_id") Long clmtrl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DownloadTransWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_DownloadTMSTranWaysLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, clmtrl_id)
                ;
        DownloadTransWayQuery.execute();
        return null;
    }

    @PostMapping("/trans_ways_clear")
    public ModelAndView trans_ways_clear(
            @RequestParam(name = "clm_id") Long clm_id,
            @RequestParam(name = "clmtrl_id") Long clmtrl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery ClearTransWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_ClearTMSTranWaysLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, clmtrl_id)
                ;
        ClearTransWayQuery.execute();
        return null;
    }

    @PostMapping("/trans_ways_delete")
    public ModelAndView trans_ways_delete(
            @RequestParam(name = "clmtwl_id") Long clmtwl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DeleteTransWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_DelTMSTranWaysLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmtwl_id);
        DeleteTransWayQuery.execute();
        return null;
    }

    @RequestMapping("/trans_ways_detail")
    public ModelAndView trans_ways_detail(@RequestParam(value="mode") Long mode,
                                          @RequestParam(value="clm_id") Long clm_id,
                                          @RequestParam(value="clm_num") String clm_num,
                                          @RequestParam(value="cnt_name") String cnt_name,

                                          @RequestParam(value="clmtrl_id") Long clmtrl_id,
                                          @RequestParam(value="trans_name") String trans_name,
                                          @RequestParam(value="cntc_type_sname") String cntc_type_sname,
                                          @RequestParam(value="cntc_type_number") String cntc_type_number,

                                          @RequestParam(value="part_world_transways_table_order_column") Long part_world_transways_table_order_column,
                                          @RequestParam(value="part_world_transways_table_order_type") String part_world_transways_table_order_type,
                                          @RequestParam(value="part_world_transways_table_search") String part_world_transways_table_search,
                                          @RequestParam(value="part_world_transways_table_pagelen") Long part_world_transways_table_pagelen,
                                          @RequestParam(value="part_world_transways_table_page") Long part_world_transways_table_page,

                                          @RequestParam(value="part_world_translink_table_order_column", required = false) Long part_world_translink_table_order_column,
                                          @RequestParam(value="part_world_translink_table_order_type", required = false) String part_world_translink_table_order_type,
                                          @RequestParam(value="part_world_translink_table_search", required = false) String part_world_translink_table_search,
                                          @RequestParam(value="part_world_translink_table_pagelen", required = false) Long part_world_translink_table_pagelen,
                                          @RequestParam(value="part_world_translink_table_page", required = false) Long part_world_translink_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<ListClaimTransWays> listClaimTransWays;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("clmtrl_id", clmtrl_id);
        mav.addObject("trans_name", trans_name);
        mav.addObject("cntc_type_sname", cntc_type_sname);
        mav.addObject("cntc_type_number", cntc_type_number);

        if(mode == 0){
            listClaimTransWays = listClaimTransWaysRepository.queryClaimTransWaysListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clmtrl_id);
            mav.addObject("clmwl_list", listClaimTransWays);
        }
        else{
        }

        mav.addObject("part_world_transways_table_order_column", part_world_transways_table_order_column);
        mav.addObject("part_world_transways_table_order_type", part_world_transways_table_order_type);
        mav.addObject("part_world_transways_table_search", part_world_transways_table_search);
        mav.addObject("part_world_transways_table_pagelen", part_world_transways_table_pagelen);
        mav.addObject("part_world_transways_table_page", part_world_transways_table_page);

        mav.addObject("part_world_translink_table_order_column", part_world_translink_table_order_column);
        mav.addObject("part_world_translink_table_order_type", part_world_translink_table_order_type);
        mav.addObject("part_world_translink_table_search", part_world_translink_table_search);
        mav.addObject("part_world_translink_table_pagelen", part_world_translink_table_pagelen);
        mav.addObject("part_world_translink_table_page", part_world_translink_table_page);

        mav.addObject("part_world_tab_name", "tab-6");

        mav.setViewName("/part_world/transways_detail");
        return mav;
    }

    @PostMapping("/trans_ways_model")
    public ModelAndView trans_ways_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,

            @RequestParam(value="clmtrl_id") Long clmtrl_id,
            @RequestParam(value="trans_name") String trans_name,
            @RequestParam(value="cntc_type_sname") String cntc_type_sname,
            @RequestParam(value="cntc_type_number") String cntc_type_number,

            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="part_world_transways_table_order_column", required = false) Long part_world_transways_table_order_column,
            @RequestParam(value="part_world_transways_table_order_type", required = false) String part_world_transways_table_order_type,
            @RequestParam(value="part_world_transways_table_search", required = false) String part_world_transways_table_search,
            @RequestParam(value="part_world_transways_table_pagelen", required = false) Long part_world_transways_table_pagelen,
            @RequestParam(value="part_world_transways_table_page", required = false) Long part_world_transways_table_page,

            @RequestParam(value="part_world_translink_table_order_column", required = false) Long part_world_translink_table_order_column,
            @RequestParam(value="part_world_translink_table_order_type", required = false) String part_world_translink_table_order_type,
            @RequestParam(value="part_world_translink_table_search", required = false) String part_world_translink_table_search,
            @RequestParam(value="part_world_translink_table_pagelen", required = false) Long part_world_translink_table_pagelen,
            @RequestParam(value="part_world_translink_table_page", required = false) Long part_world_translink_table_page
    ){
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSTranWaysLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmtrl_id)
                            .setParameter(5, clmwl_id)
                            ;
                    AddClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("clmtrl_id", clmtrl_id);
        mav.addObject("trans_name", trans_name);
        mav.addObject("cntc_type_sname", cntc_type_sname);
        mav.addObject("cntc_type_number", cntc_type_number);

        mav.addObject("part_world_transways_table_order_column", part_world_transways_table_order_column);
        mav.addObject("part_world_transways_table_order_type", part_world_transways_table_order_type);
        mav.addObject("part_world_transways_table_search", part_world_transways_table_search);
        mav.addObject("part_world_transways_table_pagelen", part_world_transways_table_pagelen);
        mav.addObject("part_world_transways_table_page", part_world_transways_table_page);

        mav.addObject("part_world_translink_table_order_column", part_world_translink_table_order_column);
        mav.addObject("part_world_translink_table_order_type", part_world_translink_table_order_type);
        mav.addObject("part_world_translink_table_search", part_world_translink_table_search);
        mav.addObject("part_world_translink_table_pagelen", part_world_translink_table_pagelen);
        mav.addObject("part_world_translink_table_page", part_world_translink_table_page);

        mav.addObject("part_world_tab_name", "tab-6");

        mav.setViewName("/part_world/transways_detail");
        return mav;
    }

    @PostMapping("/get_costs_table")
    public JSONDatatable get_costs_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimCostsListRepository.queryClaimCostsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @RequestMapping("/costs_detail")
    public ModelAndView costs_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="clm_id") Long clm_id,
                                     @RequestParam(value="clm_num") String clm_num,
                                     @RequestParam(value="cnt_name") String cnt_name,

                                     @RequestParam(value="clmcl_id") Long clmcl_id,

                                     @RequestParam(value="part_world_costs_table_order_column") Long order_column,
                                     @RequestParam(value="part_world_costs_table_order_type") String order_type,
                                     @RequestParam(value="part_world_costs_table_search") String table_search,
                                     @RequestParam(value="part_world_costs_table_pagelen") Long pagelen,
                                     @RequestParam(value="part_world_costs_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Claims_Costs_Type_List> tms_Costs_Type_List;
        List<DetailClaimCostsList> detailClaimCostsList;
        List<Currency_Use_List> currency_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);

        if(mode == 0){
            tms_Costs_Type_List = claims_Costs_Type_ListRepository.queryNewCostTypeFullListByID();
            mav.addObject("clmc_type_list", tms_Costs_Type_List);

            mav.addObject("clmcl_sum", "0,00");

            mav.addObject("currency_id", 978);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("clmcl_period", "0");
            mav.addObject("clmcl_day_cost", "0,00");
        }
        else{
            detailClaimCostsList = detailClaimCostsListRepository.queryClaimsDetailCostsListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmcl_id);
            mav.addObject("clmcl_id", detailClaimCostsList.get(0).clmcl_id);

            if(detailClaimCostsList.get(0).clmc_type_id == 1 || detailClaimCostsList.get(0).clmc_type_id == 8){
                mav.addObject("clmc_type_id", detailClaimCostsList.get(0).clmc_type_id);
                tms_Costs_Type_List = claims_Costs_Type_ListRepository.queryOneCostTypeListByID(detailClaimCostsList.get(0).clmc_type_id);
                mav.addObject("clmc_type_list", tms_Costs_Type_List);
            }
            else{
                mav.addObject("clmc_type_id", detailClaimCostsList.get(0).clmc_type_id);
                tms_Costs_Type_List = claims_Costs_Type_ListRepository.queryNewCostTypeFullListByID();
                mav.addObject("clmc_type_list", tms_Costs_Type_List);
            }

            mav.addObject("clmcl_sum", detailClaimCostsList.get(0).clmcl_sum);

            mav.addObject("clmcl_period", detailClaimCostsList.get(0).clmcl_period);
            mav.addObject("clmcl_day_cost", detailClaimCostsList.get(0).clmcl_day_cost);

            mav.addObject("currency_id", detailClaimCostsList.get(0).currency_id);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("currency_date", detailClaimCostsList.get(0).currency_date);

            mav.addObject("clmcl_note", detailClaimCostsList.get(0).clmcl_note);
        }

        mav.addObject("part_world_costs_table_order_column", order_column);
        mav.addObject("part_world_costs_table_order_type", order_type);
        mav.addObject("part_world_costs_table_search", table_search);
        mav.addObject("part_world_costs_table_pagelen", pagelen);
        mav.addObject("part_world_costs_table_page", page);

        mav.addObject("part_world_tab_name", "tab-7");

        mav.setViewName("/part_world/costs_detail");
        return mav;
    }

    @PostMapping("/costs_model")
    public ModelAndView costs_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmcl_id") Long clmcl_id,
            @RequestParam(value="clmc_type_id") Long clmc_type_id,

            @RequestParam(value="clmcl_sum") String clmcl_sum,
            @RequestParam(value="currency_id") Long currency_id,
            @RequestParam(value="currency_date") String currency_date,

            @RequestParam(value="clmcl_note") String clmcl_note,

            @RequestParam(value="clmcl_period") Long clmcl_period,
            @RequestParam(value="clmcl_day_cost") String clmcl_day_cost,

            @RequestParam(value="part_world_costs_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_costs_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_costs_table_search", required = false) String table_search,
            @RequestParam(value="part_world_costs_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_costs_table_page", required = false) Long page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Claims_Costs_Type_List> tms_Costs_Type_List;
        List<Currency_Use_List> currency_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatCurrencyDate = "";
        Date Date1 = null;

        if (currency_date != "" && currency_date != null) {
            FormatCurrencyDate = currency_date;
            Date1 = df.parse(FormatCurrencyDate);
            FormatCurrencyDate = newdf.format(Date1);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmCostsFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSFLGClaimCosts")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmc_type_id)
                            .setParameter(5, clmcl_sum)
                            .setParameter(6, clmcl_note)
                            .setParameter(7, currency_id)
                            .setParameter(8, FormatCurrencyDate)
                            .setParameter(9, clmcl_period)
                            .setParameter(10, clmcl_day_cost)
                            ;
                    AddClmCostsFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmCostsFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSFLGClaimCosts")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmcl_id)
                            .setParameter(4, clmc_type_id)
                            .setParameter(5, clmcl_sum)
                            .setParameter(6, clmcl_note)
                            .setParameter(7, currency_id)
                            .setParameter(8, FormatCurrencyDate)
                            .setParameter(9, clmcl_period)
                            .setParameter(10, clmcl_day_cost)
                            ;
                    EditClmCostsFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmCostsFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimCosts")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clmcl_id);
                    DelClmCostsFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);

        mav.addObject("clmcl_id", clmcl_id);

        mav.addObject("clmc_type_list", clmc_type_id);
        tms_Costs_Type_List = (List<Claims_Costs_Type_List>) claims_Costs_Type_ListRepository.findAll();
        mav.addObject("clmc_type_list", tms_Costs_Type_List);

        mav.addObject("clmcl_sum", clmcl_sum);

        mav.addObject("currency_id", currency_id);
        currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
        mav.addObject("currency_list", currency_List);

        mav.addObject("currency_date", currency_date);

        mav.addObject("clmcl_note", clmcl_note);

        mav.addObject("part_world_costs_table_order_column", order_column);
        mav.addObject("part_world_costs_table_order_type", order_type);
        mav.addObject("part_world_costs_table_search", table_search);
        mav.addObject("part_world_costs_table_pagelen", pagelen);
        mav.addObject("part_world_costs_table_page", page);

        mav.addObject("part_world_tab_name", "tab-7");

        mav.setViewName("/part_world/costs_detail");
        return mav;
    }

    @RequestMapping("/download_detail")
    public ModelAndView download_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="clm_id") Long clm_id,
                                        @RequestParam(value="clm_num") String clm_num,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="clmwl_id") Long clmwl_id,

                                        @RequestParam(value="rn") Long download_rn,
                                        @RequestParam(value="order_id", required = false, defaultValue = "1") Long order_id,

                                        @RequestParam(value="part_world_download_table_order_column", required = false) Long order_column,
                                        @RequestParam(value="part_world_download_table_order_type", required = false) String order_type,
                                        @RequestParam(value="part_world_download_table_search", required = false) String table_search,
                                        @RequestParam(value="part_world_download_table_pagelen", required = false) Long pagelen,
                                        @RequestParam(value="part_world_download_table_page", required = false) Long page,

                                        @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
                                        @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
                                        @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
                                        @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
                                        @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

                                        @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
                                        @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
                                        @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
                                        @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
                                        @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("download_rn", download_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(1));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);

            mav.addObject("clm_way_company",  detailClaimWaysTab.get(0).clm_way_company);

            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_download_table_order_column", order_column);
        mav.addObject("part_world_download_table_order_type", order_type);
        mav.addObject("part_world_download_table_search", table_search);
        mav.addObject("part_world_download_table_pagelen", pagelen);
        mav.addObject("part_world_download_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/download_detail");
        return mav;
    }

    @PostMapping("/download_model")
    public ModelAndView download_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="download_rn") Long download_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "1") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,
            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="clm_way_company") String clm_way_company,

            @RequestParam(value="part_world_download_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_download_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_download_table_search", required = false) String table_search,
            @RequestParam(value="part_world_download_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_download_table_page", required = false) Long page,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

            @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
            @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
            @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
            @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
            @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, clm_way_company)
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, clm_way_company)
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            ;
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("download_rn", download_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_download_table_order_column", order_column);
        mav.addObject("part_world_download_table_order_type", order_type);
        mav.addObject("part_world_download_table_search", table_search);
        mav.addObject("part_world_download_table_pagelen", pagelen);
        mav.addObject("part_world_download_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/download_detail");
        return mav;
    }

    @RequestMapping("/cust_forv_detail")
    public ModelAndView cust_forv_detail(@RequestParam(value="mode") Long mode,
                                         @RequestParam(value="clm_id") Long clm_id,
                                         @RequestParam(value="clm_num") String clm_num,
                                         @RequestParam(value="cnt_name") String cnt_name,
                                         @RequestParam(value="clmwl_id") Long clmwl_id,

                                         @RequestParam(value="rn") Long download_rn,
                                         @RequestParam(value="order_id", required = false, defaultValue = "2") Long order_id,

                                         @RequestParam(value="part_world_cust_forv_table_order_column", required = false) Long order_column,
                                         @RequestParam(value="part_world_cust_forv_table_order_type", required = false) String order_type,
                                         @RequestParam(value="part_world_cust_forv_table_search", required = false) String table_search,
                                         @RequestParam(value="part_world_cust_forv_table_pagelen", required = false) Long pagelen,
                                         @RequestParam(value="part_world_cust_forv_table_page", required = false) Long page,

                                         @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
                                         @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
                                         @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
                                         @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
                                         @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

                                         @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
                                         @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
                                         @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
                                         @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
                                         @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("download_rn", download_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(2));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);
            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_cust_forv_table_order_column", order_column);
        mav.addObject("part_world_cust_forv_table_order_type", order_type);
        mav.addObject("part_world_cust_forv_table_search", table_search);
        mav.addObject("part_world_cust_forv_table_pagelen", pagelen);
        mav.addObject("part_world_cust_forv_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/cust_forv_detail");
        return mav;
    }

    @PostMapping("/cust_forv_model")
    public ModelAndView cust_forv_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="download_rn") Long download_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "1") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,

            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="part_world_cust_forv_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_cust_forv_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_cust_forv_table_search", required = false) String table_search,
            @RequestParam(value="part_world_cust_forv_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_cust_forv_table_page", required = false) Long page,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

            @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
            @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
            @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
            @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
            @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, "")
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, "")
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id);
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("download_rn", download_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_cust_forv_table_order_column", order_column);
        mav.addObject("part_world_cust_forv_table_order_type", order_type);
        mav.addObject("part_world_cust_forv_table_search", table_search);
        mav.addObject("part_world_cust_forv_table_pagelen", pagelen);
        mav.addObject("part_world_cust_forv_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/cust_forv_detail");
        return mav;
    }

    @RequestMapping("/move_forv_detail")
    public ModelAndView move_forv_detail(@RequestParam(value="mode") Long mode,
                                         @RequestParam(value="clm_id") Long clm_id,
                                         @RequestParam(value="clm_num") String clm_num,
                                         @RequestParam(value="cnt_name") String cnt_name,
                                         @RequestParam(value="clmwl_id") Long clmwl_id,

                                         @RequestParam(value="rn") Long download_rn,
                                         @RequestParam(value="order_id", required = false, defaultValue = "3") Long order_id,

                                         @RequestParam(value="part_world_move_forv_table_order_column", required = false) Long order_column,
                                         @RequestParam(value="part_world_move_forv_table_order_type", required = false) String order_type,
                                         @RequestParam(value="part_world_move_forv_table_search", required = false) String table_search,
                                         @RequestParam(value="part_world_move_forv_table_pagelen", required = false) Long pagelen,
                                         @RequestParam(value="part_world_move_forv_table_page", required = false) Long page,

                                         @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
                                         @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
                                         @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
                                         @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
                                         @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

                                         @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
                                         @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
                                         @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
                                         @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
                                         @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("download_rn", download_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(3));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);
            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_move_forv_table_order_column", order_column);
        mav.addObject("part_world_move_forv_table_order_type", order_type);
        mav.addObject("part_world_move_forv_table_search", table_search);
        mav.addObject("part_world_move_forv_table_pagelen", pagelen);
        mav.addObject("part_world_move_forv_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/move_forv_detail");
        return mav;
    }

    @PostMapping("/move_forv_model")
    public ModelAndView move_forv_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="download_rn") Long download_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "3") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,

            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="part_world_move_forv_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_move_forv_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_move_forv_table_search", required = false) String table_search,
            @RequestParam(value="part_world_move_forv_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_move_forv_table_page", required = false) Long page,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

            @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
            @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
            @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
            @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
            @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, "")
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, "")
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id);
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("download_rn", download_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_move_forv_table_order_column", order_column);
        mav.addObject("part_world_move_forv_table_order_type", order_type);
        mav.addObject("part_world_move_forv_table_search", table_search);
        mav.addObject("part_world_move_forv_table_pagelen", pagelen);
        mav.addObject("part_world_move_forv_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/move_forv_detail");
        return mav;
    }

    @RequestMapping("/cust_after_detail")
    public ModelAndView cust_after_detail(@RequestParam(value="mode") Long mode,
                                          @RequestParam(value="clm_id") Long clm_id,
                                          @RequestParam(value="clm_num") String clm_num,
                                          @RequestParam(value="cnt_name") String cnt_name,
                                          @RequestParam(value="clmwl_id") Long clmwl_id,

                                          @RequestParam(value="rn") Long download_rn,
                                          @RequestParam(value="order_id", required = false, defaultValue = "5") Long order_id,

                                          @RequestParam(value="part_world_cust_after_table_order_column", required = false) Long order_column,
                                          @RequestParam(value="part_world_cust_after_table_order_type", required = false) String order_type,
                                          @RequestParam(value="part_world_cust_after_table_search", required = false) String table_search,
                                          @RequestParam(value="part_world_cust_after_table_pagelen", required = false) Long pagelen,
                                          @RequestParam(value="part_world_cust_after_table_page", required = false) Long page,

                                          @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
                                          @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
                                          @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
                                          @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
                                          @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

                                          @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
                                          @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
                                          @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
                                          @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
                                          @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("download_rn", download_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(4));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);
            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_cust_after_table_order_column", order_column);
        mav.addObject("part_world_cust_after_table_order_type", order_type);
        mav.addObject("part_world_cust_after_table_search", table_search);
        mav.addObject("part_world_cust_after_table_pagelen", pagelen);
        mav.addObject("part_world_cust_after_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/cust_after_detail");
        return mav;
    }

    @PostMapping("/cust_after_model")
    public ModelAndView cust_after_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="download_rn") Long download_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "5") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,

            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="part_world_cust_after_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_cust_after_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_cust_after_table_search", required = false) String table_search,
            @RequestParam(value="part_world_cust_after_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_cust_after_table_page", required = false) Long page,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

            @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
            @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
            @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
            @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
            @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, "")
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, "")
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id);
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("download_rn", download_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_cust_after_table_order_column", order_column);
        mav.addObject("part_world_cust_after_table_order_type", order_type);
        mav.addObject("part_world_cust_after_table_search", table_search);
        mav.addObject("part_world_cust_after_table_pagelen", pagelen);
        mav.addObject("part_world_cust_after_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/cust_after_detail");
        return mav;
    }

    @RequestMapping("/move_after_detail")
    public ModelAndView move_after_detail(@RequestParam(value="mode") Long mode,
                                          @RequestParam(value="clm_id") Long clm_id,
                                          @RequestParam(value="clm_num") String clm_num,
                                          @RequestParam(value="cnt_name") String cnt_name,
                                          @RequestParam(value="clmwl_id") Long clmwl_id,

                                          @RequestParam(value="rn") Long download_rn,
                                          @RequestParam(value="order_id", required = false, defaultValue = "4") Long order_id,

                                          @RequestParam(value="part_world_move_after_table_order_column") Long order_column,
                                          @RequestParam(value="part_world_move_after_table_order_type") String order_type,
                                          @RequestParam(value="part_world_move_after_table_search") String table_search,
                                          @RequestParam(value="part_world_move_after_table_pagelen") Long pagelen,
                                          @RequestParam(value="part_world_move_after_table_page") Long page,

                                          @RequestParam(value="part_world_ways_table_order_column") Long part_world_ways_table_order_column,
                                          @RequestParam(value="part_world_ways_table_order_type") String part_world_ways_table_order_type,
                                          @RequestParam(value="part_world_ways_table_search") String part_world_ways_table_search,
                                          @RequestParam(value="part_world_ways_table_pagelen") Long part_world_ways_table_pagelen,
                                          @RequestParam(value="part_world_ways_table_page") Long part_world_ways_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("download_rn", download_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(3));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);
            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_move_after_table_order_column", order_column);
        mav.addObject("part_world_move_after_table_order_type", order_type);
        mav.addObject("part_world_move_after_table_search", table_search);
        mav.addObject("part_world_move_after_table_pagelen", pagelen);
        mav.addObject("part_world_move_after_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.setViewName("/part_world/move_after_detail");
        return mav;
    }

    @PostMapping("/move_after_model")
    public ModelAndView move_after_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="download_rn") Long download_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "4") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,

            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="part_world_move_after_table_order_column") Long order_column,
            @RequestParam(value="part_world_move_after_table_order_type") String order_type,
            @RequestParam(value="part_world_move_after_table_search") String table_search,
            @RequestParam(value="part_world_move_after_table_pagelen") Long pagelen,
            @RequestParam(value="part_world_move_after_table_page") Long page,

            @RequestParam(value="part_world_ways_table_order_column") Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type") String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search") String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen") Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page") Long part_world_ways_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, "")
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, "")
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id);
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("download_rn", download_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_move_after_table_order_column", order_column);
        mav.addObject("part_world_move_after_table_order_type", order_type);
        mav.addObject("part_world_move_after_table_search", table_search);
        mav.addObject("part_world_move_after_table_pagelen", pagelen);
        mav.addObject("part_world_move_after_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.setViewName("/part_world/move_after_detail");
        return mav;
    }

    @RequestMapping("/upload_detail")
    public ModelAndView upload_detail(@RequestParam(value="mode") Long mode,
                                      @RequestParam(value="clm_id") Long clm_id,
                                      @RequestParam(value="clm_num") String clm_num,
                                      @RequestParam(value="cnt_name") String cnt_name,
                                      @RequestParam(value="clmwl_id") Long clmwl_id,

                                      @RequestParam(value="rn") Long upload_rn,
                                      @RequestParam(value="order_id", required = false, defaultValue = "6") Long order_id,

                                      @RequestParam(value="part_world_upload_table_order_column", required = false) Long order_column,
                                      @RequestParam(value="part_world_upload_table_order_type", required = false) String order_type,
                                      @RequestParam(value="part_world_upload_table_search", required = false) String table_search,
                                      @RequestParam(value="part_world_upload_table_pagelen", required = false) Long pagelen,
                                      @RequestParam(value="part_world_upload_table_page", required = false) Long page,

                                      @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
                                      @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
                                      @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
                                      @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
                                      @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

                                      @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
                                      @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
                                      @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
                                      @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
                                      @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;
        List<DetailClaimWaysTab> detailClaimWaysTab;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);

        mav.addObject("upload_rn", upload_rn);
        mav.addObject("order_id", order_id);

        if(mode == 0){
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(new Long(5));
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id", new Long (2));
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);
        }
        else{
            detailClaimWaysTab = detailClaimWaysTabRepository.queryClaimWaysTabDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, order_id, clmwl_id);

            mav.addObject("clm_way_id", detailClaimWaysTab.get(0).clm_way_id);
            tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(detailClaimWaysTab.get(0).clm_way_id);
            mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

            mav.addObject("country_id",  detailClaimWaysTab.get(0).country_id);
            country_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_List);

            mav.addObject("clm_way_date",  detailClaimWaysTab.get(0).clm_way_date);
            mav.addObject("clm_way_fact_date",  detailClaimWaysTab.get(0).clm_way_fact_date);

            mav.addObject("clm_way_city",  detailClaimWaysTab.get(0).clm_way_city);
            mav.addObject("clm_way_street",  detailClaimWaysTab.get(0).clm_way_street);
            mav.addObject("clm_way_home",  detailClaimWaysTab.get(0).clm_way_home);

            mav.addObject("clm_way_company",  detailClaimWaysTab.get(0).clm_way_company);

            mav.addObject("order_id",  detailClaimWaysTab.get(0).order_id);
        }

        mav.addObject("part_world_upload_table_order_column", order_column);
        mav.addObject("part_world_upload_table_order_type", order_type);
        mav.addObject("part_world_upload_table_search", table_search);
        mav.addObject("part_world_upload_table_pagelen", pagelen);
        mav.addObject("part_world_upload_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/upload_detail");
        return mav;
    }

    @PostMapping("/upload_model")
    public ModelAndView upload_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_num") String clm_num,
            @RequestParam(value="cnt_name") String cnt_name,
            @RequestParam(value="clmwl_id") Long clmwl_id,

            @RequestParam(value="upload_rn") Long upload_rn,
            @RequestParam(value="order_id", required = false, defaultValue = "6") Long order_id,

            @RequestParam(value="clm_zip_code", required = false, defaultValue = "") String clm_zip_code,

            @RequestParam(value="country_id") Long country_id,
            @RequestParam(value="clm_way_id") Long clm_way_id,

            @RequestParam(value="clm_way_date") String clm_way_date,
            @RequestParam(value="clm_way_fact_date") String clm_way_fact_date,

            @RequestParam(value="clm_way_city") String clm_way_city,
            @RequestParam(value="clm_way_street") String clm_way_street,
            @RequestParam(value="clm_way_home") String clm_way_home,

            @RequestParam(value="clm_way_company") String clm_way_company,

            @RequestParam(value="part_world_upload_table_order_column", required = false) Long order_column,
            @RequestParam(value="part_world_upload_table_order_type", required = false) String order_type,
            @RequestParam(value="part_world_upload_table_search", required = false) String table_search,
            @RequestParam(value="part_world_upload_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="part_world_upload_table_page", required = false) Long page,

            @RequestParam(value="part_world_ways_table_order_column", required = false) Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type", required = false) String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search", required = false) String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen", required = false) Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page", required = false) Long part_world_ways_table_page,

            @RequestParam(value="part_world_table_order_column", required = false) Long part_world_table_order_column,
            @RequestParam(value="part_world_table_order_type", required = false) String part_world_table_order_type,
            @RequestParam(value="part_world_table_search", required = false) String part_world_table_search,
            @RequestParam(value="part_world_table_pagelen", required = false) Long part_world_table_pagelen,
            @RequestParam(value="part_world_table_page", required = false) Long part_world_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<TMS_Claims_Way_Type_List> tms_Claims_Ways_Type_List;
        List<Country_Full_List> country_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatWayDate = "";
        Date Date1 = null;

        String FormatWayFactDate = "";
        Date Date2 = null;

        if (clm_way_date != "" && clm_way_date != null) {
            FormatWayDate = clm_way_date;
            Date1 = df.parse(FormatWayDate);
            FormatWayDate = newdf.format(Date1);
        }

        if (clm_way_fact_date != "" && clm_way_fact_date != null) {
            FormatWayFactDate = clm_way_fact_date;
            Date2 = df.parse(FormatWayFactDate);
            FormatWayFactDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clm_way_id)
                            .setParameter(5, country_id)
                            .setParameter(6, clm_way_city)
                            .setParameter(7, clm_way_street)
                            .setParameter(8, clm_way_home)
                            .setParameter(9, FormatWayDate)
                            .setParameter(10, FormatWayFactDate)
                            .setParameter(11, clm_way_company)
                            .setParameter(12, clm_zip_code)
                            .setParameter(13, order_id)
                            ;
                    AddClmTMSFLCQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id)
                            .setParameter(5, clm_way_id)
                            .setParameter(6, country_id)
                            .setParameter(7, clm_way_city)
                            .setParameter(8, clm_way_street)
                            .setParameter(9, clm_way_home)
                            .setParameter(10, FormatWayDate)
                            .setParameter(11, FormatWayFactDate)
                            .setParameter(12, clm_way_company)
                            .setParameter(13, clm_zip_code)
                            .setParameter(14, order_id)
                            ;
                    EditClmTMSFLCQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelClmTMSFLCQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimWayTemp")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, clm_id)
                            .setParameter(4, clmwl_id);
                    DelClmTMSFLCQuery.execute();
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
        mav.addObject("clm_id", clm_id);
        mav.addObject("clm_num", clm_num);
        mav.addObject("cnt_name", cnt_name);
        mav.addObject("clmwl_id", clmwl_id);
        mav.addObject("upload_rn", upload_rn);

        mav.addObject("clm_way_id", clm_way_id);
        tms_Claims_Ways_Type_List = tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id);
        mav.addObject("clm_way_list", tms_Claims_Ways_Type_List);

        mav.addObject("country_id", country_id);
        country_List = country_ListRepository.queryAllContryList();
        mav.addObject("country_list", country_List);

        mav.addObject("clm_way_date", clm_way_date);
        mav.addObject("clm_way_fact_date", clm_way_fact_date);

        mav.addObject("clm_way_city", clm_way_city);
        mav.addObject("clm_way_street", clm_way_street);
        mav.addObject("clm_way_home", clm_way_home);

        mav.addObject("part_world_upload_table_order_column", order_column);
        mav.addObject("part_world_upload_table_order_type", order_type);
        mav.addObject("part_world_upload_table_search", table_search);
        mav.addObject("part_world_upload_table_pagelen", pagelen);
        mav.addObject("part_world_upload_table_page", page);

        mav.addObject("part_world_ways_table_order_column", part_world_ways_table_order_column);
        mav.addObject("part_world_ways_table_order_type", part_world_ways_table_order_type);
        mav.addObject("part_world_ways_table_search", part_world_ways_table_search);
        mav.addObject("part_world_ways_table_pagelen", part_world_ways_table_pagelen);
        mav.addObject("part_world_ways_table_page", part_world_ways_table_page);

        mav.addObject("part_world_table_order_column", part_world_table_order_column);
        mav.addObject("part_world_table_order_type", part_world_table_order_type);
        mav.addObject("part_world_table_search", part_world_table_search);
        mav.addObject("part_world_table_pagelen", part_world_table_pagelen);
        mav.addObject("part_world_table_page", part_world_table_page);

        mav.setViewName("/part_world/upload_detail");
        return mav;
    }

    @PostMapping("/fill_all_way")
    public ModelAndView fill_all_way(
            @RequestParam(value="clm_id") Long clm_id,

            @RequestParam(value="part_world_ways_table_order_column") Long part_world_ways_table_order_column,
            @RequestParam(value="part_world_ways_table_order_type") String part_world_ways_table_order_type,
            @RequestParam(value="part_world_ways_table_search") String part_world_ways_table_search,
            @RequestParam(value="part_world_ways_table_pagelen") Long part_world_ways_table_pagelen,
            @RequestParam(value="part_world_ways_table_page") Long part_world_ways_table_page
    ){
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddAllClmTMSFLCQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimWay")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        AddAllClmTMSFLCQuery.execute();

        mav.addObject("clm_id", clm_id);

        mav.addObject("table_order_column", part_world_ways_table_order_column);
        mav.addObject("table_order_type",  part_world_ways_table_order_type);
        mav.addObject("table_search",  part_world_ways_table_search);
        mav.addObject("table_pagelen",  part_world_ways_table_pagelen);
        mav.addObject("table_page",  part_world_ways_table_page);

        mav.addObject("part_world_tab_name", "tab-3");

        mav.setViewName("/part_world/cover");
        return mav;
    }

    @PostMapping("/get_errors_table")
    public JSONDatatable get_errors_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimErrorsListRepository.queryClaimErrorsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }
}

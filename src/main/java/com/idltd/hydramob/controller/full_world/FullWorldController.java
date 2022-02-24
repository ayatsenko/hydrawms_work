package com.idltd.hydramob.controller.full_world;

import com.idltd.hydramob.entity.Claims_Aim_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.full_world.*;
import com.idltd.hydramob.repository.list.*;
import com.idltd.hydramob.repository.part_world.DetailFLGClaimTransListRepository;
import com.idltd.hydramob.repository.part_world.DetailFLGClaimWaysRepository;
import com.idltd.hydramob.repository.part_world.MenuFLGClaimTransListRepository;
import com.idltd.hydramob.repository.part_world.MenuFLGClaimWaysListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
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
@RequestMapping("/full_world")
public class FullWorldController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuFLCClaimsListRepository menuFLCClaimsListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Claims_Status_ListRepository claims_Status_ListRepository;
    private Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository;
    private Cars_Type_ListRepository cars_Type_ListRepository;
    private Trailers_Type_ListRepository trailers_Type_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private DetailClaimsListRepository detailClaimsListRepository;
    private DetailNewFLCClaimsListRepository detailNewFLCClaimsListRepository;

    private MenuClaimsTimeLogsRepository menuClaimsTimeLogsRepository;

    private MenuClaimTasksListRepository menuClaimTasksListRepository;
    private Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository;
    private DetailClaimTasksListRepository detailClaimTasksListRepository;

    private MenuFLGClaimWaysListRepository menuFLGClaimWaysListRepository;
    private MenuClaimWaysTabListRepository menuClaimWaysTabListRepository;
    private Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository;
    private Country_ListRepository country_ListRepository;
    private DetailFLGClaimWaysRepository detailFLGClaimWaysRepository;

    private MenuClaimLoadsListRepository menuClaimLoadsListRepository;
    private DetailClaimLoadsListRepository detailClaimLoadsListRepository;
    private WeightTypeRepository weightTypeRepository;

    private MenuClaimTransListRepository menuClaimTransListRepository;
    private MenuFLGClaimTransListRepository menuFLGClaimTransListRepository;
    private Transport_ListRepository transport_listRepository;
    private TMS_Cars_ListRepository tms_Cars_ListRepository;
    private TMS_Trailers_ListRepository tms_Trailers_ListRepository;
    private TMS_Drivers_ListRepository tms_Drivers_ListRepository;
    private DetailClaimTransListRepository detailClaimTransListRepository;
    private DetailFLGClaimTransListRepository detailFLGClaimTransListRepository;

    private MenuClaimCostsListRepository menuClaimCostsListRepository;
    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;
    private DetailClaimCostsListRepository detailClaimCostsListRepository;

    private TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository;
    private DetailClaimWaysTabRepository detailClaimWaysTabRepository;

    private MenuClaimErrorsListRepository menuClaimErrorsListRepository;

    private MenuClaimNotesListRepository menuClaimNotesListRepository;
    private DetailClaimNotesListRepository detailClaimNotesListRepository;
    private TMS_Notes_Types_ListRepository tms_Notes_Types_ListRepository;

    private MenuClaimReportsListRepository menuClaimReportsListRepository;
    private MenuClaimReportParamsListRepository menuClaimReportParamsListRepository;

    private MenuClaimPalletsListRepository menuClaimPalletsListRepository;
    private PalletListRepository palletListRepository;

    public FullWorldController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuFLCClaimsListRepository menuFLCClaimsListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Claims_Status_ListRepository claims_Status_ListRepository,
            Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository,
            Cars_Type_ListRepository cars_Type_ListRepository,
            Trailers_Type_ListRepository trailers_Type_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            DetailClaimsListRepository detailClaimsListRepository,
            DetailNewFLCClaimsListRepository detailNewFLCClaimsListRepository,

            MenuClaimsTimeLogsRepository menuClaimsTimeLogsRepository,

            MenuClaimTasksListRepository menuClaimTasksListRepository,
            Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository,
            DetailClaimTasksListRepository detailClaimTasksListRepository,

            MenuFLGClaimWaysListRepository menuFLGClaimWaysListRepository,
            Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository,
            Country_ListRepository country_ListRepository,
            DetailFLGClaimWaysRepository detailFLGClaimWaysRepository,

            MenuClaimLoadsListRepository menuClaimLoadsListRepository,
            DetailClaimLoadsListRepository detailClaimLoadsListRepository,
            WeightTypeRepository weightTypeRepository,

            MenuClaimTransListRepository menuClaimTransListRepository,
            MenuFLGClaimTransListRepository menuFLGClaimTransListRepository,
            Transport_ListRepository transport_listRepository,
            TMS_Cars_ListRepository tms_Cars_ListRepository,
            TMS_Trailers_ListRepository tms_Trailers_ListRepository,
            TMS_Drivers_ListRepository tms_Drivers_ListRepository,
            DetailClaimTransListRepository detailClaimTransListRepository,
            DetailFLGClaimTransListRepository detailFLGClaimTransListRepository,

            MenuClaimCostsListRepository menuClaimCostsListRepository,
            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,
            DetailClaimCostsListRepository detailClaimCostsListRepository,

            TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository,
            DetailClaimWaysTabRepository detailClaimWaysTabRepository,

            MenuClaimErrorsListRepository menuClaimErrorsListRepository,

            MenuClaimNotesListRepository menuClaimNotesListRepository,
            DetailClaimNotesListRepository detailClaimNotesListRepository,
            TMS_Notes_Types_ListRepository tms_Notes_Types_ListRepository,

            MenuClaimReportsListRepository menuClaimReportsListRepository,
            MenuClaimReportParamsListRepository menuClaimReportParamsListRepository,

            MenuClaimPalletsListRepository menuClaimPalletsListRepository,
            PalletListRepository palletListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuFLCClaimsListRepository = menuFLCClaimsListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.claims_Status_ListRepository = claims_Status_ListRepository;
        this.claims_Aim_Type_ListRepository = claims_Aim_Type_ListRepository;
        this.cars_Type_ListRepository = cars_Type_ListRepository;
        this.trailers_Type_ListRepository = trailers_Type_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.detailClaimsListRepository = detailClaimsListRepository;
        this.detailNewFLCClaimsListRepository = detailNewFLCClaimsListRepository;

        this.menuClaimsTimeLogsRepository = menuClaimsTimeLogsRepository;

        this.menuClaimTasksListRepository = menuClaimTasksListRepository;
        this.claims_Tasks_Type_ListRepository = claims_Tasks_Type_ListRepository;
        this.detailClaimTasksListRepository = detailClaimTasksListRepository;

        this.menuFLGClaimWaysListRepository = menuFLGClaimWaysListRepository;
        this.claims_Ways_Type_ListRepository = claims_Ways_Type_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.detailFLGClaimWaysRepository = detailFLGClaimWaysRepository;

        this.menuClaimLoadsListRepository = menuClaimLoadsListRepository;
        this.detailClaimLoadsListRepository = detailClaimLoadsListRepository;
        this.weightTypeRepository = weightTypeRepository;

        this.menuClaimTransListRepository = menuClaimTransListRepository;
        this.menuFLGClaimTransListRepository = menuFLGClaimTransListRepository;
        this.transport_listRepository = transport_listRepository;
        this.tms_Cars_ListRepository = tms_Cars_ListRepository;
        this.tms_Trailers_ListRepository = tms_Trailers_ListRepository;
        this.tms_Drivers_ListRepository = tms_Drivers_ListRepository;
        this.detailClaimTransListRepository = detailClaimTransListRepository;
        this.detailFLGClaimTransListRepository = detailFLGClaimTransListRepository;

        this.menuClaimCostsListRepository = menuClaimCostsListRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;
        this.detailClaimCostsListRepository = detailClaimCostsListRepository;

        this.tms_Claims_Ways_Type_ListRepository = tms_Claims_Ways_Type_ListRepository;
        this.detailClaimWaysTabRepository = detailClaimWaysTabRepository;

        this.menuClaimErrorsListRepository = menuClaimErrorsListRepository;

        this.menuClaimNotesListRepository = menuClaimNotesListRepository;
        this.detailClaimNotesListRepository = detailClaimNotesListRepository;
        this.tms_Notes_Types_ListRepository = tms_Notes_Types_ListRepository;

        this.menuClaimReportsListRepository = menuClaimReportsListRepository;
        this.menuClaimReportParamsListRepository = menuClaimReportParamsListRepository;

        this.menuClaimPalletsListRepository = menuClaimPalletsListRepository;
        this.palletListRepository = palletListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id,

                              @RequestParam(name = "full_world_filter_id", required = false, defaultValue = "0") Long full_world_filter_id,
                              @RequestParam(name = "full_world_filter_start_date", required = false) String full_world_filter_start_date,
                              @RequestParam(name = "full_world_filter_end_date", required = false) String full_world_filter_end_date,                              
                              
                              @RequestParam(name = "full_world_main_table_order_column", required = false) Long full_world_main_table_order_column,
                              @RequestParam(name = "full_world_main_table_order_type", required = false) String full_world_main_table_order_type,
                              @RequestParam(name = "full_world_main_table_search", required = false) String full_world_main_table_search,
                              @RequestParam(name = "full_world_main_table_pagelen", required = false) Long full_world_main_table_pagelen,
                              @RequestParam(name = "full_world_main_table_page", required = false) Long full_world_main_table_page,

                              @RequestParam(name = "clmtl_id", required = false) Long clmtl_id,

                              @RequestParam(name = "full_world_tasks_table_order_column", required = false) Long full_world_tasks_table_order_column,
                              @RequestParam(name = "full_world_tasks_table_order_type", required = false) String full_world_tasks_table_order_type,
                              @RequestParam(name = "full_world_tasks_table_search", required = false) String full_world_tasks_table_search,
                              @RequestParam(name = "full_world_tasks_table_pagelen", required = false) Long full_world_tasks_table_pagelen,
                              @RequestParam(name = "full_world_tasks_table_page", required = false) Long full_world_tasks_table_page,

                              @RequestParam(name = "full_world_ways_table_order_column", required = false) Long full_world_ways_table_order_column,
                              @RequestParam(name = "full_world_ways_table_order_type", required = false) String full_world_ways_table_order_type,
                              @RequestParam(name = "full_world_ways_table_search", required = false) String full_world_ways_table_search,
                              @RequestParam(name = "full_world_ways_table_pagelen", required = false) Long full_world_ways_table_pagelen,
                              @RequestParam(name = "full_world_ways_table_page", required = false) Long full_world_ways_table_page,

                              @RequestParam(name = "full_world_tab_name", required = false) String tab_name
    ){
        model.addObject("clm_id", clm_id);

        model.addObject("full_world_main_table_order_column", full_world_main_table_order_column);
        model.addObject("full_world_main_table_order_type", full_world_main_table_order_type);
        model.addObject("full_world_main_table_search", full_world_main_table_search);
        model.addObject("full_world_main_table_pagelen", full_world_main_table_pagelen);
        model.addObject("full_world_main_table_page", full_world_main_table_page);

        model.addObject("full_world_filter_id", full_world_filter_id);
        model.addObject("full_world_filter_start_date", full_world_filter_start_date);
        model.addObject("full_world_filter_end_date", full_world_filter_end_date);

        model.addObject("clmtl_id", clmtl_id);

        model.addObject("full_world_tasks_table_order_column", full_world_tasks_table_order_column);
        model.addObject("full_world_tasks_table_order_type", full_world_tasks_table_order_type);
        model.addObject("full_world_tasks_table_search", full_world_tasks_table_search);
        model.addObject("full_world_tasks_table_pagelen", full_world_tasks_table_pagelen);
        model.addObject("full_world_tasks_table_page", full_world_tasks_table_page);

        model.addObject("full_world_ways_table_order_column", full_world_ways_table_order_column);
        model.addObject("full_world_ways_table_order_type", full_world_ways_table_order_type);
        model.addObject("full_world_ways_table_search", full_world_ways_table_search);
        model.addObject("full_world_ways_table_pagelen", full_world_ways_table_pagelen);
        model.addObject("full_world_ways_table_page", full_world_ways_table_page);

        model.addObject("full_world_tab_name", tab_name);
        model.setViewName("full_world/cover");
        return model;
    }

    @PostMapping("/full_world")
    public JSONDatatable full_world(
            @RequestParam(name = "full_world_filter_id", required = false, defaultValue = "0") Long full_world_filter_id,
            @RequestParam(name = "full_world_filter_start_date", required = false) String full_world_filter_start_date,
            @RequestParam(name = "full_world_filter_end_date", required = false) String full_world_filter_end_date            
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuFLCClaimsListRepository.queryNewClaimsFLCMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, full_world_filter_id, full_world_filter_start_date, full_world_filter_end_date, new Long(1)));

        return result;
    }

    @GetMapping("/get_service_list")
    public JSONDatatable get_service_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="ser_id") Long ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(service_Type_ListRepository.queryTMSSerIDByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, 1L
        ));
        return result;
    }

    @GetMapping("/get_managers_list")
    public JSONDatatable get_managers_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id", required = false) Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);

        if(mode == 0) {
            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
        }
        else{
            result.setData(user_ListRepository.queryUserByID(user_id));
        }

        return result;
    }

    @GetMapping("/get_currency_list")
    public JSONDatatable get_currency_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(currency_Use_ListRepository.queryAllCurrencyListByID());
        return result;
    }

    @GetMapping("/get_aim_type_list")
    public JSONDatatable get_aim_type_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<Claims_Aim_Type_List> claims_Aim_Type_List;

        claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
        result.setData(claims_Aim_Type_List);
        return result;
    }

    @GetMapping("/get_status_list")
    public JSONDatatable get_status_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_status_id", required = false) Long clm_status_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0) {
            result.setData(claims_Status_ListRepository.queryGetStatusListByID(new Long(0)));
        }
        else{
            result.setData(claims_Status_ListRepository.queryGetStatusListByID(clm_status_id));
        }
        return result;
    }

    @GetMapping("/get_tms_client_list")
    public JSONDatatable get_client_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_filter") String cnt_filter
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(tms_Client_ListRepository.queryTMSClientFilteredByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_filter));
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/get_main_detail")
    public JSONDatatable get_main_detail(
            @RequestParam(name="mode") Integer mode,
            @RequestParam(name="clm_id", required = false, defaultValue = "0") Long clm_id
    ){
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(clm_id != null && clm_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0){
                result.setData(detailNewFLCClaimsListRepository.queryNewFLCClaimsDetailListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id
                ));
            }
            else if(mode > 0) {
                result.setData(detailClaimsListRepository.queryClaimsDetailListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
                ));
            }
        }
        return result;
    }

// Add Main
    @PostMapping("/add_main")
    public ModelAndView add_main(
            @RequestParam(name = "clm_num", required = false) String clm_num,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "cur_user_id", required = false) Long cur_user_id,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(name = "clm_sum_currency_date", required = false) String clm_sum_currency_date,
            @RequestParam(name = "clm_rate", required = false) String clm_rate,
            @RequestParam(name = "clm_rate_currency_id", required = false) Long clm_rate_currency_id,
            @RequestParam(name = "clm_rate_currency_date", required = false) String clm_rate_currency_date,
            @RequestParam(name = "clm_aim_type_id", required = false) Long clm_aim_type_id,
            @RequestParam(name = "clm_note", required = false) String clm_note,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_num)
                    .setParameter(4, cnt_id)
                    .setParameter(5, ser_id)
                    .setParameter(6, cur_user_id)
                    .setParameter(7, clm_sum)
                    .setParameter(8, clm_rate)
                    .setParameter(9, clm_aim_type_id)
                    .setParameter(10, clm_note)
                    .setParameter(11, clm_sum_currency_id)
                    .setParameter(12, clm_sum_currency_date)
                    .setParameter(13, clm_rate_currency_id)
                    .setParameter(14, clm_rate_currency_date)
                    ;
            AddMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
// Edit Main
    @PostMapping("/edit_main")
    public ModelAndView edit_main(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_num", required = false) String clm_num,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "cur_user_id", required = false) Long cur_user_id,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(name = "clm_sum_currency_date", required = false) String clm_sum_currency_date,
            @RequestParam(name = "clm_rate", required = false) String clm_rate,
            @RequestParam(name = "clm_rate_currency_id", required = false) Long clm_rate_currency_id,
            @RequestParam(name = "clm_rate_currency_date", required = false) String clm_rate_currency_date,
            @RequestParam(name = "clm_aim_type_id", required = false) Long clm_aim_type_id,
            @RequestParam(name = "clm_note", required = false) String clm_note,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
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
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_num)
                    .setParameter(5, cnt_id)
                    .setParameter(6, ser_id)
                    .setParameter(7, cur_user_id)
                    .setParameter(8, clm_sum)
                    .setParameter(9, clm_rate)
                    .setParameter(10, clm_aim_type_id)
                    .setParameter(11, clm_note)
                    .setParameter(12, clm_sum_currency_id)
                    .setParameter(13, clm_sum_currency_date)
                    .setParameter(14, clm_rate_currency_id)
                    .setParameter(15, clm_rate_currency_date)
                    ;
            AddMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
// Del Main
    @PostMapping("/del_main")
    public ModelAndView del_main(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            DelMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
// Clone Main
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
// Check Main
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
// UnCheck Main
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

            result.setData(menuClaimTasksListRepository.queryClaimTasksMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
    }

    @GetMapping("/get_clm_tasks_list")
    public JSONDatatable get_clm_tasks_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_task_id") Long clm_task_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0) {
            result.setData(claims_Tasks_Type_ListRepository.queryGetNewTasksListByClaimID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        else{
            result.setData(claims_Tasks_Type_ListRepository.queryGetEditTasksListByClaimID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_task_id));
        }
        return result;
    }

    @GetMapping("/get_tasks_start_users_list")
    public JSONDatatable get_tasks_start_users_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="start_user_id") Long start_user_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);

        if(mode == 0) {
            result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
        }
        else{
            result.setData(user_ListRepository.queryUserByID(start_user_id));
        }
        return result;
    }

    @GetMapping("/get_tasks_end_users_list")
    public JSONDatatable get_tasks_end_users_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="end_user_id") Long end_user_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);

        if(mode == 0) {
            result.setData(user_ListRepository.queryUserAndSubsAllOrderDetail(user_List.get(0).id));
        }
        else{
            result.setData(user_ListRepository.queryUserAndSubsAllOrderDetail(user_List.get(0).id));
        }
        return result;
    }

    @GetMapping("/get_tasks_detail")
    public JSONDatatable get_tasks_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clmtl_id") Long clmtl_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode != 0) {
            result.setData(detailClaimTasksListRepository.queryClaimTasksDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtl_id));
        }
        return result;
    }

    // Add Task
    @PostMapping("/add_tasks")
    public ModelAndView add_tasks(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_task_id", required = false) Long clm_task_id,
            @RequestParam(name = "start_user_id", required = false) Long start_user_id,
            @RequestParam(name = "end_user_id", required = false, defaultValue = "0") Long end_user_id,
            @RequestParam(name = "clmtl_note", required = false) String clmtl_note,
            @RequestParam(name = "deadline_date", required = false) String deadline_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddTaskQuery = entityManager
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
                    .setParameter(8, deadline_date)
                    ;
            AddTaskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Edit Task
    @PostMapping("/edit_tasks")
    public ModelAndView edit_tasks(
            @RequestParam(name = "clmtl_id", required = false) Long clmtl_id,
            @RequestParam(name = "clm_task_id", required = false) Long clm_task_id,
            @RequestParam(name = "start_user_id", required = false) Long start_user_id,
            @RequestParam(name = "end_user_id", required = false, defaultValue = "0") Long end_user_id,
            @RequestParam(name = "clmtl_note", required = false) String clmtl_note,
            @RequestParam(name = "deadline_date", required = false) String deadline_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditTaskQuery = entityManager
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
                    .setParameter(8, deadline_date)
                    ;
            EditTaskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Del Task
    @PostMapping("/del_tasks")
    public ModelAndView del_tasks(
            @RequestParam(name = "clmtl_id", required = false) Long clmtl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelTaskQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimTask")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmtl_id)
                    ;
            DelTaskQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
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

            result.setData(menuFLGClaimWaysListRepository.queryFLGClaimWaysMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/fill_temp")
    public JSONDatatable fill_temp(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
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
        return null;
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

            result.setData(menuClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
    }

    @GetMapping("/get_loads_detail")
    public JSONDatatable get_loads_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clmll_id") Long clmll_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode != 0) {
            result.setData(detailClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id
            ));
        }
        return result;
    }

    @GetMapping("/get_loads_weights_list")
    public JSONDatatable get_loads_weights_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(weightTypeRepository.queryWeightTypeAll());

        return result;
    }

    //Add Loads
    @PostMapping("/add_loads")
    public ModelAndView add_loads(
            @RequestParam(name="clm_id", required = false) Long clm_id,
            @RequestParam(value="clm_load_name", required = false) String clm_load_name,
            @RequestParam(value="clm_load_weight", required = false) String clm_load_weight,
            @RequestParam(value="clm_load_dims", required = false) String clm_load_dims,
            @RequestParam(value="clm_load_notes", required = false) String clm_load_notes,
            @RequestParam(value="weight_type_id", required = false) Long weight_type_id,
            @RequestParam(value="clmll_space_count", required = false) Long clmll_space_count,
            @RequestParam(value="clmll_load_meters", required = false) String clmll_load_meters,
            @RequestParam(value="clmll_client_order_num", required = false) String clmll_client_order_num
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddClmLoadFLGQuery = entityManager
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
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_load_name)
                    .setParameter(5, clm_load_weight)
                    .setParameter(6, clm_load_dims)
                    .setParameter(7, clm_load_notes)
                    .setParameter(8, weight_type_id)
                    .setParameter(9, clmll_space_count)
                    .setParameter(10, clmll_load_meters)
                    .setParameter(11, clmll_client_order_num)
                    ;
            AddClmLoadFLGQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Edit Loads
    @PostMapping("/edit_loads")
    public ModelAndView edit_loads(
            @RequestParam(name = "clmll_id", required = false) Long clmll_id,
            @RequestParam(value="clm_load_name", required = false) String clm_load_name,
            @RequestParam(value="clm_load_weight", required = false) String clm_load_weight,
            @RequestParam(value="clm_load_dims", required = false) String clm_load_dims,
            @RequestParam(value="clm_load_notes", required = false) String clm_load_notes,
            @RequestParam(value="weight_type_id", required = false) Long weight_type_id,
            @RequestParam(value="clmll_space_count", required = false) Long clmll_space_count,
            @RequestParam(value="clmll_load_meters", required = false) String clmll_load_meters,
            @RequestParam(value="clmll_client_order_num", required = false) String clmll_client_order_num
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditClmLoadFLGQuery = entityManager
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
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmll_id)
                    .setParameter(4, clm_load_name)
                    .setParameter(5, clm_load_weight)
                    .setParameter(6, clm_load_dims)
                    .setParameter(7, clm_load_notes)
                    .setParameter(8, weight_type_id)
                    .setParameter(9, clmll_space_count)
                    .setParameter(10, clmll_load_meters)
                    .setParameter(11, clmll_client_order_num)
                    ;
            EditClmLoadFLGQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Del Loads
    @PostMapping("/del_loads")
    public ModelAndView del_loads(
            @RequestParam(name = "clmll_id", required = false) Long clmll_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelClmLoadFLGQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimLoad")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmll_id)
                    ;
            DelClmLoadFLGQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

// Trans
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

            result.setData(menuFLGClaimTransListRepository.queryFLGClaimTransMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @GetMapping("/get_trans_detail")
    public JSONDatatable get_trans_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clmtrl_id != null && clmtrl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailFLGClaimTransListRepository.queryFLGClaimsDetailTransListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clmtrl_id
            ));
        }
        return result;
    }

    @GetMapping("/get_trans_clients_list")
    public JSONDatatable get_trans_clients_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(transport_listRepository.queryTransportByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));

        return result;
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
    //Add Transport
    @PostMapping("/add_transport")
    public ModelAndView add_transport(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="cntc_id", defaultValue = "0") Long cntc_id,
            @RequestParam(value="cntt_id", defaultValue = "0") Long cntt_id,
            @RequestParam(value="cntd_id", defaultValue = "0") Long cntd_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddTransportQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaimTransport")
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
                    .setParameter(7, cntd_id)
                    ;
            AddTransportQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Edit Transport
    @PostMapping("/edit_transport")
    public ModelAndView edit_transport(
            @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="cntc_id", defaultValue = "0") Long cntc_id,
            @RequestParam(value="cntt_id", defaultValue = "0") Long cntt_id,
            @RequestParam(value="cntd_id", defaultValue = "0") Long cntd_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditTransportQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLGTMSClaimTransport")
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
                    .setParameter(7, cntd_id)
                    ;
            EditTransportQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Delete Transport
    @PostMapping("/del_transport")
    public ModelAndView del_transport(
            @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelTransportQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelFLGTMSClaimTransport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmtrl_id)
                    ;
            DelTransportQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
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

    @GetMapping("/get_costs_detail")
    public JSONDatatable get_costs_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clmcl_id", required = false) Long clmcl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clmcl_id != null && clmcl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailClaimCostsListRepository.queryClaimsDetailCostsListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmcl_id));
        }
        return result;
    }

    @GetMapping("/get_costs_type")
    public JSONDatatable get_costs_type(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clmc_type_id", required = false) Long clmc_type_id
    ) {
        JSONDatatable result = new JSONDatatable();

        if(mode == 0) {
            result.setData(claims_Costs_Type_ListRepository.queryNewCostTypeShortListByID());
        }
        else {
            if (clmc_type_id == 1 || clmc_type_id == 8) {
                result.setData(claims_Costs_Type_ListRepository.queryOneCostTypeListByID(clmc_type_id));
            }
            else{
                result.setData(claims_Costs_Type_ListRepository.queryNewCostTypeShortListByID());
            }
        }
        return result;
    }

    //Add Transport
    @PostMapping("/add_costs")
    public ModelAndView add_costs(
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmc_type_id", required = false) Long clmc_type_id,
            @RequestParam(value="clmcl_sum", required = false) String clmcl_sum,
            @RequestParam(value="currency_id", required = false) Long currency_id,
            @RequestParam(value="currency_date", required = false) String currency_date,
            @RequestParam(value="clmcl_note", required = false) String clmcl_note
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddCostsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimCosts")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clmc_type_id)
                    .setParameter(5, clmcl_sum)
                    .setParameter(6, clmcl_note)
                    .setParameter(7, currency_id)
                    .setParameter(8, currency_date)
                    ;
            AddCostsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Edit Transport
    @PostMapping("/edit_costs")
    public ModelAndView edit_costs(
            @RequestParam(name="clmcl_id") Long clmcl_id,
            @RequestParam(name="clmc_type_id") Long clmc_type_id,
            @RequestParam(name="clmcl_sum") String clmcl_sum,
            @RequestParam(name="currency_id") Long currency_id,
            @RequestParam(name="currency_date") String currency_date,
            @RequestParam(name="clmcl_note") String clmcl_note
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditCostsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimCosts")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmcl_id)
                    .setParameter(4, clmc_type_id)
                    .setParameter(5, clmcl_sum)
                    .setParameter(6, clmcl_note)
                    .setParameter(7, currency_id)
                    .setParameter(8, currency_date)
                    .setParameter(9, new Long(0))
                    ;
            EditCostsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
    //Delete Transport
    @PostMapping("/del_costs")
    public ModelAndView del_costs(
            @RequestParam(name = "clmcl_id", required = false) Long clmcl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelCosts = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimCosts")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmcl_id)
                    ;
            DelCosts.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/fill_all_way")
    public ModelAndView fill_all_way(
            @RequestParam(value="clm_id") Long clm_id,

            @RequestParam(value="full_world_ways_table_order_column", required = false) Long full_world_ways_table_order_column,
            @RequestParam(value="full_world_ways_table_order_type", required = false) String full_world_ways_table_order_type,
            @RequestParam(value="full_world_ways_table_search", required = false) String full_world_ways_table_search,
            @RequestParam(value="full_world_ways_table_pagelen", required = false) Long full_world_ways_table_pagelen,
            @RequestParam(value="full_world_ways_table_page", required = false) Long full_world_ways_table_page,

            @RequestParam(value="full_world_main_table_order_column", required = false) Long full_world_main_table_order_column,
            @RequestParam(value="full_world_main_table_order_type", required = false) String full_world_main_table_order_type,
            @RequestParam(value="full_world_main_table_search", required = false) String full_world_main_table_search,
            @RequestParam(value="full_world_main_table_pagelen", required = false) Long full_world_main_table_pagelen,
            @RequestParam(value="full_world_main_table_page", required = false) Long full_world_main_table_page
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

        mav.addObject("full_world_ways_table_order_column", full_world_ways_table_order_column);
        mav.addObject("full_world_ways_table_order_type",  full_world_ways_table_order_type);
        mav.addObject("full_world_ways_table_search",  full_world_ways_table_search);
        mav.addObject("full_world_ways_table_pagelen",  full_world_ways_table_pagelen);
        mav.addObject("full_world_ways_table_page",  full_world_ways_table_page);

        mav.addObject("full_world_main_table_order_column", full_world_main_table_order_column);
        mav.addObject("full_world_main_table_order_type", full_world_main_table_order_type);
        mav.addObject("full_world_main_table_search", full_world_main_table_search);
        mav.addObject("full_world_main_table_pagelen", full_world_main_table_pagelen);
        mav.addObject("full_world_main_table_page", full_world_main_table_page);

        mav.addObject("full_world_tab_name", "tab-3");

        mav.setViewName("/full_world/cover");
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

    @PostMapping("/get_notes_table")
    public JSONDatatable get_notes_table(
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

            result.setData(menuClaimNotesListRepository.queryClaimNotesMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @GetMapping("/get_notes_type_list")
    public JSONDatatable get_notes_type_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clm_notes_type_id") Long clm_notes_type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0) {
            result.setData(tms_Notes_Types_ListRepository.queryTMSNewNotesListByID(clm_id));
        }
        else{
            result.setData(tms_Notes_Types_ListRepository.queryTMSNotesListByID(clm_notes_type_id));
        }
        return result;
    }

// Add Notes
    @PostMapping("/add_notes")
    public ModelAndView add_notes(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_notes_type_id", required = false) Long clm_notes_type_id,
            @RequestParam(name = "clmnl_text", required = false) String clmnl_text
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try{
            StoredProcedureQuery AddTMSClaimNotesQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimNotes")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_notes_type_id)
                    .setParameter(5, clmnl_text)
                    ;
            AddTMSClaimNotesQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

// Edit Notes
    @PostMapping("/edit_notes")
    public ModelAndView edit_notes(
            @RequestParam(name = "clmnl_id", required = false) Long clmnl_id,
            @RequestParam(name = "clm_notes_type_id", required = false) Long clm_notes_type_id,
            @RequestParam(name = "clmnl_text", required = false) String clmnl_text
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try{
            StoredProcedureQuery EditTMSClaimNotesQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimNotes")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmnl_id)
                    .setParameter(4, clm_notes_type_id)
                    .setParameter(5, clmnl_text)
                    ;
            EditTMSClaimNotesQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

// Del Notes
    @PostMapping("/del_notes")
    public ModelAndView del_notes(
            @RequestParam(name = "clmnl_id", required = false) Long clmnl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try{
            StoredProcedureQuery EditTMSClaimNotesQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimNotes")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmnl_id)
                    ;
            EditTMSClaimNotesQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_reports_table")
    public JSONDatatable get_reports_table(
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

            result.setData(menuClaimReportsListRepository.queryClaimReportsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @GetMapping("/pdf_first")
    public void getFirstPdf(
            @RequestParam(name = "clm_id", required = false) Integer clm_id,
            HttpServletResponse response)
            throws JRException, IOException, SQLException {

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_132_pdf.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("CLM_ID", clm_id);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename="+clm_id+"_132.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
    }

    @GetMapping("/pdf_second")
    public void getSecondPdf(
            @RequestParam(name = "clm_id", required = false) Integer clm_id,
            HttpServletResponse response)
            throws JRException, IOException, SQLException {

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_133_pdf.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("CLM_ID", clm_id);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename="+clm_id+"_133.pdf");

            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }
    }

    @PostMapping("/get_report_params_table")
    public JSONDatatable get_report_params_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "report_id", required = false) Long report_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clm_id > 0 && report_id != null && report_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimReportParamsListRepository.queryClaimReportParamsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, report_id));
        }
        return result;
    }

    @PostMapping("/clear_report_params")
    public JSONDatatable clear_report_params(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "report_id", required = false) Long report_id
    ) {
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery ClearClmWayFLCRepParamsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_ClearTMSClaimRepParam")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, report_id)
                    ;
            ClearClmWayFLCRepParamsQuery.execute();
        }
        return null;
    }

    @PostMapping("/fill_report_params")
    public JSONDatatable fill_report_params(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "report_id", required = false) Long report_id
    ) {
        if(clm_id != null && clm_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery FillClmWayFLCRepParamsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_FillTMSClaimRepParam")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, report_id)
                    ;
            FillClmWayFLCRepParamsQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_report_params")
    public JSONDatatable del_report_params(
            @RequestParam(name = "clm_rep_link_id", required = false) Long clm_rep_link_id
    ) {
        if(clm_rep_link_id != null && clm_rep_link_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelClmWayFLCRepParamsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimRepParam")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_rep_link_id)
                    ;
            DelClmWayFLCRepParamsQuery.execute();
        }
        return null;
    }

    @PostMapping("/edit_report_params")
    public JSONDatatable edit_report_params(
            @RequestParam(name = "clm_rep_link_id", required = false) Long clm_rep_link_id,
            @RequestParam(name = "rep_param_value", required = false) String rep_param_value
    ) {
        if(clm_rep_link_id != null && clm_rep_link_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery EditClmWayFLCRepParamsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimRepParam")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_rep_link_id)
                    .setParameter(4, rep_param_value)
                    ;
            EditClmWayFLCRepParamsQuery.execute();
        }
        return null;
    }

    @PostMapping("/get_pallets_table")
    public JSONDatatable get_pallets_table(
            @RequestParam(name = "clmll_id", required = false) Long clmll_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clmll_id != null && clmll_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimPalletsListRepository.queryClaimPalletsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id));
        }
        return result;
    }

    @GetMapping("/get_pallets_list")
    public JSONDatatable get_pallets_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(palletListRepository.queryPalletTypeListBy());

        return result;
    }

    @PostMapping("/add_load_pallets")
    public ModelAndView add_load_pallets(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clmll_id", required = false) Long clmll_id,
            @RequestParam(name = "clmpl_height", required = false) Long clmpl_height,
            @RequestParam(name = "clmpl_width", required = false) Long clmpl_width,
            @RequestParam(name = "clmpl_lenght", required = false) Long clmpl_lenght,
            @RequestParam(name = "clmpl_count", required = false) Long clmpl_count
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddLoadPalletsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddTMSClaimLoadPallets")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clmll_id)
                    .setParameter(5, clmpl_height)
                    .setParameter(6, clmpl_width)
                    .setParameter(7, clmpl_lenght)
                    .setParameter(8, clmpl_count)
                    ;
            AddLoadPalletsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/clone_load_pallets")
    public ModelAndView clone_load_pallets(
            @RequestParam(name = "clmpl_id", required = false) Long clmpl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery CloneLoadPalletsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_CloneTMSClaimLoadPallets")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmpl_id)
                    ;
            CloneLoadPalletsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_load_pallets")
    public ModelAndView edit_load_pallets(
            @RequestParam(name = "clmpl_id", required = false) Long clmpl_id,
            @RequestParam(name = "clmpl_height", required = false) Long clmpl_height,
            @RequestParam(name = "clmpl_width", required = false) Long clmpl_width,
            @RequestParam(name = "clmpl_lenght", required = false) Long clmpl_lenght,
            @RequestParam(name = "clmpl_count", required = false) Long clmpl_count
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditLoadPalletsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimLoadPallets")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmpl_id)
                    .setParameter(4, clmpl_height)
                    .setParameter(5, clmpl_width)
                    .setParameter(6, clmpl_lenght)
                    .setParameter(7, clmpl_count)
                    ;
            EditLoadPalletsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_load_pallets")
    public ModelAndView del_load_pallets(
            @RequestParam(name = "clmpl_id", required = false) Long clmpl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelLoadPalletsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimLoadPallets")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmpl_id)
                    ;
            DelLoadPalletsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
//Ways
@GetMapping("/get_ways_detail")
public JSONDatatable get_ways_detail(
        @RequestParam(name="clmwl_id", defaultValue = "0") Long clmwl_id,
        @RequestParam(name="mode") Long mode
) {
    JSONDatatable result = new JSONDatatable();
    List<User_List> user_List;
    List<User_Role_List> user_Role_List;

    if(clmwl_id != null && clmwl_id >= 0) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode != 0) {
            result.setData(detailFLGClaimWaysRepository.queryFLGClaimWaysDetailByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, clmwl_id));
        }
    }
    return result;
}

    @GetMapping("/get_country")
    public JSONDatatable get_country(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false) Long country_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0 || mode == 1) {
            result.setData(country_ListRepository.queryAllContryList());
        }
        else{
            result.setData(country_ListRepository.queryCurrentContryList(country_id));
        }
        return result;
    }

    @GetMapping("/get_ways_type_list")
    public JSONDatatable get_ways_type_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_way_id", required = false) Long clm_way_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0 || mode == 1) {
            result.setData(tms_Claims_Ways_Type_ListRepository.queryFLCClientWayTypeAll());
        }
        else{
            result.setData(tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id));
        }
        return result;
    }

    @PostMapping("/add_way")
    public ModelAndView add_way(
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clm_way_id", required = false) Long clm_way_id,
            @RequestParam(value="country_id", required = false) Long country_id,
            @RequestParam(value="clm_way_city", required = false) String clm_way_city,
            @RequestParam(value="clm_way_street", required = false) String clm_way_street,
            @RequestParam(value="clm_way_home", required = false) String clm_way_home,
            @RequestParam(value="clm_way_date", required = false) String clm_way_date,
            @RequestParam(value="clm_way_fact_date", required = false) String clm_way_fact_date,
            @RequestParam(value="clm_way_company", required = false) String clm_way_company,
            @RequestParam(value="clm_zip_code", required = false) String clm_zip_code
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddClmWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaimWays")
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
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, clm_way_id)
                .setParameter(5, country_id)
                .setParameter(6, clm_way_city)
                .setParameter(7, clm_way_street)
                .setParameter(8, clm_way_home)
                .setParameter(9, clm_way_date)
                .setParameter(10, clm_way_fact_date)
                .setParameter(11, clm_way_company)
                .setParameter(12, clm_zip_code)
                ;
        AddClmWayQuery.execute();
        return null;
    }

    @PostMapping("/clone_way")
    public ModelAndView clone_way(
            @RequestParam(value="clmwl_id", required = false) Long clmwl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery CloneClmWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_CloneFLGTMSClaimWays")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmwl_id)
                ;
        CloneClmWayQuery.execute();
        return null;
    }

    @PostMapping("/edit_way")
    public ModelAndView edit_way(
            @RequestParam(value="clmwl_id", required = false) Long clmwl_id,
            @RequestParam(value="clm_way_id", required = false) Long clm_way_id,
            @RequestParam(value="country_id", required = false) Long country_id,
            @RequestParam(value="clm_way_city", required = false) String clm_way_city,
            @RequestParam(value="clm_way_street", required = false) String clm_way_street,
            @RequestParam(value="clm_way_home", required = false) String clm_way_home,
            @RequestParam(value="clm_way_date", required = false) String clm_way_date,
            @RequestParam(value="clm_way_fact_date", required = false) String clm_way_fact_date,
            @RequestParam(value="clm_way_company", required = false) String clm_way_company,
            @RequestParam(value="clm_zip_code", required = false) String clm_zip_code
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClmWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_EditFLGTMSClaimWays")
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
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmwl_id)
                .setParameter(4, clm_way_id)
                .setParameter(5, country_id)
                .setParameter(6, clm_way_city)
                .setParameter(7, clm_way_street)
                .setParameter(8, clm_way_home)
                .setParameter(9, clm_way_date)
                .setParameter(10, clm_way_fact_date)
                .setParameter(11, clm_way_company)
                .setParameter(12, clm_zip_code)
                ;
        EditClmWayQuery.execute();
        return null;
    }

    @PostMapping("/del_way")
    public ModelAndView del_way(
            @RequestParam(value="clmwl_id", required = false) Long clmwl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClmWayQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_DelFLGTMSClaimWays")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmwl_id)
                ;
        DelClmWayQuery.execute();
        return null;
    }
}

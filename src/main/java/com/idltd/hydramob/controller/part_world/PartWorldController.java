package com.idltd.hydramob.controller.part_world;

import com.idltd.hydramob.entity.Claims_Aim_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.list.*;
import com.idltd.hydramob.repository.part_world.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/part_world")
public class PartWorldController {
    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuFLGClaimsListRepository menuFLGClaimsListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository;
    private Claims_Status_ListRepository claims_Status_ListRepository;
    private DetailFLGClaimsListRepository detailFLGClaimsListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;

    private MenuFLGClaimsTimeLogsRepository menuFLGClaimsTimeLogsRepository;

    private MenuFLGClaimTasksListRepository menuFLGClaimTasksListRepository;
    private Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository;
    private DetailFLGClaimTasksListRepository detailFLGClaimTasksListRepository;
    
    private MenuFLGClaimLoadsListRepository menuFLGClaimLoadsListRepository;
    private WeightTypeRepository weightTypeRepository;
    private DetailFLGClaimLoadsListRepository detailFLGClaimLoadsListRepository;
    
    private MenuFLGClaimPalletsListRepository menuFLGClaimPalletsListRepository;
    private PalletListRepository palletListRepository;
    
    private MenuFLGClaimTransListRepository menuFLGClaimTransListRepository;
    private DetailFLGClaimTransListRepository detailFLGClaimTransListRepository;
    private Cars_Type_ListRepository cars_Type_ListRepository;
    private Trailers_Type_ListRepository trailers_Type_ListRepository;
    private Transport_ListRepository transport_listRepository;
    private TMS_Cars_ListRepository tms_Cars_ListRepository;
    private TMS_Trailers_ListRepository tms_Trailers_ListRepository;
    private TMS_Drivers_ListRepository tms_Drivers_ListRepository;

    private MenuFLGClaimTransLinkListRepository menuFLGClaimTransLinkListRepository;

    private MenuFLGClaimTransWaysListRepository menuFLGClaimTransWaysListRepository;
    private TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository;

    private MenuFLGClaimCostsGroupsListRepository menuFLGClaimCostsGroupsListRepository;

    private MenuFLGClaimCostsListRepository menuFLGClaimCostsListRepository;
    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;
    private DetailFLGClaimCostsListRepository detailFLGClaimCostsListRepository;

    private MenuFLGClaimWHListRepository menuFLGClaimWHListRepository;

    private MenuFLGClaimWHParamListRepository menuFLGClaimWHParamListRepository;
    private WHParamListRepository whParamListRepository;

    private MenuFLGClaimErrorsListRepository menuFLGClaimErrorsListRepository;

    private MenuFLGClaimWaysListRepository menuFLGClaimWaysListRepository;

    private Country_ListRepository country_ListRepository;
    private DetailFLGClaimWaysRepository detailFLGClaimWaysRepository;
    private MenuFLGClaimWaysTabListRepository menuFLGClaimWaysTabListRepository;

    private MenuFLGClaimPointsListRepository menuFLGClaimPointsListRepository;
    public PartWorldController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuFLGClaimsListRepository menuFLGClaimsListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository,
            Claims_Status_ListRepository claims_Status_ListRepository,
            DetailFLGClaimsListRepository detailFLGClaimsListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,

            MenuFLGClaimsTimeLogsRepository menuFLGClaimsTimeLogsRepository,

            MenuFLGClaimTasksListRepository menuFLGClaimTasksListRepository,
            Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository,
            DetailFLGClaimTasksListRepository detailFLGClaimTasksListRepository,

            MenuFLGClaimLoadsListRepository menuFLGClaimLoadsListRepository,
            WeightTypeRepository weightTypeRepository,
            DetailFLGClaimLoadsListRepository detailFLGClaimLoadsListRepository,

            MenuFLGClaimPalletsListRepository menuFLGClaimPalletsListRepository,
            PalletListRepository palletListRepository,

            MenuFLGClaimTransListRepository menuFLGClaimTransListRepository,
            DetailFLGClaimTransListRepository detailFLGClaimTransListRepository,
            Cars_Type_ListRepository cars_Type_ListRepository,
            Trailers_Type_ListRepository trailers_Type_ListRepository,
            Transport_ListRepository transport_listRepository,
            TMS_Cars_ListRepository tms_Cars_ListRepository,
            TMS_Trailers_ListRepository tms_Trailers_ListRepository,
            TMS_Drivers_ListRepository tms_Drivers_ListRepository,

            MenuFLGClaimTransLinkListRepository menuFLGClaimTransLinkListRepository,

            MenuFLGClaimTransWaysListRepository menuFLGClaimTransWaysListRepository,

            MenuFLGClaimCostsGroupsListRepository menuFLGClaimCostsGroupsListRepository,

            MenuFLGClaimCostsListRepository menuFLGClaimCostsListRepository,
            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,
            DetailFLGClaimCostsListRepository detailFLGClaimCostsListRepository,

            MenuFLGClaimWHListRepository menuFLGClaimWHListRepository,

            MenuFLGClaimWHParamListRepository menuFLGClaimWHParamListRepository,
            WHParamListRepository whParamListRepository,

            MenuFLGClaimErrorsListRepository menuFLGClaimErrorsListRepository,

            MenuFLGClaimWaysListRepository menuFLGClaimWaysListRepository,
            TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository,
            Country_ListRepository country_ListRepository,
            DetailFLGClaimWaysRepository detailFLGClaimWaysRepository,
            MenuFLGClaimWaysTabListRepository menuFLGClaimWaysTabListRepository,

            MenuFLGClaimPointsListRepository menuFLGClaimPointsListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuFLGClaimsListRepository = menuFLGClaimsListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.claims_Aim_Type_ListRepository = claims_Aim_Type_ListRepository;
        this.claims_Status_ListRepository = claims_Status_ListRepository;
        this.detailFLGClaimsListRepository =  detailFLGClaimsListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;

        this.menuFLGClaimsTimeLogsRepository = menuFLGClaimsTimeLogsRepository;

        this.menuFLGClaimTasksListRepository = menuFLGClaimTasksListRepository;
        this.claims_Tasks_Type_ListRepository = claims_Tasks_Type_ListRepository;
        this.detailFLGClaimTasksListRepository = detailFLGClaimTasksListRepository;

        this.menuFLGClaimLoadsListRepository = menuFLGClaimLoadsListRepository;
        this.weightTypeRepository = weightTypeRepository;
        this.detailFLGClaimLoadsListRepository = detailFLGClaimLoadsListRepository;

        this.menuFLGClaimPalletsListRepository = menuFLGClaimPalletsListRepository;
        this.palletListRepository = palletListRepository;

        this.menuFLGClaimTransListRepository = menuFLGClaimTransListRepository;
        this.detailFLGClaimTransListRepository = detailFLGClaimTransListRepository;
        this.cars_Type_ListRepository = cars_Type_ListRepository;
        this.trailers_Type_ListRepository = trailers_Type_ListRepository;
        this.transport_listRepository = transport_listRepository;
        this.tms_Cars_ListRepository = tms_Cars_ListRepository;
        this.tms_Trailers_ListRepository = tms_Trailers_ListRepository;
        this.tms_Drivers_ListRepository = tms_Drivers_ListRepository;

        this.menuFLGClaimTransLinkListRepository = menuFLGClaimTransLinkListRepository;

        this.menuFLGClaimTransWaysListRepository = menuFLGClaimTransWaysListRepository;

        this.menuFLGClaimCostsGroupsListRepository = menuFLGClaimCostsGroupsListRepository;

        this.menuFLGClaimCostsListRepository = menuFLGClaimCostsListRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;
        this.detailFLGClaimCostsListRepository = detailFLGClaimCostsListRepository;

        this.menuFLGClaimWHListRepository = menuFLGClaimWHListRepository;
        this.menuFLGClaimWHParamListRepository = menuFLGClaimWHParamListRepository;
        this.whParamListRepository = whParamListRepository;

        this.menuFLGClaimErrorsListRepository = menuFLGClaimErrorsListRepository;

        this.menuFLGClaimWaysListRepository = menuFLGClaimWaysListRepository;
        this.tms_Claims_Ways_Type_ListRepository = tms_Claims_Ways_Type_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.detailFLGClaimWaysRepository = detailFLGClaimWaysRepository;
        this.menuFLGClaimWaysTabListRepository = menuFLGClaimWaysTabListRepository;

        this.menuFLGClaimPointsListRepository = menuFLGClaimPointsListRepository;
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

        result.setData(menuFLGClaimsListRepository.queryFLGClaimsMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, part_world_filter_id, part_world_filter_start_date, part_world_filter_end_date, new Long(4))
        );
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
                user_List.get(0).id, user_Role_List.get(0).role_id, 2L
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
            @RequestParam(name="clm_id", defaultValue = "0") Long clm_id,
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(clm_id != null && clm_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode > 0) {
                result.setData(detailFLGClaimsListRepository.queryClaimsDetailListByUserID(
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
            @RequestParam(name = "clm_aim_type_id", required = false) Long clm_aim_type_id,
            @RequestParam(name = "clm_note", required = false) String clm_note,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "wh_check", required = false) Long wh_check
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_num)
                    .setParameter(4, ser_id)
                    .setParameter(5, cur_user_id)
                    .setParameter(6, clm_sum)
                    .setParameter(7, clm_sum_currency_id)
                    .setParameter(8, clm_sum_currency_date)
                    .setParameter(9, clm_aim_type_id)
                    .setParameter(10, clm_note)
                    .setParameter(11, cnt_id)
                    .setParameter(12, wh_check)
                    ;
            AddMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

// Clone Main
    @PostMapping("/clone_main")
    public ModelAndView clone_main(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery CloneMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_CloneFLGTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            CloneMainQuery.execute();
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
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLGTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_num)
                    .setParameter(5, ser_id)
                    .setParameter(6, cur_user_id)
                    .setParameter(7, clm_sum)
                    .setParameter(8, clm_sum_currency_id)
                    .setParameter(9, clm_sum_currency_date)
                    .setParameter(10, clm_aim_type_id)
                    .setParameter(11, clm_note)
                    .setParameter(12, cnt_id)
                    ;
            EditMainQuery.execute();
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
            @RequestParam(name = "custom_date", required = false) String custom_date,
            @RequestParam(name = "unload_date", required = false) String unload_date,
            @RequestParam(name = "unloaded_date", required = false) String unloaded_date,
            @RequestParam(name = "close_date", required = false) String close_date,
            @RequestParam(name = "wh_start_date", required = false) String wh_start_date,
            @RequestParam(name = "wh_end_date", required = false) String wh_end_date,
            @RequestParam(name = "sec_custom_date", required = false) String sec_custom_date
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_FullCheckFLGTMSClaims")
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
                .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, load_date)
                .setParameter(5, loaded_date)
                .setParameter(6, custom_date)
                .setParameter(7, unload_date)
                .setParameter(8, unloaded_date)
                .setParameter(9, close_date)
                .setParameter(10, wh_start_date)
                .setParameter(11, wh_end_date)
                .setParameter(12, sec_custom_date)
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
                .createStoredProcedureQuery("PKG_TMS.pr_UnCheckFLGTMSClaims")
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

            result.setData(menuFLGClaimsTimeLogsRepository.queryClaimsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

// Add WH Check
    @PostMapping("/add_wh_check")
    public ModelAndView add_wh_check(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddWHCheckQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaimWHTimeLog")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            AddWHCheckQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

// Del WH Check
    @PostMapping("/del_wh_check")
    public ModelAndView del_wh_check(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelWHCheckQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelFLGTMSClaimWHTimeLog")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            DelWHCheckQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
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

            result.setData(menuFLGClaimTasksListRepository.queryClaimTasksMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
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
            result.setData(detailFLGClaimTasksListRepository.queryFLGClaimTasksDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtl_id));
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
// Loads
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

            result.setData(menuFLGClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
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
            result.setData(detailFLGClaimLoadsListRepository.queryClaimLoadsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id));
        }
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
            @RequestParam(value="weight_type_id", required = false) Long weight_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddClmLoadFLGQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaimLoad")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_load_name)
                    .setParameter(5, clm_load_weight)
                    .setParameter(6, clm_load_dims)
                    .setParameter(7, clm_load_notes)
                    .setParameter(8, weight_type_id)
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
            @RequestParam(value="weight_type_id", required = false) Long weight_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditClmLoadFLGQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLGTMSClaimLoad")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clmll_id)
                    .setParameter(4, clm_load_name)
                    .setParameter(5, clm_load_weight)
                    .setParameter(6, clm_load_dims)
                    .setParameter(7, clm_load_notes)
                    .setParameter(8, weight_type_id)
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
                    .createStoredProcedureQuery("PKG_TMS.pr_DelFLGTMSClaimLoad")
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
// Pallets
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

            result.setData(menuFLGClaimPalletsListRepository.queryClaimPalletsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id));
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

            result.setData(detailFLGClaimTransListRepository.queryFLGClaimsDetailTransListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmtrl_id));
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

            result.setData(menuFLGClaimTransLinkListRepository.queryFLGClaimTransMenuLinkListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
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

            result.setData(menuFLGClaimTransWaysListRepository.queryFLGClaimTransWaysMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id,clmtrl_id));
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

    @PostMapping("/get_costs_groups_table")
    public JSONDatatable get_costs_groups_table(
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

            result.setData(menuFLGClaimCostsGroupsListRepository.queryClaimCostsGroupsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/get_costs_table")
    public JSONDatatable get_costs_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clmcgl_id", required = false) Long clmcgl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clmcgl_id != null && clm_id > 0 && clmcgl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuFLGClaimCostsListRepository.queryFLGClaimCostsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clmcgl_id));
        }
        return result;
    }
//Costs Types List
    @GetMapping("/get_costs_types_list")
    public JSONDatatable get_costs_types_list(
            @RequestParam(value="mode", defaultValue = "0") Long mode,
            @RequestParam(value="clmcg_type_id", defaultValue = "0") Long clmcg_type_id,
            @RequestParam(value="clmc_type_id", defaultValue = "0") Long clmc_type_id,
            @RequestParam(value="trans_check_id", defaultValue = "0") Long trans_check_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0) {
            result.setData(claims_Costs_Type_ListRepository.queryNewCostTypeSourceListByTransID(trans_check_id));
        }
        else{
            if(clmc_type_id == 8){
                result.setData(claims_Costs_Type_ListRepository.queryOneCostTypeListByID(clmc_type_id));
            }
            else{
                result.setData(claims_Costs_Type_ListRepository.queryCostTypeAllListByTransID(trans_check_id));
            }
        }
        return result;
    }

    @GetMapping("/get_costs_detail")
    public JSONDatatable get_costs_detail(
            @RequestParam(name = "mode") Long mode,
            @RequestParam(name = "clmcl_id", required = false) Long clmcl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clmcl_id != null && clmcl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailFLGClaimCostsListRepository.queryFLGClaimsDetailCostsListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clmcl_id));
        }
        return result;
    }

    @PostMapping("/add_costs")
    public ModelAndView add_costs(
            @RequestParam(value="clm_id") Long clm_id,
            @RequestParam(value="clmcgl_id") Long clmcgl_id,
            @RequestParam(value="clmc_type_id") Long clmc_type_id,

            @RequestParam(value="clmcl_sum") String clmcl_sum,
            @RequestParam(value="currency_id") Long currency_id,
            @RequestParam(value="currency_date", required = false) String currency_date,

            @RequestParam(value="clmcl_note", required = false) String clmcl_note
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddClmCostsQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_AddTMSFLGClaimCostsNew")
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
                .setParameter(3, clm_id)
                .setParameter(4, clmc_type_id)
                .setParameter(5, clmcl_sum)
                .setParameter(6, clmcl_note)
                .setParameter(7, currency_id)
                .setParameter(8, currency_date)
                .setParameter(9, clmcgl_id)
                ;
        AddClmCostsQuery.execute();
        return null;
    }

    @PostMapping("/edit_costs")
    public ModelAndView edit_costs(
            @RequestParam(value="clmcl_id") Long clmcl_id,
            @RequestParam(value="clmcgl_id") Long clmcgl_id,
            @RequestParam(value="clmc_type_id") Long clmc_type_id,

            @RequestParam(value="clmcl_sum") String clmcl_sum,
            @RequestParam(value="currency_id") Long currency_id,
            @RequestParam(value="currency_date", required = false) String currency_date,

            @RequestParam(value="clmcl_note", required = false) String clmcl_note
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClmCostsQuery = entityManager
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
                .setParameter(9, clmcgl_id)
                ;
        EditClmCostsQuery.execute();
        return null;
    }

    @PostMapping("/del_costs")
    public ModelAndView del_costs(
            @RequestParam(value="clmcl_id", required = false) Long clmcl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClmCostsQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaimCosts")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmcl_id)
                ;
        DelClmCostsQuery.execute();
        return null;
    }

    @PostMapping("/get_wh_table")
    public JSONDatatable get_wh_table(
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

            result.setData(menuFLGClaimWHListRepository.queryFLGClaimsWHMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/edit_wh")
    public ModelAndView edit_wh(
            @RequestParam(value="clmwhl_id", required = false) Long clmwhl_id,
            @RequestParam(value="clmwhl_start_date", required = false) String clmwhl_start_date,
            @RequestParam(value="clmwhl_end_date", required = false) String clmwhl_end_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClmWHQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWH")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmwhl_id)
                .setParameter(4, clmwhl_start_date)
                .setParameter(5, clmwhl_end_date)
                ;
        EditClmWHQuery.execute();
        return null;
    }

    @PostMapping("/get_wh_param_table")
    public JSONDatatable get_wh_param_table(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clmwhl_id", required = false) Long clmwhl_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(clm_id != null && clmwhl_id != null && clm_id > 0 && clmwhl_id > 0) {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuFLGClaimWHParamListRepository.queryFLGClaimsWHParamMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clmwhl_id));
        }
        return result;
    }


    @GetMapping("/get_wh_param_list")
    public JSONDatatable get_wh_param_list(
            @RequestParam(name = "mode") Long mode,
            @RequestParam(value="wh_param_id", defaultValue = "0") Long wh_param_id
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(whParamListRepository.queryWHParamOneByID(wh_param_id));

        return result;
    }

    @PostMapping("/edit_wh_param")
    public ModelAndView edit_wh_param(
            @RequestParam(value="clmwhpl_id", required = false) Long clmwhpl_id,
            @RequestParam(value="clmwhl_currency_id", required = false) Long clmwhl_currency_id,
            @RequestParam(value="clmwhl_wh_param_rate", required = false) String clmwhl_wh_param_rate
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClmWHParamQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_EditTMSClaimWHParam")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clmwhpl_id)
                .setParameter(4, clmwhl_currency_id)
                .setParameter(5, clmwhl_wh_param_rate)
                ;
        EditClmWHParamQuery.execute();
        return null;
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

            result.setData(menuFLGClaimErrorsListRepository.queryFLGClaimErrorsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
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

            result.setData(menuFLGClaimWaysTabListRepository.queryFLGClaimWaysTabMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clm_way_id));
        }
        return result;
    }

    @PostMapping("/get_points_table")
    public JSONDatatable get_points_table(
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

            result.setData(menuFLGClaimPointsListRepository.queryClaimPointsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, clm_id));
        }
        return result;
    }

    @PostMapping("/add_point")
    public ModelAndView add_point(
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clm_point_date", required = false) String clm_point_date,
            @RequestParam(value="clm_point_name", required = false) String clm_point_name
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery AddClmPointQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_AddFLGTMSClaimPoints")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id)
                .setParameter(4, clm_point_date)
                .setParameter(5, clm_point_name)
                ;
        AddClmPointQuery.execute();
        return null;
    }

    @PostMapping("/edit_point")
    public ModelAndView edit_point(
            @RequestParam(value="clm_point_id", required = false) Long clm_point_id,
            @RequestParam(value="clm_point_date", required = false) String clm_point_date,
            @RequestParam(value="clm_point_name", required = false) String clm_point_name
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClmPointQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_EditFLGTMSClaimPoints")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_point_id)
                .setParameter(4, clm_point_date)
                .setParameter(5, clm_point_name)
                ;
        EditClmPointQuery.execute();
        return null;
    }

    @PostMapping("/del_point")
    public ModelAndView del_point(
            @RequestParam(value="clm_point_id", required = false) Long clm_point_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClmPointQuery = entityManager
                .createStoredProcedureQuery("PKG_TMS.pr_DelFLGTMSClaimPoints")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_point_id)
                ;
        DelClmPointQuery.execute();
        return null;
    }

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
            result.setData(tms_Claims_Ways_Type_ListRepository.queryFLGClientWayTypeAll());
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

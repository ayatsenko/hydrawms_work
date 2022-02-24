package com.idltd.hydramob.controller.aero_world;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.aero_world.*;
import com.idltd.hydramob.repository.full_world.DetailClaimCostsListRepository;
import com.idltd.hydramob.repository.list.Currency_Use_ListRepository;
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
@RequestMapping("/aero_world")
public class AeroWorldController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuAeroWorldClaimsListRepository menuAeroWorldClaimsListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;
    private Country_ListRepository country_ListRepository;
    private Transport_ListRepository transport_listRepository;
    private Country_Aeroport_ListRepository country_Aeroport_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;

    private MenuAeroWorldClaimsTimeLogsRepository menuAeroWorldClaimsTimeLogsRepository;

    private MenuAeroWorldClaimCostsGroupsListRepository menuAeroWorldClaimCostsGroupsListRepository;

    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;
    private DetailClaimCostsListRepository detailClaimCostsListRepository;
    private MenuAeroWorldClaimsCostsListRepository menuAeroWorldClaimsCostsListRepository;

    private DetailAeroWorldClaimsListRepository detailAeroWorldClaimsListRepository;

    public AeroWorldController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuAeroWorldClaimsListRepository menuAeroWorldClaimsListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,
            Country_ListRepository country_ListRepository,
            Transport_ListRepository transport_listRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            Country_Aeroport_ListRepository country_Aeroport_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,

            MenuAeroWorldClaimsTimeLogsRepository menuAeroWorldClaimsTimeLogsRepository,

            MenuAeroWorldClaimCostsGroupsListRepository menuAeroWorldClaimCostsGroupsListRepository,

            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,
            DetailClaimCostsListRepository detailClaimCostsListRepository,
            MenuAeroWorldClaimsCostsListRepository menuAeroWorldClaimsCostsListRepository,

            DetailAeroWorldClaimsListRepository detailAeroWorldClaimsListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuAeroWorldClaimsListRepository = menuAeroWorldClaimsListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.transport_listRepository = transport_listRepository;
        this.country_Aeroport_ListRepository = country_Aeroport_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;

        this.menuAeroWorldClaimsTimeLogsRepository = menuAeroWorldClaimsTimeLogsRepository;

        this.menuAeroWorldClaimCostsGroupsListRepository = menuAeroWorldClaimCostsGroupsListRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;
        this.detailClaimCostsListRepository = detailClaimCostsListRepository;
        this.menuAeroWorldClaimsCostsListRepository = menuAeroWorldClaimsCostsListRepository;

        this.detailAeroWorldClaimsListRepository = detailAeroWorldClaimsListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id,

                              @RequestParam(name = "aero_world_filter_id", required = false, defaultValue = "0") Long aero_world_filter_id,
                              @RequestParam(name = "aero_world_filter_start_date", required = false) String aero_world_filter_start_date,
                              @RequestParam(name = "aero_world_filter_end_date", required = false) String aero_world_filter_end_date,                              
                              
                              @RequestParam(name = "aero_world_table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "aero_world_table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "aero_world_table_search", required = false) String table_search,
                              @RequestParam(name = "aero_world_table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "aero_world_table_page", required = false) Long table_page,

                              @RequestParam(name = "aero_world_tab_name", required = false) String tab_name
    ){
        model.addObject("clm_id", clm_id);

        model.addObject("aero_world_table_order_column", table_order_column);
        model.addObject("aero_world_table_order_type", table_order_type);
        model.addObject("aero_world_table_search", table_search);
        model.addObject("aero_world_table_pagelen", table_pagelen);
        model.addObject("aero_world_table_page", table_page);

        model.addObject("aero_world_filter_id", aero_world_filter_id);
        model.addObject("aero_world_filter_start_date", aero_world_filter_start_date);
        model.addObject("aero_world_filter_end_date", aero_world_filter_end_date);        
        
        model.addObject("aero_world_tab_name", tab_name);
        model.setViewName("aero_world/cover");
        return model;
    }

    @PostMapping("/aero_world_main")
    public JSONDatatable aero_world_main(
            @RequestParam(name = "aero_world_filter_id", required = false, defaultValue = "0") Long aero_world_filter_id,
            @RequestParam(name = "aero_world_filter_start_date", required = false) String aero_world_filter_start_date,
            @RequestParam(name = "aero_world_filter_end_date", required = false) String aero_world_filter_end_date            
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuAeroWorldClaimsListRepository.queryClaimsAeroWorldMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, aero_world_filter_id, aero_world_filter_start_date, aero_world_filter_end_date, new Long(9))
        );

        return result;
    }

    @GetMapping("/aero_world_main_detail")
    public JSONDatatable aero_world_main_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="clm_id", required = false) Long clm_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode == 1) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailAeroWorldClaimsListRepository.queryDetailClaimsAeroWorldListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
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
                user_List.get(0).id, user_Role_List.get(0).role_id, 5L
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

        result.setData(tms_Client_ListRepository.queryTMSClientFilteredByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, cnt_filter
        ));
        return result;
    }

    @GetMapping("/get_tms_shipper_list")
    public JSONDatatable get_tms_shipper_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(transport_listRepository.queryShipperByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

    @GetMapping("/get_tms_consignee_list")
    public JSONDatatable get_tms_consignee_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(transport_listRepository.queryConsigneeByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

     @GetMapping("/get_country")
    public JSONDatatable get_country(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        result.setData(country_ListRepository.queryAllContryList());
        return result;
    }

    @GetMapping("/get_country_aeroport_list")
    public JSONDatatable get_country_aeroport_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", defaultValue = "0") Long country_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(country_Aeroport_ListRepository.queryAllContryAeroportList(
                country_id
        ));
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

        result.setData(transport_listRepository.queryAirTransportByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));

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

    @PersistenceContext
    private EntityManager entityManager;

    // Add Main
    @PostMapping("/add_main")
    public ModelAndView add_main(
                    @RequestParam(name = "clm_num", required = false) String clm_num,
                    @RequestParam(name = "clm_client_num", required = false) String clm_client_num,
                    @RequestParam(name = "latitude", required = false) String latitude,
                    @RequestParam(name = "ser_id", required = false) Long ser_id,
                    @RequestParam(name = "trans_cnt_id", required = false) Long trans_cnt_id,
                    @RequestParam(name = "trans_cnt_name_new", required = false) String trans_cnt_name_new,
                    @RequestParam(name = "trans_cnt_resident_new", required = false, defaultValue = "0") Long trans_cnt_resident_new,
                    @RequestParam(name = "trans_cnt_identifycode_new", required = false) String trans_cnt_identifycode_new,
                    @RequestParam(name = "cnt_id", required = false) Long cnt_id,
                    @RequestParam(name = "start_country_id", required = false) Long start_country_id,
                    @RequestParam(name = "start_aeroport_id", required = false) Long start_aeroport_id,
                    @RequestParam(name = "start_aeroport_new_name", required = false) String start_aeroport_new_name,
                    @RequestParam(name = "start_aeroport_new_code", required = false) String start_aeroport_new_code,
                    @RequestParam(name = "end_country_id", required = false) Long end_country_id,
                    @RequestParam(name = "end_aeroport_id", required = false) Long end_aeroport_id,
                    @RequestParam(name = "end_aeroport_new_name", required = false) String end_aeroport_new_name,
                    @RequestParam(name = "end_aeroport_new_code", required = false) String end_aeroport_new_code,
                    @RequestParam(name = "mawb", required = false) String mawb,
                    @RequestParam(name = "hawb", required = false) String hawb,
                    @RequestParam(name = "shipper_id", required = false) Long shipper_id,
                    @RequestParam(name = "shipper_new_name", required = false) String shipper_new_name,
                    @RequestParam(name = "consignee_id", required = false) Long consignee_id,
                    @RequestParam(name = "consignee_new_name", required = false) String consignee_new_name,
                    @RequestParam(name = "package_count", required = false) String package_count,
                    @RequestParam(name = "gross_weight", required = false) String gross_weight,
                    @RequestParam(name = "chargeble_weight", required = false) String chargeble_weight,
                    @RequestParam(name = "clm_sum", required = false) String clm_sum,
                    @RequestParam(name = "clm_sum_currency_id", required = false, defaultValue = "0") Long clm_sum_currency_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFATMSClaim")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_num)
                    .setParameter(4, clm_client_num)
                    .setParameter(5, latitude)
                    .setParameter(6, ser_id)
                    .setParameter(7, trans_cnt_id)
                    .setParameter(8, trans_cnt_name_new)
                    .setParameter(9, trans_cnt_resident_new)
                    .setParameter(10, trans_cnt_identifycode_new)
                    .setParameter(11, cnt_id)
                    .setParameter(12, start_country_id)
                    .setParameter(13, start_aeroport_id)
                    .setParameter(14, start_aeroport_new_name)
                    .setParameter(15, start_aeroport_new_code)
                    .setParameter(16, end_country_id)
                    .setParameter(17, end_aeroport_id)
                    .setParameter(18, end_aeroport_new_name)
                    .setParameter(19, end_aeroport_new_code)
                    .setParameter(20, mawb)
                    .setParameter(21, hawb)
                    .setParameter(22, shipper_id)
                    .setParameter(23, shipper_new_name)
                    .setParameter(24, consignee_id)
                    .setParameter(25, consignee_new_name)
                    .setParameter(26, package_count)
                    .setParameter(27, gross_weight)
                    .setParameter(28, chargeble_weight)
                    .setParameter(29, clm_sum)
                    .setParameter(30, clm_sum_currency_id)
                    ;
            AddAddressTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Add Main
    @PostMapping("/edit_main")
    public ModelAndView edit_main(
            @RequestParam(name = "clm_id") Long clm_id,
            @RequestParam(name = "clm_num", required = false) String clm_num,
            @RequestParam(name = "clm_client_num", required = false) String clm_client_num,
            @RequestParam(name = "latitude", required = false) String latitude,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "trans_cnt_id", required = false) Long trans_cnt_id,
            @RequestParam(name = "trans_cnt_name_new", required = false) String trans_cnt_name_new,
            @RequestParam(name = "trans_cnt_resident_new", required = false, defaultValue = "0") Long trans_cnt_resident_new,
            @RequestParam(name = "trans_cnt_identifycode_new", required = false) String trans_cnt_identifycode_new,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "start_country_id", required = false) Long start_country_id,
            @RequestParam(name = "start_aeroport_id", required = false) Long start_aeroport_id,
            @RequestParam(name = "start_aeroport_new_name", required = false) String start_aeroport_new_name,
            @RequestParam(name = "start_aeroport_new_code", required = false) String start_aeroport_new_code,
            @RequestParam(name = "end_country_id", required = false) Long end_country_id,
            @RequestParam(name = "end_aeroport_id", required = false) Long end_aeroport_id,
            @RequestParam(name = "end_aeroport_new_name", required = false) String end_aeroport_new_name,
            @RequestParam(name = "end_aeroport_new_code", required = false) String end_aeroport_new_code,
            @RequestParam(name = "mawb", required = false) String mawb,
            @RequestParam(name = "hawb", required = false) String hawb,
            @RequestParam(name = "shipper_id", required = false) Long shipper_id,
            @RequestParam(name = "shipper_new_name", required = false) String shipper_new_name,
            @RequestParam(name = "consignee_id", required = false) Long consignee_id,
            @RequestParam(name = "consignee_new_name", required = false) String consignee_new_name,
            @RequestParam(name = "package_count", required = false) String package_count,
            @RequestParam(name = "gross_weight", required = false) String gross_weight,
            @RequestParam(name = "chargeble_weight", required = false) String chargeble_weight,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false, defaultValue = "0") Long clm_sum_currency_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFATMSClaim")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(31, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_num)
                    .setParameter(5, clm_client_num)
                    .setParameter(6, latitude)
                    .setParameter(7, ser_id)
                    .setParameter(8, trans_cnt_id)
                    .setParameter(9, trans_cnt_name_new)
                    .setParameter(10, trans_cnt_resident_new)
                    .setParameter(11, trans_cnt_identifycode_new)
                    .setParameter(12, cnt_id)
                    .setParameter(13, start_country_id)
                    .setParameter(14, start_aeroport_id)
                    .setParameter(15, start_aeroport_new_name)
                    .setParameter(16, start_aeroport_new_code)
                    .setParameter(17, end_country_id)
                    .setParameter(18, end_aeroport_id)
                    .setParameter(19, end_aeroport_new_name)
                    .setParameter(20, end_aeroport_new_code)
                    .setParameter(21, mawb)
                    .setParameter(22, hawb)
                    .setParameter(23, shipper_id)
                    .setParameter(24, shipper_new_name)
                    .setParameter(25, consignee_id)
                    .setParameter(26, consignee_new_name)
                    .setParameter(27, package_count)
                    .setParameter(28, gross_weight)
                    .setParameter(29, chargeble_weight)
                    .setParameter(30, clm_sum)
                    .setParameter(31, clm_sum_currency_id)
                    ;
            AddAddressTempQuery.execute();
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
            StoredProcedureQuery DelAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            DelAddressTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    //Time log table
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

            result.setData(menuAeroWorldClaimsTimeLogsRepository.queryFAClaimsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
    }

    // Add Main
    @PostMapping("/main_full_check")
    public ModelAndView main_full_check(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "load_date", required = false) String load_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_CheckTMSFSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, load_date)
                    ;
            DelAddressTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
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
                .createStoredProcedureQuery("PKG_TMS.pr_UnCheckTMSFSClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        MainUnCheckQuery.execute();
        return null;
    }

    //Costs
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

            result.setData(menuAeroWorldClaimCostsGroupsListRepository.queryAeroWorldClaimCostsGroupsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
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

            result.setData(menuAeroWorldClaimsCostsListRepository.queryAeroWorldClaimCostsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id, clmcgl_id
            ));
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

    //Add Transport
    @PostMapping("/add_costs")
    public ModelAndView add_costs(
            @RequestParam(value="clm_id", required = false) Long clm_id,
            @RequestParam(value="clmc_type_id", required = false) Long clmc_type_id,
            @RequestParam(value="clmcl_sum", required = false) String clmcl_sum,
            @RequestParam(value="currency_id", required = false) Long currency_id,
            @RequestParam(value="currency_date", required = false) String currency_date,
            @RequestParam(value="clmcl_note", required = false) String clmcl_note,
            @RequestParam(value="clmcgl_id", required = false) Long clmcgl_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddCostsQuery = entityManager
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
            @RequestParam(name="clmcl_note") String clmcl_note,
            @RequestParam(value="clmcgl_id", required = false) Long clmcgl_id
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
                    .setParameter(9, clmcgl_id)
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
}

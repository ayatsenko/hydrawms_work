package com.idltd.hydramob.controller.sea_ships;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.full_world.DetailClaimCostsListRepository;
import com.idltd.hydramob.repository.list.Currency_Use_ListRepository;
import com.idltd.hydramob.repository.sea_ships.*;
import com.idltd.hydramob.repository.tms_locals.DetailFLDClaimTransListRepository;
import com.idltd.hydramob.repository.tms_locals.MenuFLDClaimCostsGroupsListRepository;
import com.idltd.hydramob.repository.tms_locals.MenuFLDClaimCostsListRepository;
import com.idltd.hydramob.repository.tms_locals.MenuFLDClaimTransListRepository;
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
@RequestMapping("/sea_ships")
public class SeaShipsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuSeaShipsClaimsListRepository menuSeaShipsClaimsListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;
    private TMS_Incoterms_ListRepository tms_Incoterms_ListRepository;
    private TMS_Bulk_Type_ListRepository tms_Bulk_Type_ListRepository;
    private TMS_Sea_Agent_ListRepository tms_Sea_Agent_ListRepository;
    private Country_ListRepository country_ListRepository;
    private Country_Shipyard_ListRepository country_Shipyard_ListRepository;
    private Ship_Operator_ListRepository ship_Operator_ListRepository;
    private Ships_ListRepository ships_ListRepository;
    private Transport_ListRepository transport_listRepository;
    private TMS_Cars_ListRepository tms_Cars_ListRepository;
    private TMS_Trailers_ListRepository tms_Trailers_ListRepository;
    private TMS_Drivers_ListRepository tms_Drivers_ListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private Containers_Type_ListRepository containers_Type_ListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;
    private MenuSeaShipsClaimsContainersTempRepository menuSeaShipsClaimsContainersTempRepository;

    private MenuSeaShipsClaimsTimeLogsRepository menuSeaShipsClaimsTimeLogsRepository;

    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;
    private DetailClaimCostsListRepository detailClaimCostsListRepository;
    private MenuFLDClaimCostsListRepository menuFLDClaimCostsListRepository;
    private MenuFLDClaimCostsGroupsListRepository menuFLDClaimCostsGroupsListRepository;

    private DetailFLDClaimTransListRepository detailFLDClaimTransListRepository;
    private MenuFLDClaimTransListRepository menuFLDClaimTransListRepository;

    private DetailSeaShipsClaimsListRepository detailSeaShipsClaimsListRepository;

    private MenuSeaShipsClaimTasksListRepository menuSeaShipsClaimTasksListRepository;
    private Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository;
    private DetailSeaShipsClaimTasksListRepository detailSeaShipsClaimTasksListRepository;

    private MenuSeaShipsClaimErrorsListRepository menuSeaShipsClaimErrorsListRepository;
    public SeaShipsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuSeaShipsClaimsListRepository menuSeaShipsClaimsListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,
            TMS_Incoterms_ListRepository tms_Incoterms_ListRepository,
            TMS_Bulk_Type_ListRepository tms_Bulk_Type_ListRepository,
            TMS_Sea_Agent_ListRepository tms_Sea_Agent_ListRepository,
            Country_ListRepository country_ListRepository,
            Country_Shipyard_ListRepository country_Shipyard_ListRepository,
            Ship_Operator_ListRepository ship_Operator_ListRepository,
            Ships_ListRepository ships_ListRepository,
            Transport_ListRepository transport_listRepository,
            TMS_Cars_ListRepository tms_Cars_ListRepository,
            TMS_Trailers_ListRepository tms_Trailers_ListRepository,
            TMS_Drivers_ListRepository tms_Drivers_ListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            Containers_Type_ListRepository containers_Type_ListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            MenuSeaShipsClaimsContainersTempRepository menuSeaShipsClaimsContainersTempRepository,

            MenuSeaShipsClaimsTimeLogsRepository menuSeaShipsClaimsTimeLogsRepository,
            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,
            DetailClaimCostsListRepository detailClaimCostsListRepository,
            MenuFLDClaimCostsListRepository menuFLDClaimCostsListRepository,
            MenuFLDClaimCostsGroupsListRepository menuFLDClaimCostsGroupsListRepository,

            DetailFLDClaimTransListRepository detailFLDClaimTransListRepository,
            MenuFLDClaimTransListRepository menuFLDClaimTransListRepository,

            DetailSeaShipsClaimsListRepository detailSeaShipsClaimsListRepository,

            MenuSeaShipsClaimTasksListRepository menuSeaShipsClaimTasksListRepository,
            Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository,
            DetailSeaShipsClaimTasksListRepository detailSeaShipsClaimTasksListRepository,

            MenuSeaShipsClaimErrorsListRepository menuSeaShipsClaimErrorsListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuSeaShipsClaimsListRepository = menuSeaShipsClaimsListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;
        this.tms_Incoterms_ListRepository = tms_Incoterms_ListRepository;
        this.tms_Bulk_Type_ListRepository = tms_Bulk_Type_ListRepository;
        this.tms_Sea_Agent_ListRepository = tms_Sea_Agent_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.country_Shipyard_ListRepository = country_Shipyard_ListRepository;
        this.ship_Operator_ListRepository = ship_Operator_ListRepository;
        this.ships_ListRepository = ships_ListRepository;
        this.transport_listRepository = transport_listRepository;
        this.tms_Cars_ListRepository = tms_Cars_ListRepository;
        this.tms_Trailers_ListRepository = tms_Trailers_ListRepository;
        this.tms_Drivers_ListRepository = tms_Drivers_ListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.containers_Type_ListRepository = containers_Type_ListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.menuSeaShipsClaimsContainersTempRepository = menuSeaShipsClaimsContainersTempRepository;

        this.menuSeaShipsClaimsTimeLogsRepository = menuSeaShipsClaimsTimeLogsRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;
        this.detailClaimCostsListRepository = detailClaimCostsListRepository;
        this.menuFLDClaimCostsListRepository = menuFLDClaimCostsListRepository;
        this.menuFLDClaimCostsGroupsListRepository = menuFLDClaimCostsGroupsListRepository;
        this.detailFLDClaimTransListRepository = detailFLDClaimTransListRepository;
        this.menuFLDClaimTransListRepository = menuFLDClaimTransListRepository;

        this.detailSeaShipsClaimsListRepository = detailSeaShipsClaimsListRepository;

        this.menuSeaShipsClaimTasksListRepository = menuSeaShipsClaimTasksListRepository;
        this.claims_Tasks_Type_ListRepository = claims_Tasks_Type_ListRepository;
        this.detailSeaShipsClaimTasksListRepository = detailSeaShipsClaimTasksListRepository;

        this.menuSeaShipsClaimErrorsListRepository = menuSeaShipsClaimErrorsListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id,

                              @RequestParam(name = "sea_ships_filter_id", required = false, defaultValue = "0") Long sea_ships_filter_id,
                              @RequestParam(name = "sea_ships_filter_start_date", required = false) String sea_ships_filter_start_date,
                              @RequestParam(name = "sea_ships_filter_end_date", required = false) String sea_ships_filter_end_date,                              
                              
                              @RequestParam(name = "sea_ships_table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "sea_ships_table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "sea_ships_table_search", required = false) String table_search,
                              @RequestParam(name = "sea_ships_table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "sea_ships_table_page", required = false) Long table_page,

                              @RequestParam(name = "sea_ships_tab_name", required = false) String tab_name
    ){
        model.addObject("clm_id", clm_id);

        model.addObject("sea_ships_table_order_column", table_order_column);
        model.addObject("sea_ships_table_order_type", table_order_type);
        model.addObject("sea_ships_table_search", table_search);
        model.addObject("sea_ships_table_pagelen", table_pagelen);
        model.addObject("sea_ships_table_page", table_page);

        model.addObject("sea_ships_filter_id", sea_ships_filter_id);
        model.addObject("sea_ships_filter_start_date", sea_ships_filter_start_date);
        model.addObject("sea_ships_filter_end_date", sea_ships_filter_end_date);        
        
        model.addObject("sea_ships_tab_name", tab_name);
        model.setViewName("sea_ships/cover");
        return model;
    }

    @PostMapping("/sea_ships_main")
    public JSONDatatable sea_ships_main(
            @RequestParam(name = "sea_ships_filter_id", required = false, defaultValue = "0") Long sea_ships_filter_id,
            @RequestParam(name = "sea_ships_filter_start_date", required = false) String sea_ships_filter_start_date,
            @RequestParam(name = "sea_ships_filter_end_date", required = false) String sea_ships_filter_end_date            
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSeaShipsClaimsListRepository.queryClaimsMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, sea_ships_filter_id, sea_ships_filter_start_date, sea_ships_filter_end_date, new Long(9))
        );

        return result;
    }

    @PostMapping("/sea_ships_containers_temp")
    public JSONDatatable sea_ships_containers_temp(
            @RequestParam(name = "mode", required = false) Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(mode != null && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuSeaShipsClaimsContainersTempRepository.queryClaimsContainersTempByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id)
            );
        }
        return result;
    }

    //Add Containers Temp
    @PostMapping("/add_containers_temp")
    public ModelAndView add_containers_temp(
            @RequestParam(name = "clm_containers_id", required = false) Long clm_containers_id,
            @RequestParam(name = "clm_containers_new_name", required = false) String clm_containers_new_name,
            @RequestParam(name = "clm_containers_num", required = false) String clm_containers_num,
            @RequestParam(name = "clm_containers_weight", required = false) String clm_containers_weight
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_addTMSFSClaimsContainersTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_containers_id)
                    .setParameter(4, clm_containers_new_name)
                    .setParameter(5, clm_containers_num)
                    .setParameter(6, clm_containers_weight)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    //Add Containers Temp
    @PostMapping("/del_containers_temp")
    public ModelAndView del_containers_temp(
            @RequestParam(name = "row_id", required = false) Long row_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_delTMSFSClaimsContainersTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, row_id)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    //Add Containers Temp
    @PostMapping("/all_del_containers_temp")
    public ModelAndView all_del_containers_temp(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_delAllTMSFSClaimsContainersTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    //Add Containers Temp
    @PostMapping("/fill_containers_temp")
    public ModelAndView fill_containers_temp(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditAfterOpsQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_fillTMSFSClaimsContainersTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            EditAfterOpsQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/sea_ships_main_detail")
    public JSONDatatable sea_ships_main_detail(
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

            result.setData(detailSeaShipsClaimsListRepository.queryDetailFSClaimsListByUserID(
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
                user_List.get(0).id, user_Role_List.get(0).role_id, 4L
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
            @RequestParam(value="cnt_filter", required = false) String cnt_filter
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_filter != " " && cnt_filter != null ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(tms_Client_ListRepository.queryTMSClientFilteredByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_filter
            ));
        }
        return result;
    }

    @GetMapping("/get_tms_incoterms_list")
    public JSONDatatable get_tms_incoterms_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(tms_Incoterms_ListRepository.queryTMSIncotersByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

    @GetMapping("/get_tms_bulk_type_list")
    public JSONDatatable get_tms_bulk_type_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(tms_Bulk_Type_ListRepository.queryTMSBulkTypeByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

    @GetMapping("/get_tms_sea_agent_list")
    public JSONDatatable get_tms_sea_agent_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(tms_Sea_Agent_ListRepository.queryTMSSeaAgentByUserID(
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

    @GetMapping("/get_country_shipyard_list")
    public JSONDatatable get_country_shipyard_list(
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

        result.setData(country_Shipyard_ListRepository.queryAllContryShipyardList(
                country_id
        ));
        return result;
    }

    @GetMapping("/get_ship_operator_list")
    public JSONDatatable get_ship_operator_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(ship_Operator_ListRepository.queryShipOperatorByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

    @GetMapping("/get_ships_list")
    public JSONDatatable get_ships_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="ship_operator_id", defaultValue = "0") Long ship_operator_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(ships_ListRepository.queryShipsByUserID(
                ship_operator_id
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

        result.setData(transport_listRepository.queryTransportByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));

        return result;
    }

    @GetMapping("/get_cars")
    public JSONDatatable get_cars(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null &&  cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(tms_Cars_ListRepository.queryTMSClientAllCarListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @GetMapping("/get_trailers")
    public JSONDatatable get_trailers(
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

            result.setData(tms_Trailers_ListRepository.queryTMSClientAllTrailersListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
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

    @GetMapping("/get_currency_list")
    public JSONDatatable get_currency_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(currency_Use_ListRepository.queryAllCurrencyListByID());
        return result;
    }

    @GetMapping("/get_containers_type_list")
    public JSONDatatable get_containers_type_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(containers_Type_ListRepository.queryContainersTypeByUserID());
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    // Add Main
    @PostMapping("/add_main")
    public ModelAndView add_main(
                    @RequestParam(name = "sea_ships_main_modal_clm_num", required = false) String sea_ships_main_modal_clm_num,
                    @RequestParam(name = "sea_ships_main_modal_clm_client_num", required = false) String sea_ships_main_modal_clm_client_num,
                    @RequestParam(name = "sea_ships_main_modal_cnt_id", required = false) Long sea_ships_main_modal_cnt_id,
                    @RequestParam(name = "sea_ships_main_modal_ser_id", required = false) Long sea_ships_main_modal_ser_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_incoterms_id", required = false) Long sea_ships_main_modal_clm_incoterms_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_incoterms_new_name", required = false) String sea_ships_main_modal_clm_incoterms_new_name,
                    @RequestParam(name = "sea_ships_main_modal_clm_bulk_type_id", required = false) Long sea_ships_main_modal_clm_bulk_type_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_bulk_type_new_name", required = false) String sea_ships_main_modal_clm_bulk_type_new_name,
                    @RequestParam(name = "sea_ships_main_modal_clm_sea_agent_id", required = false) Long sea_ships_main_modal_clm_sea_agent_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_sea_agent_new_name", required = false) String sea_ships_main_modal_clm_sea_agent_new_name,
                    @RequestParam(name = "clm_sea_agent_resident_new", required = false) Long clm_sea_agent_resident_new,
                    @RequestParam(name = "clm_sea_agent_identifycode_new", required = false) Long clm_sea_agent_identifycode_new,
                    @RequestParam(name = "sea_ships_main_modal_clm_doc_check", required = false) Long sea_ships_main_modal_clm_doc_check,
                    @RequestParam(name = "sea_ships_main_modal_clm_sea_line_id", required = false) Long sea_ships_main_modal_clm_sea_line_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_sea_line_new_name", required = false) String sea_ships_main_modal_clm_sea_line_new_name,
                    @RequestParam(name = "sea_ships_main_modal_ship_id", required = false) Long sea_ships_main_modal_ship_id,
                    @RequestParam(name = "sea_ships_main_modal_ship_new_name", required = false) String sea_ships_main_modal_ship_new_name,
                    @RequestParam(name = "sea_ships_main_modal_start_country_id", required = false) Long sea_ships_main_modal_start_country_id,
                    @RequestParam(name = "sea_ships_main_modal_start_shipyard_id", required = false) Long sea_ships_main_modal_start_shipyard_id,
                    @RequestParam(name = "sea_ships_main_modal_start_shipyard_new_name", required = false) String sea_ships_main_modal_start_shipyard_new_name,
                    @RequestParam(name = "sea_ships_main_modal_end_country_id", required = false) Long sea_ships_main_modal_end_country_id,
                    @RequestParam(name = "sea_ships_main_modal_end_shipyard_id", required = false) Long sea_ships_main_modal_end_shipyard_id,
                    @RequestParam(name = "sea_ships_main_modal_end_shipyard_new_name", required = false) String sea_ships_main_modal_end_shipyard_new_name,
                    @RequestParam(name = "clm_load_date", required = false) String clm_load_date,
                    @RequestParam(name = "clm_eta_date", required = false) String clm_eta_date,
                    @RequestParam(name = "clm_ata_date", required = false) String clm_ata_date,
                    @RequestParam(name = "sea_ships_main_modal_clm_mhb", required = false) String sea_ships_main_modal_clm_mhb,
                    @RequestParam(name = "sea_ships_main_modal_clm_hbl", required = false) String sea_ships_main_modal_clm_hbl,
                    @RequestParam(name = "clm_shipyard_date", required = false) String clm_shipyard_date,
                    @RequestParam(name = "clm_border_date", required = false) String clm_border_date,

                    @RequestParam(name = "trans_cnt_id", required = false, defaultValue = "0") Long trans_cnt_id,
                    @RequestParam(name = "trans_cnt_name_new", required = false) String trans_cnt_name_new,
                    @RequestParam(name = "cntc_id", required = false, defaultValue = "0") Long cntc_id,
                    @RequestParam(name = "cntc_name_new", required = false) String cntc_name_new,
                    @RequestParam(name = "cntt_id", required = false, defaultValue = "0") Long cntt_id,
                    @RequestParam(name = "cntt_name_new", required = false) String cntt_name_new,
                    @RequestParam(name = "cntd_id", required = false, defaultValue = "0") Long cntd_id,
                    @RequestParam(name = "cntd_name_new", required = false) String cntd_name_new,

                    @RequestParam(name = "sea_ships_main_modal_clm_containers_id", required = false) Long sea_ships_main_modal_clm_containers_id,
                    @RequestParam(name = "sea_ships_main_modal_clm_containers_new_name", required = false) String sea_ships_main_modal_clm_containers_new_name,
                    @RequestParam(name = "sea_ships_main_modal_clm_containers_num", required = false) String sea_ships_main_modal_clm_containers_num,
                    @RequestParam(name = "sea_ships_main_modal_clm_containers_weight", required = false) String sea_ships_main_modal_clm_containers_weight,
                    @RequestParam(name = "clm_sum", required = false) String clm_sum,
                    @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFSTMSClaim")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(31, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(32, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(33, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(34, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(35, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(36, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(37, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(38, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(39, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(40, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(41, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(42, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(43, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(44, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(45, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(46, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, sea_ships_main_modal_clm_num)
                    .setParameter(4, sea_ships_main_modal_clm_client_num)
                    .setParameter(5, sea_ships_main_modal_cnt_id)
                    .setParameter(6, sea_ships_main_modal_ser_id)
                    .setParameter(7, sea_ships_main_modal_clm_incoterms_id)
                    .setParameter(8, sea_ships_main_modal_clm_incoterms_new_name)
                    .setParameter(9, sea_ships_main_modal_clm_bulk_type_id)
                    .setParameter(10, sea_ships_main_modal_clm_bulk_type_new_name)
                    .setParameter(11, sea_ships_main_modal_clm_sea_agent_id)
                    .setParameter(12, sea_ships_main_modal_clm_sea_agent_new_name)
                    .setParameter(13, clm_sea_agent_resident_new)
                    .setParameter(14, clm_sea_agent_identifycode_new)
                    .setParameter(15, sea_ships_main_modal_clm_doc_check)
                    .setParameter(16, sea_ships_main_modal_clm_sea_line_id)
                    .setParameter(17, sea_ships_main_modal_clm_sea_line_new_name)
                    .setParameter(18, sea_ships_main_modal_ship_id)
                    .setParameter(19, sea_ships_main_modal_ship_new_name)
                    .setParameter(20, sea_ships_main_modal_start_country_id)
                    .setParameter(21, sea_ships_main_modal_start_shipyard_id)
                    .setParameter(22, sea_ships_main_modal_start_shipyard_new_name)
                    .setParameter(23, sea_ships_main_modal_end_country_id)
                    .setParameter(24, sea_ships_main_modal_end_shipyard_id)
                    .setParameter(25, sea_ships_main_modal_end_shipyard_new_name)
                    .setParameter(26, clm_load_date)
                    .setParameter(27, clm_eta_date)
                    .setParameter(28, clm_ata_date)
                    .setParameter(29, sea_ships_main_modal_clm_mhb)
                    .setParameter(30, sea_ships_main_modal_clm_hbl)
                    .setParameter(31, clm_shipyard_date)
                    .setParameter(32, clm_border_date)
                    .setParameter(33, trans_cnt_id)
                    .setParameter(34, trans_cnt_name_new)
                    .setParameter(35, cntc_id)
                    .setParameter(36, cntc_name_new)
                    .setParameter(37, cntt_id)
                    .setParameter(38, cntt_name_new)
                    .setParameter(39, cntd_id)
                    .setParameter(40, cntd_name_new)
                    .setParameter(41, sea_ships_main_modal_clm_containers_id)
                    .setParameter(42, sea_ships_main_modal_clm_containers_new_name)
                    .setParameter(43, sea_ships_main_modal_clm_containers_num)
                    .setParameter(44, sea_ships_main_modal_clm_containers_weight)
                    .setParameter(45, clm_sum)
                    .setParameter(46, clm_sum_currency_id)
                    ;
            AddAddressTempQuery.execute();
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
            @RequestParam(name = "sea_ships_main_modal_clm_id") Long sea_ships_main_modal_clm_id,
            @RequestParam(name = "sea_ships_main_modal_clm_num", required = false) String sea_ships_main_modal_clm_num,
            @RequestParam(name = "sea_ships_main_modal_clm_client_num", required = false) String sea_ships_main_modal_clm_client_num,
            @RequestParam(name = "sea_ships_main_modal_cnt_id", required = false) Long sea_ships_main_modal_cnt_id,
            @RequestParam(name = "sea_ships_main_modal_ser_id", required = false) Long sea_ships_main_modal_ser_id,
            @RequestParam(name = "sea_ships_main_modal_clm_incoterms_id", required = false) Long sea_ships_main_modal_clm_incoterms_id,
            @RequestParam(name = "sea_ships_main_modal_clm_incoterms_new_name", required = false) String sea_ships_main_modal_clm_incoterms_new_name,
            @RequestParam(name = "sea_ships_main_modal_clm_bulk_type_id", required = false) Long sea_ships_main_modal_clm_bulk_type_id,
            @RequestParam(name = "sea_ships_main_modal_clm_bulk_type_new_name", required = false) String sea_ships_main_modal_clm_bulk_type_new_name,
            @RequestParam(name = "sea_ships_main_modal_clm_sea_agent_id", required = false) Long sea_ships_main_modal_clm_sea_agent_id,
            @RequestParam(name = "sea_ships_main_modal_clm_sea_agent_new_name", required = false) String sea_ships_main_modal_clm_sea_agent_new_name,
            @RequestParam(name = "clm_sea_agent_resident_new", required = false) Long clm_sea_agent_resident_new,
            @RequestParam(name = "clm_sea_agent_identifycode_new", required = false) Long clm_sea_agent_identifycode_new,
            @RequestParam(name = "sea_ships_main_modal_clm_doc_check", required = false) Long sea_ships_main_modal_clm_doc_check,
            @RequestParam(name = "sea_ships_main_modal_clm_sea_line_id", required = false) Long sea_ships_main_modal_clm_sea_line_id,
            @RequestParam(name = "sea_ships_main_modal_clm_sea_line_new_name", required = false) String sea_ships_main_modal_clm_sea_line_new_name,
            @RequestParam(name = "sea_ships_main_modal_ship_id", required = false) Long sea_ships_main_modal_ship_id,
            @RequestParam(name = "sea_ships_main_modal_ship_new_name", required = false) String sea_ships_main_modal_ship_new_name,
            @RequestParam(name = "sea_ships_main_modal_start_country_id", required = false) Long sea_ships_main_modal_start_country_id,
            @RequestParam(name = "sea_ships_main_modal_start_shipyard_id", required = false) Long sea_ships_main_modal_start_shipyard_id,
            @RequestParam(name = "sea_ships_main_modal_start_shipyard_new_name", required = false) String sea_ships_main_modal_start_shipyard_new_name,
            @RequestParam(name = "sea_ships_main_modal_end_country_id", required = false) Long sea_ships_main_modal_end_country_id,
            @RequestParam(name = "sea_ships_main_modal_end_shipyard_id", required = false) Long sea_ships_main_modal_end_shipyard_id,
            @RequestParam(name = "sea_ships_main_modal_end_shipyard_new_name", required = false) String sea_ships_main_modal_end_shipyard_new_name,
            @RequestParam(name = "clm_load_date", required = false) String clm_load_date,
            @RequestParam(name = "clm_eta_date", required = false) String clm_eta_date,
            @RequestParam(name = "clm_ata_date", required = false) String clm_ata_date,
            @RequestParam(name = "sea_ships_main_modal_clm_mhb", required = false) String sea_ships_main_modal_clm_mhb,
            @RequestParam(name = "sea_ships_main_modal_clm_hbl", required = false) String sea_ships_main_modal_clm_hbl,
            @RequestParam(name = "clm_shipyard_date", required = false) String clm_shipyard_date,
            @RequestParam(name = "clm_border_date", required = false) String clm_border_date,

            @RequestParam(name = "trans_cnt_id", required = false, defaultValue = "0") Long trans_cnt_id,
            @RequestParam(name = "trans_cnt_name_new", required = false) String trans_cnt_name_new,
            @RequestParam(name = "cntc_id", required = false, defaultValue = "0") Long cntc_id,
            @RequestParam(name = "cntc_name_new", required = false) String cntc_name_new,
            @RequestParam(name = "cntt_id", required = false, defaultValue = "0") Long cntt_id,
            @RequestParam(name = "cntt_name_new", required = false) String cntt_name_new,
            @RequestParam(name = "cntd_id", required = false, defaultValue = "0") Long cntd_id,
            @RequestParam(name = "cntd_name_new", required = false) String cntd_name_new,

            @RequestParam(name = "sea_ships_main_modal_clm_containers_id", required = false) Long sea_ships_main_modal_clm_containers_id,
            @RequestParam(name = "sea_ships_main_modal_clm_containers_new_name", required = false) String sea_ships_main_modal_clm_containers_new_name,
            @RequestParam(name = "sea_ships_main_modal_clm_containers_num", required = false) String sea_ships_main_modal_clm_containers_num,
            @RequestParam(name = "sea_ships_main_modal_clm_containers_weight", required = false) String sea_ships_main_modal_clm_containers_weight,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFSTMSClaim")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(31, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(32, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(33, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(34, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(35, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(36, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(37, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(38, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(39, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(40, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(41, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(42, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(43, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(44, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(45, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(46, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(47, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, sea_ships_main_modal_clm_id)
                    .setParameter(4, sea_ships_main_modal_clm_num)
                    .setParameter(5, sea_ships_main_modal_clm_client_num)
                    .setParameter(6, sea_ships_main_modal_cnt_id)
                    .setParameter(7, sea_ships_main_modal_ser_id)
                    .setParameter(8, sea_ships_main_modal_clm_incoterms_id)
                    .setParameter(9, sea_ships_main_modal_clm_incoterms_new_name)
                    .setParameter(10, sea_ships_main_modal_clm_bulk_type_id)
                    .setParameter(11, sea_ships_main_modal_clm_bulk_type_new_name)
                    .setParameter(12, sea_ships_main_modal_clm_sea_agent_id)
                    .setParameter(13, sea_ships_main_modal_clm_sea_agent_new_name)
                    .setParameter(14, clm_sea_agent_resident_new)
                    .setParameter(15, clm_sea_agent_identifycode_new)
                    .setParameter(16, sea_ships_main_modal_clm_doc_check)
                    .setParameter(17, sea_ships_main_modal_clm_sea_line_id)
                    .setParameter(18, sea_ships_main_modal_clm_sea_line_new_name)
                    .setParameter(19, sea_ships_main_modal_ship_id)
                    .setParameter(20, sea_ships_main_modal_ship_new_name)
                    .setParameter(21, sea_ships_main_modal_start_country_id)
                    .setParameter(22, sea_ships_main_modal_start_shipyard_id)
                    .setParameter(23, sea_ships_main_modal_start_shipyard_new_name)
                    .setParameter(24, sea_ships_main_modal_end_country_id)
                    .setParameter(25, sea_ships_main_modal_end_shipyard_id)
                    .setParameter(26, sea_ships_main_modal_end_shipyard_new_name)
                    .setParameter(27, clm_load_date)
                    .setParameter(28, clm_eta_date)
                    .setParameter(29, clm_ata_date)
                    .setParameter(30, sea_ships_main_modal_clm_mhb)
                    .setParameter(31, sea_ships_main_modal_clm_hbl)
                    .setParameter(32, clm_shipyard_date)
                    .setParameter(33, clm_border_date)
                    .setParameter(34, trans_cnt_id)
                    .setParameter(35, trans_cnt_name_new)
                    .setParameter(36, cntc_id)
                    .setParameter(37, cntc_name_new)
                    .setParameter(38, cntt_id)
                    .setParameter(39, cntt_name_new)
                    .setParameter(40, cntd_id)
                    .setParameter(41, cntd_name_new)
                    .setParameter(42, sea_ships_main_modal_clm_containers_id)
                    .setParameter(43, sea_ships_main_modal_clm_containers_new_name)
                    .setParameter(44, sea_ships_main_modal_clm_containers_num)
                    .setParameter(45, sea_ships_main_modal_clm_containers_weight)
                    .setParameter(46, clm_sum)
                    .setParameter(47, clm_sum_currency_id)
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

            result.setData(menuSeaShipsClaimsTimeLogsRepository.queryFLDClaimsMenuListByUserID(
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

            result.setData(menuFLDClaimTransListRepository.queryFLDClaimTransMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
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

            result.setData(detailFLDClaimTransListRepository.queryFLDClaimsDetailTransListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clmtrl_id
            ));
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

            result.setData(menuFLDClaimCostsGroupsListRepository.queryFLDClaimCostsGroupsMenuListByUserID(
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

            result.setData(menuFLDClaimCostsListRepository.queryFLDClaimCostsMenuListByUserID(
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

            result.setData(menuSeaShipsClaimTasksListRepository.queryClaimTasksMenuListByUserID(
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
            result.setData(claims_Tasks_Type_ListRepository.queryGetNewTasksListByClaimID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        else{
            result.setData(claims_Tasks_Type_ListRepository.queryGetEditTasksListByClaimID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_task_id
            ));
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
            result.setData(detailSeaShipsClaimTasksListRepository.queryClaimTasksDetailListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clmtl_id
            ));
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

            result.setData(menuSeaShipsClaimErrorsListRepository.queryClaimErrorsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
    }
}

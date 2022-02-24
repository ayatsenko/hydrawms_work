package com.idltd.hydramob.controller.tms_locals;

import com.idltd.hydramob.entity.Claims_Aim_Type_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.tms_locals.FLDClaimFileTemp;
import com.idltd.hydramob.entity.tms_locals.FLDClaimHusqvarnaTemp;
import com.idltd.hydramob.entity.tms_locals.FLDClaimKercherTemp;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.address_lists.City_ListRepository;
import com.idltd.hydramob.repository.address_lists.House_ListRepository;
import com.idltd.hydramob.repository.address_lists.Street_ListRepository;
import com.idltd.hydramob.repository.full_world.DetailClaimCostsListRepository;
import com.idltd.hydramob.repository.full_world.DetailClaimTasksListRepository;
import com.idltd.hydramob.repository.list.Currency_Use_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Claims_Ways_Type_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Notes_Types_ListRepository;
import com.idltd.hydramob.repository.list.WeightTypeRepository;
import com.idltd.hydramob.repository.tms_locals.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialException;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/tms_locals")
public class TMSLocalsController{

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuClaimMainListRepository menuClaimMainListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository;
    private Claims_Status_ListRepository claims_Status_ListRepository;
    private TMS_Client_ListRepository tms_Client_ListRepository;
    private TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository;
    private FLDClaimWaysListRepository fldClaimWaysListRepository;
    private FLDClaimWaysDetailRepository fldClaimWaysDetailRepository;
    private FLDClaimConsumersRepository fldClaimConsumersRepository;
    private MenuClaimAddressTempRepository menuClaimAddressTempRepository;
    private DetailClaimAddressTempRepository detailClaimAddressTempRepository;
    private MenuClaimLoadsTempRepository menuClaimLoadsTempRepository;
    private WeightTypeRepository weightTypeRepository;
    private DetailClaimLoadsTempRepository detailClaimLoadsTempRepository;
    private MenuClaimTransPointsTempRepository menuClaimTransPointsTempRepository;
    private Transport_ListRepository transport_listRepository;
    private TMS_Cars_ListRepository tms_Cars_ListRepository;
    private TMS_Trailers_ListRepository tms_Trailers_ListRepository;
    private TMS_Drivers_ListRepository tms_Drivers_ListRepository;
    private FLDClaimFileTempRepository fldClaimFileTempRepository;
    private FLDClaimKercherTempRepository fldClaimKercherTempRepository;
    private MenuClaimKercherTempRepository menuClaimKercherTempRepository;
    private FLDClaimHusqvarnaTempRepository fldClaimHusqvarnaTempRepository;

    private MenuFLDClaimsTimeLogsRepository menuFLDClaimsTimeLogsRepository;

    private MenuFLDClaimWaysListRepository menuFLDClaimWaysListRepository;
    private DetailFLDClaimWaysRepository detailFLDClaimWaysRepository;
    private Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository;
    private Country_ListRepository country_ListRepository;

    private MenuFLDClaimLoadsListRepository menuFLDClaimLoadsListRepository;
    private DetailFLDClaimLoadsListRepository detailFLDClaimLoadsListRepository;

    private MenuFLDClaimTransListRepository menuFLDClaimTransListRepository;
    private DetailFLDClaimTransListRepository detailFLDClaimTransListRepository;

    private MenuFLDClaimCostsGroupsListRepository menuFLDClaimCostsGroupsListRepository;
    private MenuFLDClaimCostsListRepository menuFLDClaimCostsListRepository;
    private DetailClaimCostsListRepository detailClaimCostsListRepository;
    private Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository;

    private MenuFLDClaimErrorsListRepository menuFLDClaimErrorsListRepository;

    private MenuFLDClaimTasksListRepository menuFLDClaimTasksListRepository;
    private Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository;

    private MenuFLDClaimNotesListRepository menuFLDClaimNotesListRepository;
    private TMS_Notes_Types_ListRepository tms_Notes_Types_ListRepository;
    private DetailClaimTasksListRepository detailClaimTasksListRepository;

    private Service_Type_ListRepository service_Type_ListRepository;
    private DetailFLDClaimsListRepository detailFLDClaimsListRepository;
    private DetailFLDClaimsMainTransRepository detailFLDClaimsMainTransRepository;

    private City_ListRepository city_ListRepository;
    private Street_ListRepository street_ListRepository;
    private House_ListRepository house_ListRepository;
    public TMSLocalsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuClaimMainListRepository menuClaimMainListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            Claims_Aim_Type_ListRepository claims_Aim_Type_ListRepository,
            Claims_Status_ListRepository claims_Status_ListRepository,
            TMS_Client_ListRepository tms_Client_ListRepository,
            TMS_Claims_Ways_Type_ListRepository tms_Claims_Ways_Type_ListRepository,
            FLDClaimWaysListRepository fldClaimWaysListRepository,
            FLDClaimWaysDetailRepository fldClaimWaysDetailRepository,
            FLDClaimConsumersRepository fldClaimConsumersRepository,
            MenuClaimAddressTempRepository menuClaimAddressTempRepository,
            DetailClaimAddressTempRepository detailClaimAddressTempRepository,
            MenuClaimLoadsTempRepository menuClaimLoadsTempRepository,
            WeightTypeRepository weightTypeRepository,
            DetailClaimLoadsTempRepository detailClaimLoadsTempRepository,
            MenuClaimTransPointsTempRepository menuClaimTransPointsTempRepository,
            Transport_ListRepository transport_listRepository,
            TMS_Cars_ListRepository tms_Cars_ListRepository,
            TMS_Trailers_ListRepository tms_Trailers_ListRepository,
            TMS_Drivers_ListRepository tms_Drivers_ListRepository,
            FLDClaimFileTempRepository fldClaimFileTempRepository,
            FLDClaimKercherTempRepository fldClaimKercherTempRepository,
            MenuClaimKercherTempRepository menuClaimKercherTempRepository,
            FLDClaimHusqvarnaTempRepository fldClaimHusqvarnaTempRepository,

            MenuFLDClaimsTimeLogsRepository menuFLDClaimsTimeLogsRepository,
            MenuFLDClaimWaysListRepository menuFLDClaimWaysListRepository,
            DetailFLDClaimWaysRepository detailFLDClaimWaysRepository,
            Claims_Ways_Type_ListRepository claims_Ways_Type_ListRepository,
            Country_ListRepository country_ListRepository,

            MenuFLDClaimLoadsListRepository menuFLDClaimLoadsListRepository,
            DetailFLDClaimLoadsListRepository detailFLDClaimLoadsListRepository,

            MenuFLDClaimTransListRepository menuFLDClaimTransListRepository,
            DetailFLDClaimTransListRepository detailFLDClaimTransListRepository,

            MenuFLDClaimCostsGroupsListRepository menuFLDClaimCostsGroupsListRepository,            
            MenuFLDClaimCostsListRepository menuFLDClaimCostsListRepository,
            DetailClaimCostsListRepository detailClaimCostsListRepository,
            Claims_Costs_Type_ListRepository claims_Costs_Type_ListRepository,

            MenuFLDClaimErrorsListRepository menuFLDClaimErrorsListRepository,

            MenuFLDClaimTasksListRepository menuFLDClaimTasksListRepository,
            Claims_Tasks_Type_ListRepository claims_Tasks_Type_ListRepository,
            DetailClaimTasksListRepository detailClaimTasksListRepository,

            MenuFLDClaimNotesListRepository menuFLDClaimNotesListRepository,
            TMS_Notes_Types_ListRepository tms_Notes_Types_ListRepository,

            Service_Type_ListRepository service_Type_ListRepository,
            DetailFLDClaimsListRepository detailFLDClaimsListRepository,

            DetailFLDClaimsMainTransRepository detailFLDClaimsMainTransRepository,

            City_ListRepository city_ListRepository,
            Street_ListRepository street_ListRepository,
            House_ListRepository house_ListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuClaimMainListRepository = menuClaimMainListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.claims_Aim_Type_ListRepository = claims_Aim_Type_ListRepository;
        this.claims_Status_ListRepository = claims_Status_ListRepository;
        this.tms_Client_ListRepository = tms_Client_ListRepository;
        this.tms_Claims_Ways_Type_ListRepository = tms_Claims_Ways_Type_ListRepository;
        this.fldClaimWaysListRepository = fldClaimWaysListRepository;
        this.fldClaimWaysDetailRepository = fldClaimWaysDetailRepository;
        this.fldClaimConsumersRepository = fldClaimConsumersRepository;
        this.menuClaimAddressTempRepository = menuClaimAddressTempRepository;
        this.detailClaimAddressTempRepository = detailClaimAddressTempRepository;
        this.menuClaimLoadsTempRepository = menuClaimLoadsTempRepository;
        this.weightTypeRepository = weightTypeRepository;
        this.detailClaimLoadsTempRepository = detailClaimLoadsTempRepository;
        this.menuClaimTransPointsTempRepository = menuClaimTransPointsTempRepository;
        this.transport_listRepository = transport_listRepository;
        this.tms_Cars_ListRepository = tms_Cars_ListRepository;
        this.tms_Trailers_ListRepository = tms_Trailers_ListRepository;
        this.tms_Drivers_ListRepository = tms_Drivers_ListRepository;
        this.fldClaimFileTempRepository = fldClaimFileTempRepository;
        this.fldClaimKercherTempRepository = fldClaimKercherTempRepository;
        this.menuClaimKercherTempRepository = menuClaimKercherTempRepository;
        this.fldClaimHusqvarnaTempRepository = fldClaimHusqvarnaTempRepository;

        this.menuFLDClaimsTimeLogsRepository = menuFLDClaimsTimeLogsRepository;
        this.menuFLDClaimWaysListRepository = menuFLDClaimWaysListRepository;
        this.detailFLDClaimWaysRepository = detailFLDClaimWaysRepository;
        this.claims_Ways_Type_ListRepository = claims_Ways_Type_ListRepository;
        this.country_ListRepository = country_ListRepository;

        this.menuFLDClaimLoadsListRepository = menuFLDClaimLoadsListRepository;
        this.detailFLDClaimLoadsListRepository = detailFLDClaimLoadsListRepository;

        this.menuFLDClaimTransListRepository = menuFLDClaimTransListRepository;
        this.detailFLDClaimTransListRepository = detailFLDClaimTransListRepository;

        this.menuFLDClaimCostsGroupsListRepository = menuFLDClaimCostsGroupsListRepository;
        this.menuFLDClaimCostsListRepository = menuFLDClaimCostsListRepository;
        this.detailClaimCostsListRepository = detailClaimCostsListRepository;
        this.claims_Costs_Type_ListRepository = claims_Costs_Type_ListRepository;

        this.menuFLDClaimErrorsListRepository = menuFLDClaimErrorsListRepository;

        this.menuFLDClaimTasksListRepository = menuFLDClaimTasksListRepository;
        this.claims_Tasks_Type_ListRepository = claims_Tasks_Type_ListRepository;
        this.detailClaimTasksListRepository = detailClaimTasksListRepository;

        this.menuFLDClaimNotesListRepository = menuFLDClaimNotesListRepository;
        this.tms_Notes_Types_ListRepository = tms_Notes_Types_ListRepository;

        this.service_Type_ListRepository = service_Type_ListRepository;
        this.detailFLDClaimsListRepository = detailFLDClaimsListRepository;

        this.detailFLDClaimsMainTransRepository = detailFLDClaimsMainTransRepository;

        this.city_ListRepository = city_ListRepository;
        this.street_ListRepository = street_ListRepository;
        this.house_ListRepository = house_ListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "clm_id", required = false) Long clm_id,

                              @RequestParam(name = "tms_locals_filter_id", required = false, defaultValue = "0") Long tms_locals_filter_id,
                              @RequestParam(name = "tms_locals_filter_start_date", required = false) String tms_locals_filter_start_date,
                              @RequestParam(name = "tms_locals_filter_end_date", required = false) String tms_locals_filter_end_date,

                              @RequestParam(name = "tms_locals_main_table_order_column", required = false) Long tms_locals_main_table_order_column,
                              @RequestParam(name = "tms_locals_main_table_order_type", required = false) String tms_locals_main_table_order_type,
                              @RequestParam(name = "tms_locals_main_table_search", required = false) String tms_locals_main_table_search,
                              @RequestParam(name = "tms_locals_main_table_pagelen", required = false) Long tms_locals_main_table_pagelen,
                              @RequestParam(name = "tms_locals_main_table_page", required = false) Long tms_locals_main_table_page,

                              @RequestParam(name = "tms_locals_tab_name", required = false) String tab_name,
                              @RequestParam(name = "clmtl_id", required = false) Long clmtl_id,
                              @RequestParam(name = "clmtrl_id", required = false) Long clmtrl_id
    ){
        model.addObject("clm_id", clm_id);

        model.addObject("tms_locals_main_table_order_column", tms_locals_main_table_order_column);
        model.addObject("tms_locals_main_table_order_type", tms_locals_main_table_order_type);
        model.addObject("tms_locals_main_table_search", tms_locals_main_table_search);
        model.addObject("tms_locals_main_table_pagelen", tms_locals_main_table_pagelen);
        model.addObject("tms_locals_main_table_page", tms_locals_main_table_page);

        model.addObject("tms_locals_filter_id", tms_locals_filter_id);
        model.addObject("tms_locals_filter_start_date", tms_locals_filter_start_date);
        model.addObject("tms_locals_filter_end_date", tms_locals_filter_end_date);

        model.addObject("tms_locals_tab_name", tab_name);

        model.addObject("clmtl_id", clmtl_id);
        model.addObject("clmtrl_id", clmtrl_id);

        model.setViewName("tms_locals/cover");
        return model;
    }

    @PostMapping("/tms_locals_main")
    public JSONDatatable tms_locals_main(
            @RequestParam(name = "tms_locals_filter_id", required = false, defaultValue = "0") Long tms_locals_filter_id,
            @RequestParam(name = "tms_locals_filter_start_date", required = false) String tms_locals_filter_start_date,
            @RequestParam(name = "tms_locals_filter_end_date", required = false) String tms_locals_filter_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClaimMainListRepository.queryClaimFLDMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, tms_locals_filter_id, tms_locals_filter_start_date, tms_locals_filter_end_date, new Long(1)
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
        if(mode >= 0) {
            result.setData(currency_Use_ListRepository.queryAllCurrencyListByID());
        }
        return result;
    }

    @GetMapping("/get_aim_type_list")
    public JSONDatatable get_aim_type_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<Claims_Aim_Type_List> claims_Aim_Type_List;

        if(mode >= 0) {
            claims_Aim_Type_List = (List<Claims_Aim_Type_List>) claims_Aim_Type_ListRepository.findAll();
            result.setData(claims_Aim_Type_List);
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
            result.setData(tms_Claims_Ways_Type_ListRepository.queryFLDClientWayTypeAll());
        }
        else{
            result.setData(tms_Claims_Ways_Type_ListRepository.queryClientWayTypeByID(clm_way_id));
        }
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

        if(mode >= 0) {
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
                user_List.get(0).id, user_Role_List.get(0).role_id, 3L
        ));
        return result;
    }

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
                result.setData(detailFLDClaimsListRepository.queryFLDlaimsDetailListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
                ));
            }
        }
        return result;
    }

    @GetMapping("/get_main_trans_detail")
    public JSONDatatable get_main_trans_detail(
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
                result.setData(detailFLDClaimsMainTransRepository.queryFLDlaimsMainTransDetailByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
                ));
            }
        }
        return result;
    }

    @GetMapping("/get_fld_way_list")
    public JSONDatatable get_fld_way_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="clm_way_id") Long clm_way_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null && cnt_id != null && clm_way_id != null && cnt_id > 0 && clm_way_id > 0 && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(fldClaimWaysListRepository.queryFLDClaimWayListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, clm_way_id));
        }
        return result;
    }

    @GetMapping("/get_fld_way_details")
    public JSONDatatable get_fld_way_details(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_way_id", required = false) Long cnt_way_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_way_id != null && cnt_way_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(fldClaimWaysDetailRepository.queryFLDClaimWayDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_way_id));
        }
        return result;
    }

    @GetMapping("/get_fld_consumers_list")
    public JSONDatatable get_fld_consumers_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="clm_way_id") Long clm_way_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null && cnt_id != null && clm_way_id != null && cnt_id > 0 && clm_way_id > 0 && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(fldClaimConsumersRepository.queryFLDClaimConsumersByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, clm_way_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    // Add Main
    @PostMapping("/add_main_temp")
    public ModelAndView add_main_temp(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "clm_way_id", required = false) Long clm_way_id,
            @RequestParam(name = "cnt_way_id", required = false, defaultValue = "0") Long cnt_way_id,
            @RequestParam(name = "cnt_way_area", required = false) String cnt_way_area,
            @RequestParam(name = "cnt_way_region", required = false) String cnt_way_region,
            @RequestParam(name = "cnt_way_city", required = false) String cnt_way_city,
            @RequestParam(name = "cnt_way_street", required = false) String cnt_way_street,
            @RequestParam(name = "cnt_way_home", required = false) String cnt_way_home,
            @RequestParam(name = "cnt_way_cons_id", required = false, defaultValue = "0") Long cnt_way_cons_id,
            @RequestParam(name = "cnt_way_company", required = false) String cnt_way_company,
            @RequestParam(name = "cnt_way_contact_id", required = false, defaultValue = "0") Long cnt_way_contact_id,
            @RequestParam(name = "cnt_way_contact_person", required = false) String cnt_way_contact_person,
            @RequestParam(name = "cnt_way_contact_phone", required = false) String cnt_way_contact_phone,
            @RequestParam(name = "cnt_way_contact_phone2", required = false) String cnt_way_contact_phone2,
            @RequestParam(name = "cnt_way_date", required = false) String cnt_way_date,
            @RequestParam(name = "clm_way_id_temp", required = false, defaultValue = "0") Long clm_way_id_temp,
            @RequestParam(name = "order_id_temp", required = false, defaultValue = "0") Long order_id_temp

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLDTMSClaimTemp")
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
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, clm_way_id)
                    .setParameter(5, cnt_way_id)
                    .setParameter(6, new Long(2))
                    .setParameter(7, cnt_way_area)
                    .setParameter(8, cnt_way_region)
                    .setParameter(9, cnt_way_city)
                    .setParameter(10, cnt_way_street)
                    .setParameter(11, cnt_way_home)
                    .setParameter(12, cnt_way_cons_id)
                    .setParameter(13, cnt_way_company)
                    .setParameter(14, cnt_way_contact_id)
                    .setParameter(15, cnt_way_contact_person)
                    .setParameter(16, cnt_way_contact_phone)
                    .setParameter(17, cnt_way_contact_phone2)
                    .setParameter(18, cnt_way_date)
                    .setParameter(19, clm_way_id_temp)
                    .setParameter(20, order_id_temp)
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
    @PostMapping("/del_main_temp")
    public ModelAndView del_main_temp(
            @RequestParam(name = "clm_way_id", required = false) Long clm_way_id,
            @RequestParam(name = "order_id", required = false) Long order_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelAddressTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelFLDTMSClaimTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_way_id)
                    .setParameter(4, order_id)
                    ;
            DelAddressTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_address_temp_table")
    public JSONDatatable get_address_temp_table(
            @RequestParam(name = "mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(mode != null && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimAddressTempRepository.queryFLDClaimAddressTempByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @GetMapping("/get_address_temp_detail")
    public JSONDatatable get_address_temp_detail(
            @RequestParam(value="clm_way_id", required = false) Long clm_way_id,
            @RequestParam(value="order_id", required = false) Long order_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(clm_way_id != null && order_id != null && clm_way_id > 0 && order_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailClaimAddressTempRepository.queryFLDClaimAddressTempDetailByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_way_id, order_id
            ));
        }
        return result;
    }

    @PostMapping("/get_loads_temp_table")
    public JSONDatatable get_loads_temp_table(
            @RequestParam(name = "mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(mode != null && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimLoadsTempRepository.queryFLDClaimLoadsTempMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id
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

    @GetMapping("/get_loads_temp_detail")
    public JSONDatatable get_loads_temp_detail(
            @RequestParam(value="clm_load_id", required = false) Long clm_load_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(clm_load_id != null && clm_load_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailClaimLoadsTempRepository.queryFLDClaimLoadsTempDetailByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_load_id
            ));
        }
        return result;
    }

    // Add Main Loads Temp
    @PostMapping("/add_main_locals_temp")
    public ModelAndView add_main_locals_temp(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "clm_load_id", required = false, defaultValue = "0") Long clm_load_id,
            @RequestParam(name = "clm_load_name", required = false) String clm_load_name,
            @RequestParam(name = "clm_load_weight", required = false) String clm_load_weight,
            @RequestParam(name = "clm_load_dims", required = false) String clm_load_dims,
            @RequestParam(name = "clm_load_notes", required = false) String clm_load_notes,
            @RequestParam(name = "weight_type_id", required = false) Long weight_type_id,
            @RequestParam(name = "clmll_space_count", required = false) String clmll_space_count,
            @RequestParam(name = "clmll_load_meters", required = false) String clmll_load_meters,
            @RequestParam(name = "clmll_client_order_num", required = false) String clmll_client_order_num
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddLoadsTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLDTMSClaimLoadTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, clm_load_id)
                    .setParameter(5, clm_load_name)
                    .setParameter(6, clm_load_weight)
                    .setParameter(7, clm_load_dims)
                    .setParameter(8, clm_load_notes)
                    .setParameter(9, weight_type_id)
                    .setParameter(10, clmll_space_count)
                    .setParameter(11, clmll_load_meters)
                    .setParameter(12, clmll_client_order_num)
                    ;
            AddLoadsTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Del Main Loads Temp
    @PostMapping("/del_main_locals_temp")
    public ModelAndView del_main_locals_temp(
            @RequestParam(name = "clm_load_id", required = false, defaultValue = "0") Long clm_load_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelLoadsTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelFLDTMSClaimLoadTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_load_id)
                    ;
            DelLoadsTempQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Add Master Main
    @PostMapping("/add_master_main")
    public ModelAndView add_master_main(
            @RequestParam(name = "clm_num", required = false) String clm_num,
            @RequestParam(name = "clm_client_num", required = false) String clm_client_num,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(name = "clm_sum_currency_date_type", required = false) Long clm_sum_currency_date_type,
            @RequestParam(name = "clm_sum_currency_date", required = false) String clm_sum_currency_date,
            @RequestParam(name = "clm_rate", required = false) String clm_rate,
            @RequestParam(name = "clm_rate_currency_id", required = false) Long clm_rate_currency_id,
            @RequestParam(name = "clm_rate_currency_date_type", required = false) Long clm_rate_currency_date_type,
            @RequestParam(name = "clm_rate_currency_date", required = false) String clm_rate_currency_date,
            @RequestParam(name = "clm_aim_type_id", required = false) Long clm_aim_type_id,
            @RequestParam(name = "clm_note", required = false) String clm_note,
            @RequestParam(name = "ser_id", required = false) Long ser_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_FLDAddTMSClaims")
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
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_num)
                    .setParameter(4, clm_client_num)
                    .setParameter(5, cnt_id)
                    .setParameter(6, ser_id)
                    .setParameter(7, user_List.get(0).id)
                    .setParameter(8, clm_sum)
                    .setParameter(9, clm_rate)
                    .setParameter(10, clm_aim_type_id)
                    .setParameter(11, clm_note)
                    .setParameter(12, clm_sum_currency_id)
                    .setParameter(13, clm_sum_currency_date_type)
                    .setParameter(14, clm_sum_currency_date)
                    .setParameter(15, clm_rate_currency_id)
                    .setParameter(16, clm_rate_currency_date_type)
                    .setParameter(17, clm_rate_currency_date)
                    ;
            AddMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Add Master Main
    @PostMapping("/add_main")
    public ModelAndView add_main(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_FLDAddPartTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            AddMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Add Trans Temp
    @PostMapping("/add_trans_temp")
    public ModelAndView add_trans_temp(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(clm_id != null && clm_id > 0 ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            try {
                StoredProcedureQuery DelTransTempQuery = entityManager
                        .createStoredProcedureQuery("PKG_TMS.pr_AddFLDTMSClaimTransTemp")
                        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                        .setParameter(1, user_List.get(0).id)
                        .setParameter(2, user_Role_List.get(0).role_id)
                        .setParameter(3, clm_id);
                DelTransTempQuery.execute();
            } catch (Exception e) {
                System.out.println("Error:> " + e);
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/dell_all_trans_temp")
    public ModelAndView dell_all_trans_temp(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            StoredProcedureQuery DelTransTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_delFLDTMSClaimTransTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            DelTransTempQuery.execute();
        } catch (Exception e) {
            System.out.println("Error:> " + e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_trans_point_temp_table")
    public JSONDatatable get_trans_point_temp_table(
            @RequestParam(name = "mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(mode != null && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimTransPointsTempRepository.queryFLDClaimTransPointsTempMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id
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

    @PostMapping("/add_main_trans")
    public ModelAndView add_main_trans(
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id,
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

        try {
            StoredProcedureQuery DelTransTempQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddFLDTMSClaimTransport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, cntc_id)
                    .setParameter(5, cntt_id)
                    .setParameter(6, cntd_id)
                    ;
            DelTransTempQuery.execute();
        } catch (Exception e) {
            System.out.println("Error:> " + e);
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

    @PostMapping("/add_temp_files")
    public ModelAndView add_temp_files(
            @RequestParam(name = "file_type", required = false) Long file_type,
            @RequestParam("file") MultipartFile file
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            FLDClaimFileTemp temp_doc_file = new FLDClaimFileTemp();

            temp_doc_file.db_user_id = user_List.get(0).id;
            temp_doc_file.db_role_id = user_Role_List.get(0).role_id;
            temp_doc_file.clm_fld_file_contenttype = file.getContentType();

            try (InputStream inputStream = file.getInputStream()) {
                byte[] obj_in = IOUtils.toByteArray(inputStream);
                temp_doc_file.clm_fld_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SerialException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            temp_doc_file = fldClaimFileTempRepository.save(temp_doc_file);
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }

        Long successCount=0L;
        Long errorCount=0L;

        try {
            InputStream excelFile =  new BufferedInputStream(file.getInputStream());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> it = datatypeSheet.iterator();

            //пропускаем 1-ю строку
            if (it.hasNext()) {
                Row row = it.next();
            }

            //обрабатываем следующие
            while (it.hasNext()) {
                Row row = it.next();
                if ( file_type == 1) {
                    processKercher(row);
                }
                else if(file_type == 2){
                    processHusqvarna(row);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean processKercher(Row row){
        FLDClaimKercherTemp kercher_temp = new FLDClaimKercherTemp();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            if(row.getCell(5).toString().equals("Zammler")) {
                kercher_temp.setRow_id((long) row.getRowNum());
                kercher_temp.setDb_user_id(user_List.get(0).id);
                kercher_temp.setDb_role_id(user_Role_List.get(0).role_id);
                kercher_temp.setOrder_date(row.getCell(0).toString());
                kercher_temp.setOrder_number((new BigDecimal(row.getCell(1).getNumericCellValue())).toString());
                kercher_temp.setClient_name(row.getCell(2).toString());
                kercher_temp.setDelivery_address(row.getCell(3).toString());
                kercher_temp.setContact_person(row.getCell(4).toString());
                kercher_temp.setLoad_date(row.getCell(6).toString());
                kercher_temp.setUnload_date(row.getCell(7).toString());
                kercher_temp.setComment_text(row.getCell(8).toString());
                kercher_temp.setTtn_number(row.getCell(9).toString());
                kercher_temp.setCar_number(row.getCell(10).toString());
                kercher_temp.setDriver_name(row.getCell(11).toString());
                kercher_temp.setStatus_name(row.getCell(12).toString());
                kercher_temp.setManager_name(row.getCell(13).toString());
                kercher_temp.setClose_date(row.getCell(14).toString());
                kercher_temp.setDoc_sum(row.getCell(15).toString());

                fldClaimKercherTempRepository.save(kercher_temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean processHusqvarna(Row row) {
        FLDClaimHusqvarnaTemp husqvarna_temp = new FLDClaimHusqvarnaTemp();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            if(row.getCell(6).toString().equals("Заммлер")) {
                husqvarna_temp.setRow_id((long) row.getRowNum());
                husqvarna_temp.setDb_user_id(user_List.get(0).id);
                husqvarna_temp.setDb_role_id(user_Role_List.get(0).role_id);
                /*лист подбора*/
                husqvarna_temp.setDoc_num(row.getCell(0).toString());
                /*заказ*/
                husqvarna_temp.setOrder_num(row.getCell(1).toString());
                /*ВН*/
                husqvarna_temp.setCompany_num(row.getCell(2).toString());
                /*дата входящего заказа*/
                husqvarna_temp.setOrder_date(row.getCell(3).toString());
                /*код клиента*/
                husqvarna_temp.setReciver_tax_num((new BigDecimal(row.getCell(4).getNumericCellValue())).toString());
                /*клиент*/
                husqvarna_temp.setClient_name(row.getCell(5).toString());
                /*перевозчик*/
                husqvarna_temp.setTransport(row.getCell(6).toString());
                /*номер декларации*/
                husqvarna_temp.setTtn_number(row.getCell(7).toString());
                /*номер машины*/
                husqvarna_temp.setCar_num(row.getCell(8).toString());
                /*водитель, имя и телефон*/
                husqvarna_temp.setDriver(row.getCell(9).toString());
                /*время регистрации*/
                husqvarna_temp.setRegistration_time(row.getCell(10).toString());
                /*время отправки*/
                husqvarna_temp.setStart_time(row.getCell(11).toString());
                /*время простоя*/
                husqvarna_temp.setStop_time(row.getCell(12).toString());
                /*дата загрузки запрашиваемая*/
                husqvarna_temp.setLoad_plan_date(row.getCell(13).toString());
                /*дата загрузки фактическая*/
                husqvarna_temp.setLoad_fact_date(row.getCell(14).toString());
                /*дата доставки запрашиваемая*/
                husqvarna_temp.setLoaded_plan_date(row.getCell(15).toString());
                /*дата доставки фактическая*/
                husqvarna_temp.setLoaded_fact_date(row.getCell(16).toString());
                /*кол-во палетт фактическое*/
                husqvarna_temp.setPallet_fact_count(row.getCell(17).toString());
                /*кол-во коробок фактическое*/
                husqvarna_temp.setBox_fact_count(row.getCell(18).toString());
                /*способ упаковки*/
                husqvarna_temp.setPack_type(row.getCell(19).toString());
                /*склад*/
                husqvarna_temp.setWh(row.getCell(20).toString());
                /*кол-во строк*/
                husqvarna_temp.setString_count(row.getCell(21).toString());
                /*статус товара*/
                husqvarna_temp.setGoods_status(row.getCell(22).toString());
                /*объем*/
                husqvarna_temp.setAmmount(row.getCell(23).toString());
                /*вес*/
                husqvarna_temp.setWeight(row.getCell(24).toString());
                /*сумма заказа с НДС*/
                husqvarna_temp.setDoc_sum(row.getCell(25).toString());
                /*город доставки*/
                husqvarna_temp.setLoaded_city(row.getCell(26).toString());
                /*адрес доставки*/
                husqvarna_temp.setLoaded_address(row.getCell(27).toString());
                /*контакное лицо, телефон*/
                husqvarna_temp.setContact(row.getCell(28).toString());
                /*возврат документов перевозчиком*/
                husqvarna_temp.setDoc_return(row.getCell(29).toString());
                /*примечание на склад*/
                husqvarna_temp.setWh_notes(row.getCell(30).toString());
                /*примечание для трансп.ко.*/
                husqvarna_temp.setTrans_notes(row.getCell(31).toString());

                fldClaimHusqvarnaTempRepository.save(husqvarna_temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @PostMapping("/get_kercher_temp_table")
    public JSONDatatable get_kercher_temp_table(
            @RequestParam(name = "mode") Long mode,
            @RequestParam(name = "type_id") Long type_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(mode != null && mode >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClaimKercherTempRepository.queryFLDClaimKercherTempMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, type_id
            ));
        }
        return result;
    }

    // Del Main
    @PostMapping("/del_kercher_main")
    public ModelAndView del_kercher_main(
            @RequestParam(name = "row_id", required = false) Long row_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_delFLDTMSClaimKercherTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, row_id)
                    ;
            DelMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Del Main
    @PostMapping("/del_all_main")
    public ModelAndView del_all_main(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_delAllFLDTMSClaimTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            DelMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    // Del Main
    @PostMapping("/add_all_main")
    public ModelAndView add_all_main(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddHusqMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddClaimsFromHusqvarnaFileTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            AddHusqMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/add_all_kercher_main")
    public ModelAndView add_all_kercher_main(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddHusqMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddClaimsFromKercherFileTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            AddHusqMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main")
    public ModelAndView edit_main(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_num", required = false) String clm_num,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(name = "clm_rate", required = false) String clm_rate,
            @RequestParam(name = "clm_rate_currency_id", required = false) Long clm_rate_currency_id,
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
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaims")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_num)
                    .setParameter(5, clm_sum)
                    .setParameter(6, clm_sum_currency_id)
                    .setParameter(7, clm_rate)
                    .setParameter(8, clm_rate_currency_id)
                    .setParameter(9, clm_aim_type_id)
                    .setParameter(10, clm_note)
                    .setParameter(11, cnt_id)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_data")
    public ModelAndView edit_main_clm_data(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_date", required = false) String clm_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsDate")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_date)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_num")
    public ModelAndView edit_main_clm_num(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_num", required = false) String clm_num
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsNum")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_num)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_way_date")
    public ModelAndView edit_main_clm_way_date(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_way_date", required = false) String clm_way_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsWayDate")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_way_date)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_cnt_id")
    public ModelAndView edit_main_cnt_id(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
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
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsCntID")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, cnt_id)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_client_num")
    public ModelAndView edit_main_clm_client_num(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_client_num", required = false) String clm_client_num
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsClmClientNum")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_client_num)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_sum")
    public ModelAndView edit_main_clm_sum(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_sum", required = false) String clm_sum,
            @RequestParam(name = "clm_sum_currency_id", required = false) Long clm_sum_currency_id,
            @RequestParam(name = "clm_sum_currency_date_type", required = false) Long clm_sum_currency_date_type,
            @RequestParam(name = "clm_sum_currency_date", required = false) String clm_sum_currency_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsClmSum")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_sum)
                    .setParameter(5, clm_sum_currency_id)
                    .setParameter(6, clm_sum_currency_date_type)
                    .setParameter(7, clm_sum_currency_date)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_clm_rate")
    public ModelAndView edit_main_clm_rate(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_rate", required = false) String clm_rate,
            @RequestParam(name = "clm_rate_currency_id", required = false) Long clm_rate_currency_id,
            @RequestParam(name = "clm_rate_currency_date_type", required = false) Long clm_rate_currency_date_type,
            @RequestParam(name = "clm_rate_currency_date", required = false) String clm_rate_currency_date
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsClmRate")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, clm_rate)
                    .setParameter(5, clm_rate_currency_id)
                    .setParameter(6, clm_rate_currency_date_type)
                    .setParameter(7, clm_rate_currency_date)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_trans_cnt_id")
    public ModelAndView edit_main_trans_cnt_id(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "trans_id", required = false) Long trans_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsTransID")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    .setParameter(4, trans_id)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_car_id")
    public ModelAndView edit_main_car_id(
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "trans_id", required = false) Long trans_id,
            @RequestParam(name = "cntc_id", required = false) Long cntc_id,
            @RequestParam(name = "cntt_id", required = false) Long cntt_id,
            @RequestParam(name = "cntd_id", required = false) Long cntd_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsCarID")
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
                    .setParameter(4, trans_id)
                    .setParameter(5, cntc_id)
                    .setParameter(6, cntt_id)
                    .setParameter(7, cntd_id)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_main_loads")
    public ModelAndView edit_main_loads(
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditMainQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimsLoadFromTemp")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, clm_id)
                    ;
            EditMainQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/fill_main_loads_temp")
    public ModelAndView fill_main_loads_temp(
            @RequestParam(name = "mode", required = false) Long mode,
            @RequestParam(name = "clm_id", required = false) Long clm_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode > 0 ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            try {
                StoredProcedureQuery EditMainQuery = entityManager
                        .createStoredProcedureQuery("PKG_TMS.pr_FillFLDTMSClaimsLoadFromTemp")
                        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                        .setParameter(1, user_List.get(0).id)
                        .setParameter(2, user_Role_List.get(0).role_id)
                        .setParameter(3, clm_id);
                EditMainQuery.execute();
            } catch (Exception e) {
                System.out.println("Error:> " + e);
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/fill_main_loads_point_temp")
    public ModelAndView fill_main_loads_point_temp(
            @RequestParam(name = "mode", required = false) Long mode,
            @RequestParam(name = "clm_id", required = false) Long clm_id,
            @RequestParam(name = "clm_way_id", required = false) Long clm_way_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode > 0 ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            try {
                StoredProcedureQuery EditMainQuery = entityManager
                        .createStoredProcedureQuery("PKG_TMS.pr_FillFLDTMSClaimsLoadPointToTemp")
                        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                        .setParameter(1, user_List.get(0).id)
                        .setParameter(2, user_Role_List.get(0).role_id)
                        .setParameter(3, clm_id)
                        .setParameter(4, clm_way_id);
                EditMainQuery.execute();
            } catch (Exception e) {
                System.out.println("Error:> " + e);
                e.printStackTrace();
            }
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

            result.setData(menuFLDClaimsTimeLogsRepository.queryFLDClaimsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
    }

    //Ways Detail
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

            result.setData(menuFLDClaimWaysListRepository.queryFLDClaimWaysMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
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
                result.setData(detailFLDClaimWaysRepository.queryFLDClaimWaysDetailByUserID(
                        user_List.get(0).id,user_Role_List.get(0).role_id, clmwl_id
                ));
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
                .createStoredProcedureQuery("PKG_TMS.pr_AddFLDTMSClaimWays")
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
                .createStoredProcedureQuery("PKG_TMS.pr_CloneFLDTMSClaimWays")
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
                .createStoredProcedureQuery("PKG_TMS.pr_EditFLDTMSClaimWays")
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
                .createStoredProcedureQuery("PKG_TMS.pr_DelFLDTMSClaimWays")
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

    //Loads
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

            result.setData(menuFLDClaimLoadsListRepository.queryFLDClaimLoadsMenuListByUserID(
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
            result.setData(detailFLDClaimLoadsListRepository.queryFLDClaimLoadsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clmll_id
            ));
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

//Errors
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

            result.setData(menuFLDClaimErrorsListRepository.queryFLDClaimErrorsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
        }
        return result;
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
                .createStoredProcedureQuery("PKG_TMS.pr_CheckTMSFLDClaims")
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
                .createStoredProcedureQuery("PKG_TMS.pr_FullFLDCheckTMSClaims")
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
                .createStoredProcedureQuery("PKG_TMS.pr_UnCheckTMSFLDClaims")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, clm_id);
        MainUnCheckQuery.execute();
        return null;
    }

    //Notes
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

            result.setData(menuFLDClaimNotesListRepository.queryFLDClaimNotesMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, clm_id
            ));
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

    //Tasks
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

            result.setData(menuFLDClaimTasksListRepository.queryClaimTasksMenuListByUserID(
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

    @GetMapping("/get_city_list")
    public JSONDatatable get_city_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false, defaultValue = "2") Long country_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode >= 0) {
            result.setData(city_ListRepository.queryAllCityListByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, country_id
            ));
        }
        return result;
    }

    @GetMapping("/get_city_street_list")
    public JSONDatatable get_city_street_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false, defaultValue = "2") Long country_id,
            @RequestParam(value="city_id", required = false) Long city_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode >= 0) {
            result.setData(street_ListRepository.queryAllCityStreetListByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, country_id, city_id
            ));
        }
        return result;
    }

    @GetMapping("/get_city_street_house_list")
    public JSONDatatable get_city_street_house_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false, defaultValue = "2") Long country_id,
            @RequestParam(value="city_id", required = false) Long city_id,
            @RequestParam(value="street_id", required = false) Long street_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode >= 0) {
            result.setData(house_ListRepository.queryAllCityStreetHouseListByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, country_id, city_id, street_id
            ));
        }
        return result;
    }
}
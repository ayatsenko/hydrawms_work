package com.idltd.hydramob.controller.clients;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.clients.*;
import com.idltd.hydramob.entity.directions.Direction;
import com.idltd.hydramob.entity.list.ClientClassList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.clients.*;
import com.idltd.hydramob.repository.list.BanksListRepository;
import com.idltd.hydramob.repository.list.ClientClassListRepository;
import com.idltd.hydramob.repository.list.ContragentSubListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeUtility;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private User_Report_ListRepository user_Report_ListRepository;
    private ClientClassListRepository clientClassListRepository;

    private MenuClientsListRepository menuClientsListRepository;
    private DetailClientsListRepository detailClientsListRepository;

    private MenuClientContactsListRepository menuClientContactsListRepository;
    private DetailClientsContactsListRepository detailClientsContactsListRepository;

    private MenuClientServicesListRepository menuClientServicesListRepository;
    private DetailClientsServicesListRepository detailClientsServicesListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;

    private MenuClientAddressListRepository menuClientAddressListRepository;
    private Address_Type_ListRepository address_Type_ListRepository;
    private DetailClientAddressListRepository detailClientAddressListRepository;

    private MenuClientDocumentsListRepository menuClientDocumentsListRepository;
    private DetailClientDocumentsListRepository detailClientDocumentsListRepository;

    private MenuClientDocFilesListRepository menuClientDocFilesListRepository;
    private DetailClientDocFilesListRepository detailClientDocFilesListRepository;
    private ClientDocFileRepository clientDocFileRepository;

    private MenuClientUsersListRepository menuClientUsersListRepository;
    private ClientUser_ListRepository clientUser_ListRepository;

    private Contragent_Status_ListRepository contragent_Status_ListRepository;
    private OwnerShip_Type_ListRepository ownerShip_Type_ListRepository;
    private Contragent_Source_ListRepository contragent_Source_ListRepository;
    private Country_ListRepository country_ListRepository;
    private ClientLawTypeListRepository clientLawTypeListRepository;

    private MenuClientUsersRolesListRepository menuClientUsersRolesListRepository;

    private MenuClientStatisticsListRepository menuClientStatisticsListRepository;

    private MenuClientSubListRepository menuClientSubListRepository;
    private ContragentSubListRepository contragentSubListRepository;

    private MenuClientReferenceListRepository menuClientReferenceListRepository;
    private BanksListRepository banksListRepository;
    private DetailClientReferenceListRepository detailClientReferenceListRepository;

    private MenuClientsWHListRepository menuClientsWHListRepository;
    private ClientsWHListRepository clientsWHListRepository;

    private MenuClientsWHServicesListRepository menuClientsWHServicesListRepository;
    private ClientsWHServicesTypesListRepository clientsWHServicesTypesListRepository;

    private MenuClientsWHParametersListRepository menuClientsWHParametersListRepository;
    private ClientsWHParametersTypesListRepository clientsWHParametersTypesListRepository;
    public ClientsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            User_Report_ListRepository user_Report_ListRepository,
            ClientClassListRepository clientClassListRepository,

            MenuClientsListRepository menuClientsListRepository,
            DetailClientsListRepository detailClientsListRepository,
            MenuClientContactsListRepository menuClientContactsListRepository,
            DetailClientsContactsListRepository detailClientsContactsListRepository,

            MenuClientServicesListRepository menuClientServicesListRepository,
            DetailClientsServicesListRepository detailClientsServicesListRepository,
            Service_Type_ListRepository service_Type_ListRepository,

            MenuClientAddressListRepository menuClientAddressListRepository,
            Address_Type_ListRepository address_Type_ListRepository,
            DetailClientAddressListRepository detailClientAddressListRepository,

            MenuClientDocumentsListRepository menuClientDocumentsListRepository,
            DetailClientDocumentsListRepository detailClientDocumentsListRepository,

            MenuClientDocFilesListRepository menuClientDocFilesListRepository,
            DetailClientDocFilesListRepository detailClientDocFilesListRepository,
            ClientDocFileRepository clientDocFileRepository,

            MenuClientUsersListRepository menuClientUsersListRepository,
            ClientUser_ListRepository clientUser_ListRepository,

            Contragent_Status_ListRepository contragent_Status_ListRepository,
            OwnerShip_Type_ListRepository ownerShip_Type_ListRepository,
            Contragent_Source_ListRepository contragent_Source_ListRepository,
            Country_ListRepository country_ListRepository,
            ClientLawTypeListRepository clientLawTypeListRepository,

            MenuClientStatisticsListRepository menuClientStatisticsListRepository,

            MenuClientUsersRolesListRepository menuClientUsersRolesListRepository,

            MenuClientSubListRepository menuClientSubListRepository,
            ContragentSubListRepository contragentSubListRepository,

            MenuClientReferenceListRepository menuClientReferenceListRepository,
            BanksListRepository banksListRepository,
            DetailClientReferenceListRepository detailClientReferenceListRepository,

            MenuClientsWHListRepository menuClientsWHListRepository,
            ClientsWHListRepository clientsWHListRepository,

            MenuClientsWHServicesListRepository menuClientsWHServicesListRepository,
            ClientsWHServicesTypesListRepository clientsWHServicesTypesListRepository,

            MenuClientsWHParametersListRepository menuClientsWHParametersListRepository,
            ClientsWHParametersTypesListRepository clientsWHParametersTypesListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.user_Report_ListRepository = user_Report_ListRepository;
        this.clientClassListRepository = clientClassListRepository;

        this.menuClientsListRepository = menuClientsListRepository;
        this.detailClientsListRepository = detailClientsListRepository;

        this.menuClientContactsListRepository = menuClientContactsListRepository;
        this.detailClientsContactsListRepository = detailClientsContactsListRepository;

        this.menuClientServicesListRepository = menuClientServicesListRepository;
        this.detailClientsServicesListRepository = detailClientsServicesListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;

        this.menuClientAddressListRepository = menuClientAddressListRepository;
        this.address_Type_ListRepository = address_Type_ListRepository;
        this.detailClientAddressListRepository = detailClientAddressListRepository;

        this.menuClientDocumentsListRepository = menuClientDocumentsListRepository;
        this.detailClientDocumentsListRepository = detailClientDocumentsListRepository;

        this.menuClientDocFilesListRepository = menuClientDocFilesListRepository;
        this.detailClientDocFilesListRepository = detailClientDocFilesListRepository;
        this.clientDocFileRepository = clientDocFileRepository;

        this.menuClientUsersListRepository = menuClientUsersListRepository;
        this.clientUser_ListRepository = clientUser_ListRepository;

        this.contragent_Status_ListRepository = contragent_Status_ListRepository;
        this.ownerShip_Type_ListRepository = ownerShip_Type_ListRepository;
        this.contragent_Source_ListRepository = contragent_Source_ListRepository;
        this.country_ListRepository = country_ListRepository;
        this.clientLawTypeListRepository = clientLawTypeListRepository;

        this.menuClientStatisticsListRepository = menuClientStatisticsListRepository;

        this.menuClientUsersRolesListRepository = menuClientUsersRolesListRepository;

        this.menuClientSubListRepository = menuClientSubListRepository;
        this.contragentSubListRepository = contragentSubListRepository;

        this.menuClientReferenceListRepository = menuClientReferenceListRepository;
        this.banksListRepository = banksListRepository;
        this.detailClientReferenceListRepository = detailClientReferenceListRepository;

        this.menuClientsWHListRepository = menuClientsWHListRepository;
        this.clientsWHListRepository = clientsWHListRepository;

        this.menuClientsWHServicesListRepository = menuClientsWHServicesListRepository;
        this.clientsWHServicesTypesListRepository = clientsWHServicesTypesListRepository;

        this.menuClientsWHParametersListRepository = menuClientsWHParametersListRepository;
        this.clientsWHParametersTypesListRepository = clientsWHParametersTypesListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                              @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                              @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                              @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id,

                              @RequestParam(value="clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                              @RequestParam(value="clients_list_table_order_type", required = false) String clients_list_table_order_type,
                              @RequestParam(value="clients_list_table_search", required = false) String clients_list_table_search,
                              @RequestParam(value="clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                              @RequestParam(value="clients_list_table_page", required = false) Long clients_list_table_page,

                              @RequestParam(value="clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                              @RequestParam(value="clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                              @RequestParam(value="clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                              @RequestParam(value="clients_services_collapse", required = false) Boolean clients_services_collapse,
                              @RequestParam(value="clients_address_collapse", required = false) Boolean clients_address_collapse,
                              @RequestParam(value="clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                              @RequestParam(value="clients_files_collapse", required = false) Boolean clients_files_collapse,
                              @RequestParam(value="clients_filter_collapse", required = false) Boolean clients_filter_collapse
    ) {
        List<User_List> user_List;
        List<User_List> filter_User_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_Status_List> contragent_Status_List;
        List<ClientClassList> clientClassList;
        List<ClientClassList> clientEditClassList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("cnt_id", cnt_id);

        model.addObject("clients_list_table_order_column", clients_list_table_order_column);
        model.addObject("clients_list_table_order_type", clients_list_table_order_type);
        model.addObject("clients_list_table_search", clients_list_table_search);
        model.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        model.addObject("clients_list_table_page", clients_list_table_page);

        model.addObject("clients_managers_collapse", clients_managers_collapse);
        model.addObject("clients_statistics_collapse", clients_statistics_collapse);
        model.addObject("clients_contacts_collapse", clients_contacts_collapse);
        model.addObject("clients_services_collapse", clients_services_collapse);
        model.addObject("clients_address_collapse", clients_address_collapse);
        model.addObject("clients_documents_collapse", clients_documents_collapse);
        model.addObject("clients_files_collapse", clients_files_collapse);
        model.addObject("clients_filter_collapse", clients_filter_collapse);

        model.addObject("clients_filter_check", clients_filter_check);
        model.addObject("clients_filters_user_id", clients_filters_user_id);
        model.addObject("clients_filters_status_id", clients_filters_status_id);
        model.addObject("clients_filters_class_id", clients_filters_class_id);

        filter_User_List = user_ListRepository.queryUserAllOrderDetail();
        model.addObject("filter_user_list", filter_User_List);

        contragent_Status_List = contragent_Status_ListRepository.queryGetAllStatus();
        model.addObject("filter_statuses_list", contragent_Status_List);

        clientClassList = (List<ClientClassList>) clientClassListRepository.findAll();
        model.addObject("filter_classes_list", clientClassList);

        clientEditClassList = clientClassListRepository.queryAllClientClassListBy();
        model.addObject("edit_classes_list", clientEditClassList);

        model.setViewName("clients/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(value="clients_filter_check", defaultValue = "0") Long clients_filter_check,
            @RequestParam(value="clients_filters_user_id", defaultValue = "0") Long clients_filters_user_id,
            @RequestParam(value="clients_filters_status_id", defaultValue = "-10") Long clients_filters_status_id,
            @RequestParam(value="clients_filters_class_id", defaultValue = "0") Long clients_filters_class_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClientsListRepository.queryClientMenuFilterListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, clients_filter_check, clients_filters_user_id, clients_filters_status_id, clients_filters_class_id
        ));
        return result;
    }

    @GetMapping("/get_list_detail")
    public JSONDatatable get_list_detail(
            @RequestParam(name="cnt_id", defaultValue = "0") Long cnt_id,
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0) {
                result.setData(detailClientsListRepository.queryNewClientListByUserID(
                        user_List.get(0).id,user_Role_List.get(0).role_id
                ));
            }
            else{
                result.setData(detailClientsListRepository.queryClientListDetaulByUserID(
                        user_List.get(0).id,user_Role_List.get(0).role_id, cnt_id
                ));
            }
        }
        return result;
    }

    @GetMapping("/get_cnt_status")
    public JSONDatatable get_cnt_status(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cs_id", required = false) Long cs_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode == 0) {
            result.setData(contragent_Status_ListRepository.queryGetCurrStatus(new Long(0)));
        }
        else{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData( contragent_Status_ListRepository.queryGetCurrStatusByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id,cs_id
            ));
        }
        return result;
    }

    @GetMapping("/get_country")
    public JSONDatatable get_country(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false, defaultValue = "0") Long country_id
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

    @GetMapping("/get_ownership")
    public JSONDatatable get_ownership(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="owt_id", required = false, defaultValue = "-1") Long owt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0 || mode == 1) {
            result.setData(ownerShip_Type_ListRepository.queryGetOwnList());
        }
        else{
            result.setData(ownerShip_Type_ListRepository.queryCurrentOwnList(owt_id));
        }
        return result;
    }

    @GetMapping("/get_law_type")
    public JSONDatatable get_law_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode >= 0) {
            result.setData(clientLawTypeListRepository.queryGetAllLawTypeList());
        }
        return result;
    }

    @GetMapping("/get_users")
    public JSONDatatable get_users(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id", required = false, defaultValue = "0") Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0) {
            if(user_Role_List.get(0).role_id == 2 || user_Role_List.get(0).role_id == 4 || user_Role_List.get(0).role_id == 5 || user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id ==  6) {
                result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
            }
            else{
                result.setData(user_ListRepository.queryUserAll());
            }
        }
        else{
            if(user_Role_List.get(0).role_id == 2 || user_Role_List.get(0).role_id == 4 || user_Role_List.get(0).role_id == 5 || user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id ==  6) {
                result.setData(user_ListRepository.queryUserByID(user_List.get(0).id));
            }
            else {
                result.setData(user_ListRepository.queryUserAll());
            }
        }
        return result;
    }

    @GetMapping("/get_source")
    public JSONDatatable get_source(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_source_id", required = false, defaultValue = "0") Long cnt_source_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0 || mode == 1) {
            result.setData(contragent_Source_ListRepository.queryEditContragentSource());
        }
        else{
            result.setData(contragent_Source_ListRepository.queryCurrentContragentSource(cnt_source_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add_client_list")
    public ModelAndView add_client_list(
            @RequestParam(name = "cnt_name") String cnt_name,
            @RequestParam(name = "cnt_sname", required = false) String cnt_sname,

            @RequestParam(name = "cnt_identifycode", required = false, defaultValue = "0") String cnt_identifycode,
            @RequestParam(name = "owt_id", required = false) Long owt_id,
            @RequestParam(name = "cs_id", required = false) Long cs_id,
            @RequestParam(name = "user_id", required = false, defaultValue = "0") Long user_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Long cnt_resident,
            @RequestParam(name = "cnt_source_id", required = false, defaultValue = "0") Long cnt_source_id,
            @RequestParam(name = "country_id", required = false, defaultValue = "0") Long country_id,
            @RequestParam(name = "cnt_name_eng", required = false) String cnt_name_eng,
            @RequestParam(name = "cnt_law_type_id", required = false) Long cnt_law_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Integer ErrorID = 0;
        Integer newCNTID = 0;
        String ErrorValue = new String();

        try{
            StoredProcedureQuery AddQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragent_new")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(16, Integer.class, ParameterMode.OUT)
                    .setParameter(1, cnt_name)
                    .setParameter(2, cnt_sname)
                    .setParameter(3, cnt_identifycode)
                    .setParameter(4, owt_id)
                    .setParameter(5, cs_id)
                    .setParameter(6, cnt_note)
                    .setParameter(7, cnt_resident)
                    .setParameter(8, user_id)
                    .setParameter(9, user_Role_List.get(0).role_id)
                    .setParameter(10, cnt_source_id)
                    .setParameter(11, country_id)
                    .setParameter(12, cnt_name_eng)
                    .setParameter(13, cnt_law_type_id)
                    ;
            AddQuery.execute();

            ErrorID = (Integer) AddQuery.getOutputParameterValue(14);
            ErrorValue = (String) AddQuery.getOutputParameterValue(15);
            newCNTID = (Integer) AddQuery.getOutputParameterValue(16);

            if(newCNTID > 0) {
                try {
                    Path out_path = Paths.get("/opt/files/OUT/" + newCNTID);
                    Path in_path = Paths.get("/opt/files/IN/" + newCNTID);

                    //java.nio.file.Files;
                    Files.createDirectories(out_path);
                    Files.createDirectories(in_path);
                    System.out.println("OUT Directory is created!");
                    System.out.println("IN Directory is created!");

                } catch (IOException e) {
                    System.err.println("Failed to create directory!" + e.getMessage());
                }
            }
        }
            catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_client_list")
    public ModelAndView edit_client_list(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "cnt_name") String cnt_name,
            @RequestParam(name = "cnt_sname", required = false) String cnt_sname,

            @RequestParam(name = "cnt_identifycode", required = false, defaultValue = "0") String cnt_identifycode,
            @RequestParam(name = "owt_id", required = false) Long owt_id,
            @RequestParam(name = "cs_id", required = false) Long cs_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Long cnt_resident,
            @RequestParam(name = "cnt_source_id", required = false, defaultValue = "0") Long cnt_source_id,
            @RequestParam(name = "country_id", required = false, defaultValue = "0") Long country_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "cnt_name_eng", required = false) String cnt_name_eng,
            @RequestParam(name = "cnt_law_type_id", required = false) Long cnt_law_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Integer ErrorID = 0;
        String ErrorValue = new String();

        try{
            StoredProcedureQuery EditQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragent_new")
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
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, cnt_name)
                    .setParameter(5, cnt_sname)
                    .setParameter(6, cnt_identifycode)
                    .setParameter(7, owt_id)
                    .setParameter(8, cs_id)
                    .setParameter(9, cnt_note)
                    .setParameter(10, cnt_resident)
                    .setParameter(11, cnt_source_id)
                    .setParameter(12, country_id)
                    .setParameter(13, user_id)
                    .setParameter(14, cnt_name_eng)
                    .setParameter(15, cnt_law_type_id)
                    ;
            EditQuery.execute();

            ErrorID = (Integer) EditQuery.getOutputParameterValue(16);
            ErrorValue = (String) EditQuery.getOutputParameterValue(17);
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_client_list")
    public ModelAndView del_client_list(
            @RequestParam(name = "cnt_id") Long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragent")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id);
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/list_model")
    public ModelAndView model(
            @RequestParam(value = "mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,

            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_sname", required = false) String cnt_sname,

            @RequestParam(name = "cnt_identifycode", required = false, defaultValue = "0") String cnt_identifycode,
            @RequestParam(name = "owt_id", required = false) Long owt_id,
            @RequestParam(name = "cs_id", required = false) Long cs_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Boolean cnt_resident,
            @RequestParam(name = "cnt_source_id", required = false, defaultValue = "0") Long cnt_source_id,
            @RequestParam(name = "country_id", required = false, defaultValue = "0") Long country_id,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();

        Long CntResidence;
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(cnt_resident == null){ CntResidence = new Long("0");}
        else { CntResidence = new Long("1");}

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Integer ErrorID = 0;
        String ErrorValue = new String();

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragent_new")
                            .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, Integer.class, ParameterMode.OUT)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.OUT)
                            .setParameter(1, cnt_name)
                            .setParameter(2, cnt_sname)
                            .setParameter(3, cnt_identifycode)
                            .setParameter(4, owt_id)
                            .setParameter(5, cs_id)
                            .setParameter(6, cnt_note)
                            .setParameter(7, CntResidence)
                            .setParameter(8, user_List.get(0).id)
                            .setParameter(9, user_Role_List.get(0).role_id)
                            .setParameter(10, cnt_source_id)
                            .setParameter(11, country_id);
                    AddQuery.execute();

                    ErrorID = (Integer) AddQuery.getOutputParameterValue(12);
                    ErrorValue = (String) AddQuery.getOutputParameterValue(13);
                    break;
                case 1:
                    StoredProcedureQuery EditQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragent_new")
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
                            .registerStoredProcedureParameter(12, Integer.class, ParameterMode.OUT)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.OUT)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cnt_name)
                            .setParameter(4, cnt_sname)
                            .setParameter(5, cnt_identifycode)
                            .setParameter(6, owt_id)
                            .setParameter(7, cs_id)
                            .setParameter(8, cnt_note)
                            .setParameter(9, CntResidence)
                            .setParameter(10, cnt_source_id)
                            .setParameter(11, country_id);
                    EditQuery.execute();

                    ErrorID = (Integer) EditQuery.getOutputParameterValue(12);
                    ErrorValue = (String) EditQuery.getOutputParameterValue(13);
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragent")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id);
                    DelQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }

            if( ErrorID > 0){
                mav.addObject("error_code",500);
                mav.addObject("exception", ErrorValue);
            }
            else {
                mav.addObject("error_code", 0);
            }
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("clients_list_table_order_column", order_column);
        mav.addObject("clients_list_table_order_type", order_type);
        mav.addObject("clients_list_table_search", table_search);
        mav.addObject("clients_list_table_pagelen", pagelen);
        mav.addObject("clients_list_table_page", page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/list_detail");
        return mav;
    }

    @PostMapping("/list_add_link")
    public void task_uncheck(
            @RequestParam(name = "cnt_id") long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery TaskUnCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_UserContragentLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id);
        TaskUnCheckQuery.execute();
        return;
    }

    @PostMapping("/list_renew")
    public void list_renew(
            @RequestParam(name = "cnt_id") long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery CntRenewQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_UserContragentRenew")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id);
        CntRenewQuery.execute();
        return;
    }

    @PostMapping("/edit_class")
    public ModelAndView edit_class(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "edit_class_id") Long edit_class_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                .setParameter(4, edit_class_id)
                ;
        EditClientClassQuery.execute();
        return null;
    }

    @PostMapping("/del_class")
    public ModelAndView del_class(
            @RequestParam(name = "cnt_id") Long cnt_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                ;
        DelClientClassQuery.execute();
        return null;
    }

    @PostMapping("/get_contacts_table")
    public JSONDatatable get_contacts_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientContactsListRepository.queryClientContactsMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @RequestMapping("/contacts_detail")
    public ModelAndView contacts_detail(@RequestParam(value = "mode") Long mode,
                                        @RequestParam(value = "cnt_id") Long cnt_id,
                                        @RequestParam(value = "cnt_name") String cnt_name,
                                        @RequestParam(value = "cc_id") Long cc_id,

                                        @RequestParam(value = "clients_contacts_table_order_column") Long order_column,
                                        @RequestParam(value = "clients_contacts_table_order_type") String order_type,
                                        @RequestParam(value = "clients_contacts_table_search") String table_search,
                                        @RequestParam(value = "clients_contacts_table_pagelen") Long pagelen,
                                        @RequestParam(value = "clients_contacts_table_page") Long page,

                                        @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                        @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                        @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                        @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                        @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                        @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                        @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                        @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                        @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                        @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                        @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                        @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                        @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                        @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                        @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                        @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                        @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<Direction> directionDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailClientContactsList> detailClientsContactsList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);
        }
        else{
            detailClientsContactsList = detailClientsContactsListRepository.queryClientContactsListDetaulByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, cc_id);

            mav.addObject("cnt_id", detailClientsContactsList.get(0).cnt_id);
            mav.addObject("cnt_name", detailClientsContactsList.get(0).cnt_name);

            mav.addObject("cc_id", detailClientsContactsList.get(0).cc_id);

            mav.addObject("cc_person", detailClientsContactsList.get(0).cc_person);

            mav.addObject("cc_position", detailClientsContactsList.get(0).cc_position);

            mav.addObject("cc_main_phone", detailClientsContactsList.get(0).cc_main_phone);
            if(detailClientsContactsList.get(0).cc_main_phone != null && detailClientsContactsList.get(0).cc_main_phone != "") {
                mav.addObject("cc_main_phone_link", "tel:" + detailClientsContactsList.get(0).cc_main_phone);
            }

            mav.addObject("cc_second_phone", detailClientsContactsList.get(0).cc_second_phone);
            if(detailClientsContactsList.get(0).cc_second_phone != null && detailClientsContactsList.get(0).cc_second_phone != "") {
                mav.addObject("cc_second_phone_link", "tel:" + detailClientsContactsList.get(0).cc_second_phone);
            }

            mav.addObject("cc_main_office_phone", detailClientsContactsList.get(0).cc_main_office_phone);
            if(detailClientsContactsList.get(0).cc_main_office_phone != null && detailClientsContactsList.get(0).cc_main_office_phone != "") {
                mav.addObject("cc_main_office_phone_link", "tel:" + detailClientsContactsList.get(0).cc_main_office_phone);
            }

            mav.addObject("cc_second_office_phone", detailClientsContactsList.get(0).cc_second_office_phone);
            if(detailClientsContactsList.get(0).cc_second_office_phone != null && detailClientsContactsList.get(0).cc_second_office_phone != "") {
                mav.addObject("cc_second_office_phone_link", "tel:" + detailClientsContactsList.get(0).cc_second_office_phone);
            }

            mav.addObject("cc_email", detailClientsContactsList.get(0).cc_email);
            if(detailClientsContactsList.get(0).cc_email != null && detailClientsContactsList.get(0).cc_email != "") {
                mav.addObject("cc_email_link", "mailto:" + detailClientsContactsList.get(0).cc_email);
            }

            mav.addObject("cc_skype", detailClientsContactsList.get(0).cc_skype);
            if(detailClientsContactsList.get(0).cc_skype != null && detailClientsContactsList.get(0).cc_skype != "") {
                mav.addObject("cc_skype_link", "skype:" + detailClientsContactsList.get(0).cc_skype);
            }

            mav.addObject("cc_note", detailClientsContactsList.get(0).cc_note);
        }

        mav.addObject("clients_contacts_table_order_column", order_column);
        mav.addObject("clients_contacts_table_order_type", order_type);
        mav.addObject("clients_contacts_table_search", table_search);
        mav.addObject("clients_contacts_table_pagelen", pagelen);
        mav.addObject("clients_contacts_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/contacts_detail");
        return mav;
    }

    @PostMapping("/contacts_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cc_id", required = false) Long cc_id,

            @RequestParam(name = "cc_person", required = false) String cc_person,
            @RequestParam(name = "cc_position", required = false) String cc_position,
            @RequestParam(name = "cc_main_phone", required = false) String cc_main_phone,
            @RequestParam(name = "cc_second_phone", required = false) String cc_second_phone,
            @RequestParam(name = "cc_email", required = false) String cc_email,
            @RequestParam(name = "cc_skype", required = false) String cc_skype,
            @RequestParam(name = "cc_note", required = false) String cc_note,
            @RequestParam(name = "cc_main_office_phone", required = false) String cc_main_office_phone,
            @RequestParam(name = "cc_second_office_phone", required = false) String cc_second_office_phone,

            @RequestParam(name="clients_contacts_table_order_column", required = false) Long order_column,
            @RequestParam(name="clients_contacts_table_order_type", required = false) String order_type,
            @RequestParam(name="clients_contacts_table_search", required = false) String table_search,
            @RequestParam(name="clients_contacts_table_pagelen", required = false) Long pagelen,
            @RequestParam(name="clients_contacts_table_page", required = false) Long page,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddContQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContContact")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cc_person)
                            .setParameter(4, cc_position)
                            .setParameter(5, cc_main_phone)
                            .setParameter(6, cc_second_phone)
                            .setParameter(7, cc_email)
                            .setParameter(8, cc_skype)
                            .setParameter(9, cc_note)
                            .setParameter(10, cc_main_office_phone)
                            .setParameter(11, cc_second_office_phone);
                    AddContQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditContQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContContact")
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
                            .setParameter(2, cnt_id)
                            .setParameter(3, cc_id)
                            .setParameter(4, cc_person)
                            .setParameter(5, cc_position)
                            .setParameter(6, cc_main_phone)
                            .setParameter(7, cc_second_phone)
                            .setParameter(8, cc_email)
                            .setParameter(9, cc_skype)
                            .setParameter(10, cc_note)
                            .setParameter(11, cc_main_office_phone)
                            .setParameter(12, cc_second_office_phone);
                    EditContQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelContQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContContact")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cc_id);
                    DelContQuery.execute();
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

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("cc_id", cc_id);

        mav.addObject("cc_person", cc_person);

        mav.addObject("cc_position", cc_position);

        mav.addObject("cc_main_phone", cc_main_phone);
        if(cc_main_phone != null && cc_main_phone != "") {
            mav.addObject("cc_main_phone_link", "tel:" + cc_main_phone);
        }

        mav.addObject("cc_second_phone", cc_second_phone);
        if(cc_second_phone != null && cc_second_phone != "") {
            mav.addObject("cc_second_phone_link", "tel:" + cc_second_phone);
        }

        mav.addObject("cc_main_office_phone", cc_main_office_phone);
        if(cc_main_office_phone != null && cc_main_office_phone != "") {
            mav.addObject("cc_main_office_phone_link", "tel:" + cc_main_office_phone);
        }

        mav.addObject("cc_second_office_phone", cc_second_office_phone);
        if(cc_second_office_phone != null && cc_second_office_phone != "") {
            mav.addObject("cc_second_office_phone_link", "tel:" + cc_second_office_phone);
        }

        mav.addObject("cc_email", cc_email);
        if(cc_email != null && cc_email != "") {
            mav.addObject("cc_email_link", "mailto:" + cc_email);
        }

        mav.addObject("cc_skype", cc_skype);
        if(cc_skype != null && cc_skype != "") {
            mav.addObject("cc_skype_link", "skype:" + cc_skype);
        }

        mav.addObject("cc_note", cc_note);

        mav.addObject("clients_contacts_table_order_column", order_column);
        mav.addObject("clients_contacts_table_order_type", order_type);
        mav.addObject("clients_contacts_table_search", table_search);
        mav.addObject("clients_contacts_table_pagelen", pagelen);
        mav.addObject("clients_contacts_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/contacts_detail");
        return mav;
    }

    @PostMapping("/get_services_table")
    public JSONDatatable get_services_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientServicesListRepository.queryClientServicesMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/services_detail")
    public ModelAndView services_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="cnt_id") Long cnt_id,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="cntserl_id") Long cntserl_id,

                                        @RequestParam(value="clients_services_table_order_column") Long order_column,
                                        @RequestParam(value="clients_services_table_order_type") String order_type,
                                        @RequestParam(value="clients_services_table_search") String table_search,
                                        @RequestParam(value="clients_services_table_pagelen") Long pagelen,
                                        @RequestParam(value="clients_services_table_page") Long page,

                                        @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                        @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                        @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                        @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                        @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                        @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                        @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                        @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                        @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                        @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                        @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                        @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                        @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                        @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                        @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                        @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                        @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Service_Type_List> service_Type_List;
        List<DetailClientServicesList> detailClientsServicesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            service_Type_List = service_Type_ListRepository.queryNewSerIDByCnt(cnt_id);
            mav.addObject("ser_list", service_Type_List);
        }
        else{
            detailClientsServicesList = detailClientsServicesListRepository.queryClientServicesListDetaulByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cntserl_id);

            mav.addObject("cnt_id", detailClientsServicesList.get(0).cnt_id);
            mav.addObject("cnt_name", detailClientsServicesList.get(0).cnt_name);

            mav.addObject("cntserl_id", detailClientsServicesList.get(0).cntserl_id);
            mav.addObject("cntserl_date", detailClientsServicesList.get(0).cntserl_date);

            mav.addObject("ser_id", detailClientsServicesList.get(0).ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(detailClientsServicesList.get(0).ser_id);
            mav.addObject("ser_list", service_Type_List);
        }

        mav.addObject("clients_services_table_order_column", order_column);
        mav.addObject("clients_services_table_order_type", order_type);
        mav.addObject("clients_services_table_search", table_search);
        mav.addObject("clients_services_table_pagelen", pagelen);
        mav.addObject("clients_services_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/services_detail");
        return mav;
    }

    @PostMapping("/services_model")
    public ModelAndView service_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cntserl_id", required = false) Long cntserl_id,
            @RequestParam(name = "ser_id", required = false) Long ser_id,
            @RequestParam(name = "ser_name", required = false) String ser_name,
            @RequestParam(name = "cntserl_date", required = false) String cntserl_date,

            @RequestParam(name = "cc_person", required = false) String cc_person,
            @RequestParam(name = "cc_position", required = false) String cc_position,
            @RequestParam(name = "cc_main_phone", required = false) String cc_main_phone,
            @RequestParam(name = "cc_second_phone", required = false) String cc_second_phone,
            @RequestParam(name = "cc_email", required = false) String cc_email,
            @RequestParam(name = "cc_skype", required = false) String cc_skype,
            @RequestParam(name = "cc_note", required = false) String cc_note,
            @RequestParam(name = "cc_main_office_phone", required = false) String cc_main_office_phone,
            @RequestParam(name = "cc_second_office_phone", required = false) String cc_second_office_phone,

            @RequestParam(name="clients_services_table_order_column", required = false) Long order_column,
            @RequestParam(name="clients_services_table_order_type", required = false) String order_type,
            @RequestParam(name="clients_services_table_search", required = false) String table_search,
            @RequestParam(name="clients_services_table_pagelen", required = false) Long pagelen,
            @RequestParam(name="clients_services_table_page", required = false) Long page,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<Service_Type_List> service_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContService")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, ser_id);
                    AddQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContService")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntserl_id);
                    DelQuery.execute();
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

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("cntserl_id", cntserl_id);
        mav.addObject("cntserl_date", cntserl_date);

        if(mode == 0){
            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryNewSerIDByCnt(cnt_id);
            mav.addObject("ser_list", service_Type_List);
        }
        else{
            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);
        }

        mav.addObject("clients_services_table_order_column", order_column);
        mav.addObject("clients_services_table_order_type", order_type);
        mav.addObject("clients_services_table_search", table_search);
        mav.addObject("clients_services_table_pagelen", pagelen);
        mav.addObject("clients_services_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/services_detail");
        return mav;
    }

    @PostMapping("/get_address_table")
    public JSONDatatable get_address_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientAddressListRepository.queryClientAddressMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/address_detail")
    public ModelAndView address_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="cnt_id") Long cnt_id,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="cnt_add_id") Long cnt_add_id,

                                        @RequestParam(value="clients_address_table_order_column") Long order_column,
                                        @RequestParam(value="clients_address_table_order_type") String order_type,
                                        @RequestParam(value="clients_address_table_search") String table_search,
                                        @RequestParam(value="clients_address_table_pagelen") Long pagelen,
                                        @RequestParam(value="clients_address_table_page") Long page,

                                       @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                       @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                       @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                       @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                       @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                       @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                       @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                       @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                       @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                       @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                       @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                       @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                       @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                       @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                       @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                       @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                       @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Address_Type_List> address_Type_List;
        List<DetailClientAddressList> detailClientAddressList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            address_Type_List = (List<Address_Type_List>) address_Type_ListRepository.findAll();
            mav.addObject("add_type_list", address_Type_List);
        }
        else{
            detailClientAddressList = detailClientAddressListRepository.queryClientAddressDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_add_id);

            mav.addObject("cnt_id", detailClientAddressList.get(0).cnt_id);
            mav.addObject("cnt_name", detailClientAddressList.get(0).cnt_name);

            mav.addObject("cnt_add_id", detailClientAddressList.get(0).cnt_add_id);

            mav.addObject("add_type_id", detailClientAddressList.get(0).add_type_id);
            address_Type_List = address_Type_ListRepository.queryClientAddressTypeByID(detailClientAddressList.get(0).add_type_id);
            mav.addObject("add_type_list", address_Type_List);

            mav.addObject("cnt_add_value", detailClientAddressList.get(0).cnt_add_value);

            mav.addObject("cnt_add_note", detailClientAddressList.get(0).cnt_add_note);
        }

        mav.addObject("clients_address_table_order_column", order_column);
        mav.addObject("clients_address_table_order_type", order_type);
        mav.addObject("clients_address_table_search", table_search);
        mav.addObject("clients_address_table_pagelen", pagelen);
        mav.addObject("clients_address_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/address_detail");
        return mav;
    }

    @PostMapping("/address_model")
    public ModelAndView address_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_add_id", required = false) Long cnt_add_id,
            @RequestParam(name = "add_type_id", required = true) Long add_type_id,
            @RequestParam(name = "cnt_add_value", required = false) String cnt_add_value,
            @RequestParam(name = "cnt_add_note", required = false) String cnt_add_note,

            @RequestParam(name = "clients_address_table_order_column", required = false) Long order_column,
            @RequestParam(name = "clients_address_table_order_type", required = false) String order_type,
            @RequestParam(name = "clients_address_table_search", required = false) String table_search,
            @RequestParam(name = "clients_address_table_pagelen", required = false) Long pagelen,
            @RequestParam(name = "clients_address_table_page", required = false) Long page,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<Address_Type_List> address_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddaddressQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContAddress")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, add_type_id)
                            .setParameter(4, cnt_add_value)
                            .setParameter(5, cnt_add_note);
                    AddaddressQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditAddressQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContAddress")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cnt_add_id)
                            .setParameter(4, add_type_id)
                            .setParameter(5, cnt_add_value)
                            .setParameter(6, cnt_add_note);
                    EditAddressQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelAddressQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContAddress")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cnt_add_id);
                    DelAddressQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        if(mode == 0){
            mav.addObject("add_type_id", add_type_id);
            address_Type_List = (List<Address_Type_List>) address_Type_ListRepository.findAll();
            mav.addObject("add_type_list", address_Type_List);
        }
        else{
            mav.addObject("cnt_add_id", cnt_add_id);

            mav.addObject("add_type_id", add_type_id);
            address_Type_List = address_Type_ListRepository.queryClientAddressTypeByID(add_type_id);
            mav.addObject("add_type_list", address_Type_List);
        }

        mav.addObject("cnt_add_value", cnt_add_value);

        mav.addObject("cnt_add_note", cnt_add_note);

        mav.addObject("clients_address_table_order_column", order_column);
        mav.addObject("clients_address_table_order_type", order_type);
        mav.addObject("clients_address_table_search", table_search);
        mav.addObject("clients_address_table_pagelen", pagelen);
        mav.addObject("clients_address_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/address_detail");
        return mav;
    }

    @PostMapping("/get_documents_table")
    public JSONDatatable get_documents_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientDocumentsListRepository.queryClientDocumentsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/documents_detail")
    public ModelAndView documents_detail(@RequestParam(value="mode") Long mode,
                                         @RequestParam(value="cnt_id") Long cnt_id,
                                         @RequestParam(value="cnt_name") String cnt_name,
                                         @RequestParam(value="cnt_doc_id") Long cnt_doc_id,

                                         @RequestParam(value="clients_documents_table_order_column") Long order_column,
                                         @RequestParam(value="clients_documents_table_order_type") String order_type,
                                         @RequestParam(value="clients_documents_table_search") String table_search,
                                         @RequestParam(value="clients_documents_table_pagelen") Long pagelen,
                                         @RequestParam(value="clients_documents_table_page") Long page,

                                         @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                         @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                         @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                         @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                         @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                         @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                         @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                         @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                         @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                         @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                         @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                         @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                         @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                         @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                         @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                         @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                         @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailClientDocumentsList> detailClientDocumentsList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);
        }
        else{
            detailClientDocumentsList = detailClientDocumentsListRepository.queryClientDocumentsDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_doc_id);

            mav.addObject("cnt_id", detailClientDocumentsList.get(0).cnt_id);
            mav.addObject("cnt_name", detailClientDocumentsList.get(0).cnt_name);

            mav.addObject("cnt_doc_id", detailClientDocumentsList.get(0).cnt_doc_id);
            mav.addObject("cnt_doc_num", detailClientDocumentsList.get(0).cnt_doc_num);

            mav.addObject("cnt_doc_start_date", detailClientDocumentsList.get(0).cnt_doc_start_date );

            mav.addObject("cnt_doc_end_date", detailClientDocumentsList.get(0).cnt_doc_end_date);

            mav.addObject("cnt_doc_note", detailClientDocumentsList.get(0).cnt_doc_note);
        }

        mav.addObject("clients_documents_table_order_column", order_column);
        mav.addObject("clients_documents_table_order_type", order_type);
        mav.addObject("clients_documents_table_search", table_search);
        mav.addObject("clients_documents_table_pagelen", pagelen);
        mav.addObject("clients_documents_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/documents_detail");
        return mav;
    }

    @PostMapping("/documents_model")
    public ModelAndView documents_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_doc_id", required = false) Long cnt_doc_id,
            @RequestParam(name = "cnt_doc_num", required = false) String cnt_doc_num,
            @RequestParam(name = "cnt_doc_start_date", required = false) String cnt_doc_start_date,
            @RequestParam(name = "cnt_doc_end_date", required = false) String cnt_doc_end_date,
            @RequestParam(name = "cnt_doc_note", required = false) String cnt_doc_note,

            @RequestParam(name="clients_documents_table_order_column", required = false) Long order_column,
            @RequestParam(name="clients_documents_table_order_type", required = false) String order_type,
            @RequestParam(name="clients_documents_table_search", required = false) String table_search,
            @RequestParam(name="clients_documents_table_pagelen", required = false) Long pagelen,
            @RequestParam(name="clients_documents_table_page", required = false) Long page,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Date Date1 = null;
        Date Date2 = null;

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        if(cnt_doc_start_date != "") {
            String startDate = cnt_doc_start_date;
            Date1 = df.parse(startDate);
        }

        if(cnt_doc_end_date != "") {
            String endDate = cnt_doc_end_date;
            Date2 = df.parse(endDate);
        }

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContDoc")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cnt_id)
                            .setParameter(4, cnt_doc_num)
                            .setParameter(5, Date1)
                            .setParameter(6, Date2)
                            .setParameter(7, cnt_doc_note)
                            ;
                    AddQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContDoc")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Date.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cnt_id)
                            .setParameter(4, cnt_doc_id)
                            .setParameter(5, cnt_doc_num)
                            .setParameter(6, Date1)
                            .setParameter(7, Date2)
                            .setParameter(8, cnt_doc_note)
                            ;
                    EditQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContDoc")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cnt_id)
                            .setParameter(4, cnt_doc_id)
                            ;
                    DelQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("cnt_doc_id", cnt_doc_id);
        mav.addObject("cnt_doc_num", cnt_doc_num);

        mav.addObject("cnt_doc_start_date", cnt_doc_start_date );

        mav.addObject("cnt_doc_end_date", cnt_doc_end_date);

        mav.addObject("cnt_doc_note", cnt_doc_note);

        mav.addObject("clients_documents_table_order_column", order_column);
        mav.addObject("clients_documents_table_order_type", order_type);
        mav.addObject("clients_documents_table_search", table_search);
        mav.addObject("clients_documents_table_pagelen", pagelen);
        mav.addObject("clients_documents_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/documents_detail");
        return mav;
    }

    @PostMapping("/get_files_table")
    public JSONDatatable get_files_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_doc_id", required = false) Long cnt_doc_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0 && cnt_doc_id != null && cnt_doc_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientDocFilesListRepository.queryClientDocFilesMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, cnt_doc_id));
        }
        return result;
    }

    @RequestMapping("/files_detail")
    public ModelAndView files_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="cnt_id") Long cnt_id,
                                     @RequestParam(value="cnt_name") String cnt_name,
                                     @RequestParam(value="cnt_doc_id") Long cnt_doc_id,
                                     @RequestParam(value="cnt_doc_num") String cnt_doc_num,
                                     @RequestParam(value="cnt_doc_file_id") Long cnt_doc_file_id,

                                     @RequestParam(value="clients_files_table_order_column") Long order_column,
                                     @RequestParam(value="clients_files_table_order_type") String order_type,
                                     @RequestParam(value="clients_files_table_search") String table_search,
                                     @RequestParam(value="clients_files_table_pagelen") Long pagelen,
                                     @RequestParam(value="clients_files_table_page") Long page,

                                     @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                     @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                     @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                     @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                     @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                     @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                     @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                     @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                     @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                     @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                     @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                     @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                     @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                     @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                     @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                     @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                     @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailClientDocFilesList> detailClientDocFilesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("cnt_doc_id", cnt_doc_id);
            mav.addObject("cnt_doc_num", cnt_doc_num);
        }
        else{
            detailClientDocFilesList =  detailClientDocFilesListRepository.queryClientAddressDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_doc_file_id);

            mav.addObject("cnt_id", detailClientDocFilesList.get(0).cnt_id);
            mav.addObject("cnt_name", detailClientDocFilesList.get(0).cnt_name);

            mav.addObject("cnt_doc_id", detailClientDocFilesList.get(0).cnt_doc_id);
            mav.addObject("cnt_doc_num", detailClientDocFilesList.get(0).cnt_doc_num);

            mav.addObject("cnt_doc_file_id", detailClientDocFilesList.get(0).cnt_doc_file_id);
            mav.addObject("cnt_doc_file_date", detailClientDocFilesList.get(0).cnt_doc_file_date);
            mav.addObject("cnt_doc_file_name", detailClientDocFilesList.get(0).cnt_doc_file_name);
        }

        mav.addObject("clients_files_table_order_column", order_column);
        mav.addObject("clients_files_table_order_type", order_type);
        mav.addObject("clients_files_table_search", table_search);
        mav.addObject("clients_files_table_pagelen", pagelen);
        mav.addObject("clients_files_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/files_detail");
        return mav;
    }

    @PostMapping("/files_model")
    public ModelAndView files_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,
            @RequestParam(name = "cnt_doc_id", required = false) Long cnt_doc_id,
            @RequestParam(name = "cnt_doc_num", required = false) String cnt_doc_num,
            @RequestParam(name = "cnt_doc_file_id", required = false) Long cnt_doc_file_id,
            @RequestParam(name = "cnt_doc_file_date", required = false) String cnt_doc_file_date,
            @RequestParam("file") MultipartFile file,

            @RequestParam(name = "clients_documents_table_order_column", required = false) Long order_column,
            @RequestParam(name = "clients_documents_table_order_type", required = false) String order_type,
            @RequestParam(name = "clients_documents_table_search", required = false) String table_search,
            @RequestParam(name = "clients_documents_table_pagelen", required = false) Long pagelen,
            @RequestParam(name = "clients_documents_table_page", required = false) Long page,

            @RequestParam(name = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(name = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(name = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(name = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(name = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(name = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(name = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(name = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(name="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(name="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(name="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(name="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    ClientDocFile client_doc_file = new ClientDocFile();

                    client_doc_file.cnt_id = cnt_id;
                    client_doc_file.cnt_doc_id = cnt_doc_id;
                    client_doc_file.cnt_doc_file_name = file.getOriginalFilename();
                    client_doc_file.cnt_doc_file_contenttype = file.getContentType();

                    try (InputStream inputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(inputStream);
                        client_doc_file.cnt_doc_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    client_doc_file = clientDocFileRepository.save(client_doc_file);
                    break;
                case 2:
                    StoredProcedureQuery DelDocFileQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContDocFile")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_doc_file_id);
                    DelDocFileQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("clients_documents_table_order_column", order_column);
        mav.addObject("clients_documents_table_order_type", order_type);
        mav.addObject("clients_documents_table_search", table_search);
        mav.addObject("clients_documents_table_pagelen", pagelen);
        mav.addObject("clients_documents_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/documents_detail");
        return mav;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(
            @RequestParam(name = "cnt_doc_file_id") Long cnt_doc_file_id
    ) throws SQLException {

        ClientDocFile clientDocFile = (ClientDocFile) clientDocFileRepository.queryDocByID(cnt_doc_file_id);

        int blobLength = (int) clientDocFile.cnt_doc_file_body.length();
        byte[] output = clientDocFile.cnt_doc_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(clientDocFile.cnt_doc_file_contenttype));
        responseHeaders.setContentLength(output.length);
        try {
            responseHeaders.set("Content-disposition", "attachment; filename="+
                    MimeUtility.encodeWord(clientDocFile.cnt_doc_file_name, "utf-8", "Q")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientUsersListRepository.queryClientUsersMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/users_detail")
    public ModelAndView users_detail(@RequestParam(value = "mode") Long mode,
                                     @RequestParam(value = "cnt_id", required = false) Long cnt_id,
                                     @RequestParam(value = "cnt_name", required = false) String cnt_name,
                                     @RequestParam(value = "cntul_id", required = false) Long cntul_id,
                                     @RequestParam(value = "user_id", required = false) Long cur_user_id,
                                     @RequestParam(value = "cntul_main_id", required = false) Long cntul_main_id,

                                     @RequestParam(value = "clients_users_table_order_column") Long order_column,
                                     @RequestParam(value = "clients_users_table_order_type") String order_type,
                                     @RequestParam(value = "clients_users_table_search") String table_search,
                                     @RequestParam(value = "clients_users_table_pagelen") Long pagelen,
                                     @RequestParam(value = "clients_users_table_page") Long page,

                                     @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
                                     @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
                                     @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
                                     @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
                                     @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

                                     @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
                                     @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
                                     @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                     @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                     @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                     @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                     @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
                                     @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

                                     @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
                                     @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
                                     @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
                                     @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> user_Edit_List;
        List<User_Role_List> user_Role_List;
        List<ClientUser_List> clientUser_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("cntul_id", cntul_id);

        if(mode == 0){
            clientUser_List = clientUser_ListRepository.queryNewCntUserByID(cnt_id);
            mav.addObject("user_list", clientUser_List);
        }
        else{
            mav.addObject("user_id", cur_user_id);
            user_Edit_List = user_ListRepository.queryUserByID(cur_user_id);
            mav.addObject("user_list", user_Edit_List);
        }

        mav.addObject("cntul_main_id", cntul_main_id);

        mav.addObject("clients_users_table_order_column", order_column);
        mav.addObject("clients_users_table_order_type", order_type);
        mav.addObject("clients_users_table_search", table_search);
        mav.addObject("clients_users_table_pagelen", pagelen);
        mav.addObject("clients_users_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/users_detail");
        return mav;
    }

    @PostMapping("/users_model")
    public ModelAndView user_model(
            @RequestParam(value = "mode") Long mode,
            @RequestParam(value = "cnt_id", required = false) Long cnt_id,
            @RequestParam(value = "cnt_name", required = false) String cnt_name,
            @RequestParam(value = "cntul_id", required = false) Long cntul_id,
            @RequestParam(value = "user_id", required = false) Long cur_user_id,
            @RequestParam(value = "cntul_main_id", required = false) Boolean cntul_main_id,

            @RequestParam(value = "clients_users_table_order_column") Long order_column,
            @RequestParam(value = "clients_users_table_order_type") String order_type,
            @RequestParam(value = "clients_users_table_search") String table_search,
            @RequestParam(value = "clients_users_table_pagelen") Long pagelen,
            @RequestParam(value = "clients_users_table_page") Long page,

            @RequestParam(value = "clients_list_table_order_column", required = false) Long clients_list_table_order_column,
            @RequestParam(value = "clients_list_table_order_type", required = false) String clients_list_table_order_type,
            @RequestParam(value = "clients_list_table_search", required = false) String clients_list_table_search,
            @RequestParam(value = "clients_list_table_pagelen", required = false) Long clients_list_table_pagelen,
            @RequestParam(value = "clients_list_table_page", required = false) Long clients_list_table_page,

            @RequestParam(value = "clients_managers_collapse", required = false) Boolean clients_managers_collapse,
            @RequestParam(value = "clients_statistics_collapse", required = false) Boolean clients_statistics_collapse,
            @RequestParam(value = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(value = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(value = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(value = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(value = "clients_files_collapse", required = false) Boolean clients_files_collapse,
            @RequestParam(value = "clients_filter_collapse", required = false) Boolean clients_filter_collapse,

            @RequestParam(value="clients_filter_check", required = false) Long clients_filter_check,
            @RequestParam(value="clients_filters_user_id", required = false) Long clients_filters_user_id,
            @RequestParam(value="clients_filters_status_id", required = false) Long clients_filters_status_id,
            @RequestParam(value="clients_filters_class_id", required = false) Long clients_filters_class_id
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> user_Edit_List;
        List<User_Role_List> user_Role_List;
        List<ClientUser_List> clientUser_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Long CntulMain;
        if(cntul_main_id == null){ CntulMain = new Long("0");}
        else { CntulMain = new Long("1");}

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cur_user_id)
                            .setParameter(4, CntulMain);
                    AddUserClintQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntul_id)
                            .setParameter(3, CntulMain);
                    EditUserClintQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntul_id);
                    DelUserClintQuery.execute();
                    cntul_id = null;
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

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_name", cnt_name);

        mav.addObject("cntul_id", cntul_id);

        if(mode == 0){
            mav.addObject("user_id", cur_user_id);
            clientUser_List = clientUser_ListRepository.queryNewCntUserByID(cnt_id);
            mav.addObject("user_list", clientUser_List);
        }
        else{
            mav.addObject("user_id", cur_user_id);
            user_Edit_List = user_ListRepository.queryUserByID(cur_user_id);
            mav.addObject("user_list", user_Edit_List);
        }

        mav.addObject("cntul_main_id", cntul_main_id);

        mav.addObject("clients_users_table_order_column", order_column);
        mav.addObject("clients_users_table_order_type", order_type);
        mav.addObject("clients_users_table_search", table_search);
        mav.addObject("clients_users_table_pagelen", pagelen);
        mav.addObject("clients_users_table_page", page);

        mav.addObject("clients_list_table_order_column", clients_list_table_order_column);
        mav.addObject("clients_list_table_order_type", clients_list_table_order_type);
        mav.addObject("clients_list_table_search", clients_list_table_search);
        mav.addObject("clients_list_table_pagelen", clients_list_table_pagelen);
        mav.addObject("clients_list_table_page", clients_list_table_page);

        mav.addObject("clients_managers_collapse", clients_managers_collapse);
        mav.addObject("clients_statistics_collapse", clients_statistics_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);
        mav.addObject("clients_filter_collapse", clients_filter_collapse);

        mav.addObject("clients_filter_check", clients_filter_check);
        mav.addObject("clients_filters_user_id", clients_filters_user_id);
        mav.addObject("clients_filters_status_id", clients_filters_status_id);
        mav.addObject("clients_filters_class_id", clients_filters_class_id);

        mav.setViewName("/clients/users_detail");
        return mav;
    }

    @PostMapping("/get_users_roles_table")
    public JSONDatatable get_users_roles_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientUsersRolesListRepository.queryClientUsersRolesMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/edit_users_roles")
    public ModelAndView edit_users_roles(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "ser_id") Long ser_id,
            @RequestParam(name = "cs_cntul_id", required = false, defaultValue = "0") Long cs_cntul_id,
            @RequestParam(name = "cs_user_id", required = false, defaultValue = "0") Long cs_user_id,
            @RequestParam(name = "cs_main_id", required = false) Long cs_main_id,
            @RequestParam(name = "cs_persent", required = false, defaultValue = "0") String  cs_persent,
            @RequestParam(name = "ops_cntul_id", required = false, defaultValue = "0") Long ops_cntul_id,
            @RequestParam(name = "ops_user_id", required = false, defaultValue = "0") Long ops_user_id,
            @RequestParam(name = "ops_main_id", required = false) Long ops_main_id,
            @RequestParam(name = "ops_persent", required = false, defaultValue = "0") String ops_persent,
            @RequestParam(name = "cs_start_date", required = false) String cs_start_date,
            @RequestParam(name = "cs_end_date", required = false) String cs_end_date,
            @RequestParam(name = "ops_start_date", required = false) String ops_start_date,
            @RequestParam(name = "ops_end_date", required = false) String ops_end_date
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        try{
            StoredProcedureQuery EditUsersRolesQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragentUserLink_new")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, ser_id)
                    .setParameter(5, cs_cntul_id)
                    .setParameter(6, cs_user_id)
                    .setParameter(7, cs_main_id)
                    .setParameter(8, cs_persent)
                    .setParameter(9, ops_cntul_id)
                    .setParameter(10, ops_user_id)
                    .setParameter(11, ops_main_id)
                    .setParameter(12, ops_persent)
                    .setParameter(13, cs_start_date)
                    .setParameter(14, cs_end_date)
                    .setParameter(15, ops_start_date)
                    .setParameter(16, ops_end_date)
                    ;
            EditUsersRolesQuery.execute();
        }
            catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_users_self_add")
    public ModelAndView edit_users_self_add(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "ser_id") Long ser_id
    ){
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditUsersRolesQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddUserSelfContragentServiceLink")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                .setParameter(4, ser_id)
                ;
        EditUsersRolesQuery.execute();
        return null;
    }

    @GetMapping("/get_sale_list")
    public JSONDatatable get_sale_list(
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(user_ListRepository.querySaleUserAll());
        return result;
    }

    @GetMapping("/get_ops_list")
    public JSONDatatable get_ops_list(
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(user_ListRepository.queryOPSUserAll());
        return result;
    }

    @PostMapping("/get_statistics_table")
    public JSONDatatable get_statistics_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0 && start_date != null && end_date != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientStatisticsListRepository.queryClientMenuStatisticsListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, start_date, end_date));
        }
        return result;
    }

    @PostMapping("/get_sub_table")
    public JSONDatatable get_sub_table(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if (cnt_id != null && cnt_id > 0) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientSubListRepository.queryClientMenuSubListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @GetMapping("/get_sub_list")
    public JSONDatatable get_sub_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode == 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(contragentSubListRepository.queryCntSubList(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @PostMapping("/add_client_sub")
    public ModelAndView add_client_sub(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "cnt_sub_id", required = false) Long cnt_sub_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddSubQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragentSubLink")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, cnt_sub_id)
                    ;
            AddSubQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_client_sub")
    public ModelAndView del_client_sub(
            @RequestParam(name = "cnt_sub_id", required = false) Long cnt_sub_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelSubQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragentSubLink")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_sub_id)
                    ;
            DelSubQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_references_table")
    public JSONDatatable get_references_table(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if (cnt_id != null && cnt_id > 0) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientReferenceListRepository.queryClientMenuReferenceListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @GetMapping("/get_banks_list")
    public JSONDatatable get_banks_list(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        result.setData(banksListRepository.queryBanksListAll(user_List.get(0).id, user_Role_List.get(0).role_id));

        return result;
    }

    @PostMapping("/add_clients_references")
    public ModelAndView add_clients_references(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "bank_id", required = false) Long bank_id,
            @RequestParam(name = "cnt_ipn", required = false) Long cnt_ipn,
            @RequestParam(name = "cnt_iban", required = false) String cnt_iban,
            @RequestParam(name = "cnt_account", required = false) String cnt_account,
            @RequestParam(name = "cnt_ref_default", required = false) Long cnt_ref_default
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddReferenceQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragentReferences")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, bank_id)
                    .setParameter(5, cnt_ipn)
                    .setParameter(6, cnt_iban)
                    .setParameter(7, cnt_account)
                    .setParameter(8, cnt_ref_default)
                    ;
            AddReferenceQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_clients_references")
    public ModelAndView edit_clients_references(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "cnt_ref_id") Long cnt_ref_id,
            @RequestParam(name = "bank_id", required = false) Long bank_id,
            @RequestParam(name = "cnt_ipn", required = false) Long cnt_ipn,
            @RequestParam(name = "cnt_iban", required = false) String cnt_iban,
            @RequestParam(name = "cnt_account", required = false) String cnt_account,
            @RequestParam(name = "cnt_ref_default", required = false) Long cnt_ref_default
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditReferenceQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragentReferences")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, cnt_ref_id)
                    .setParameter(5, bank_id)
                    .setParameter(6, cnt_ipn)
                    .setParameter(7, cnt_iban)
                    .setParameter(8, cnt_account)
                    .setParameter(9, cnt_ref_default)
                    ;
            EditReferenceQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_clients_references")
    public ModelAndView del_clients_references(
            @RequestParam(name = "cnt_ref_id") Long cnt_ref_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelReferenceQuery = entityManager
                    .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragentReferences")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_ref_id)
                    ;
            DelReferenceQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get_references_detail")
    public JSONDatatable get_references_detail(
            @RequestParam(name="cnt_ref_id", defaultValue = "0") Long cnt_ref_id,
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_ref_id != null && cnt_ref_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0) {
            }
            else{
                result.setData(detailClientReferenceListRepository.queryClientDetailReferenceListByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, cnt_ref_id));
            }
        }
        return result;
    }

    @PostMapping("/get_wh_table")
    public JSONDatatable get_wh_table(
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientsWHListRepository.queryClientMenuWHListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @GetMapping("/get_wh_client_list")
    public JSONDatatable get_wh_client_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        result.setData(clientsWHListRepository.queryClientWHListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
        ));

        return result;
    }

    @PostMapping("/add_wh_client_list")
    public ModelAndView add_wh_client_list(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "wh_client_id") Long wh_client_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_AddWHClientToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, wh_client_id)
                    ;
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_wh_client_list")
    public ModelAndView del_wh_client_list(
            @RequestParam(name = "wh_client_link_id") Long wh_client_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_DelWHClientToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, wh_client_link_id);
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_wh_services_table")
    public JSONDatatable get_wh_services_table(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientsWHServicesListRepository.queryClientMenuWHServicesListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @GetMapping("/get_wh_services_types_list")
    public JSONDatatable get_wh_services_types_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        result.setData(clientsWHServicesTypesListRepository.queryClientWHServiceTypesListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
        ));

        return result;
    }

    @PostMapping("/add_wh_services_list")
    public ModelAndView add_wh_services_list(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "exch_ser_type_id") Long exch_ser_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_AddWHServiceLinkToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, exch_ser_type_id)
                    ;
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_wh_services_list")
    public ModelAndView del_wh_services_list(
            @RequestParam(name = "exch_ser_link_id") Long exch_ser_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_DelWHServiceLinkToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, exch_ser_link_id);
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_wh_parameters_table")
    public JSONDatatable get_wh_parameters_table(
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientsWHParametersListRepository.queryClientMenuWHParametersListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @GetMapping("/get_wh_parameters_types_list")
    public JSONDatatable get_wh_parameters_types_list(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id", defaultValue = "0") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        result.setData(clientsWHParametersTypesListRepository.queryClientWHParametersTypesListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
        ));

        return result;
    }

    @PostMapping("/edit_wh_parameters_list")
    public ModelAndView edit_wh_parameters_list(
            @RequestParam(name = "exch_param_link_id") Long exch_param_link_id,
            @RequestParam(name = "exch_param_link_value", required = false) String exch_param_link_value
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_EditWHParametersLinkToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, exch_param_link_id)
                    .setParameter(4, exch_param_link_value)
                    ;
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_wh_parameters_list")
    public ModelAndView del_wh_parameters_list(
            @RequestParam(name = "exch_param_link_id") Long exch_param_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_DelWHParametersLinkToCNT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, exch_param_link_id);
            DelQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}

package com.idltd.hydramob.controller.transports;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.directions.Direction;
import com.idltd.hydramob.entity.list.Trailers_Type_List;
import com.idltd.hydramob.entity.transports.*;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.clients.MenuClientContactsListRepository;
import com.idltd.hydramob.repository.list.Cars_Type_ListRepository;
import com.idltd.hydramob.repository.list.Cars_Weight_Type_ListRepository;
import com.idltd.hydramob.repository.list.TMS_Contract_Type_ListRepository;
import com.idltd.hydramob.repository.list.Trailers_Type_ListRepository;
import com.idltd.hydramob.repository.transports.*;
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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transports")
public class TransportsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuTransportsListRepository menuTransportsListRepository;
    private DetailTransportsListRepository detailTransportsListRepository;
    private TMS_Contract_Type_ListRepository tms_Contract_Type_ListRepository;

    private MenuTransportContactsListRepository menuTransportContactsListRepository;
    private DetailTransportsContactsListRepository detailTransportsContactsListRepository;

    private MenuClientContactsListRepository menuClientContactsListRepository;

    private MenuTransportServicesListRepository menuTransportServicesListRepository;
    private DetailTransportsServicesListRepository detailTransportsServicesListRepository;
    private Service_Type_ListRepository service_Type_ListRepository;

    private MenuTransportAddressListRepository menuTransportAddressListRepository;
    private Address_Type_ListRepository address_Type_ListRepository;
    private DetailTransportAddressListRepository detailTransportAddressListRepository;

    private Contragent_Status_ListRepository contragent_Status_ListRepository;
    private OwnerShip_Type_ListRepository ownerShip_Type_ListRepository;
    private Contragent_Source_ListRepository contragent_Source_ListRepository;
    private Country_ListRepository country_ListRepository;
    private Cars_Weight_Type_ListRepository cars_Weight_Type_ListRepository;

    private MenuTransportAutoListRepository menuTransportAutoListRepository;
    private DetailTransportAutoListRepository detailTransportAutoListRepository;
    private Cars_Type_ListRepository cars_Type_ListRepository;

    private MenuTransportTrailersListRepository menuTransportTrailersListRepository;
    private DetailTransportTrailersListRepository detailTransportTrailersListRepository;
    private Trailers_Type_ListRepository trailers_Type_ListRepository;

    private MenuTransportDriversListRepository menuTransportDriversListRepository;
    private DetailTransportDriversListRepository detailTransportDriversListRepository;

    private MenuTransportDocumentsListRepository menuTransportDocumentsListRepository;
    private DetailTransportDocumentsListRepository detailTransportDocumentsListRepository;

    private MenuTransportDocFilesListRepository menuTransportDocFilesListRepository;
    private DetailTransportDocFilesListRepository detailTransportDocFilesListRepository;
    private TransportDocFileRepository transportDocFileRepository;    

    private MenuTransportCountryListRepository menuTransportCountryListRepository;
    private DetailTransportsCountryListRepository detailTransportsCountryListRepository;

    public TransportsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuTransportsListRepository menuTransportsListRepository,
            DetailTransportsListRepository detailTransportsListRepository,
            MenuTransportContactsListRepository menuTransportContactsListRepository,
            DetailTransportsContactsListRepository detailTransportsContactsListRepository,
            TMS_Contract_Type_ListRepository tms_Contract_Type_ListRepository,

            MenuTransportServicesListRepository menuTransportServicesListRepository,
            DetailTransportsServicesListRepository detailTransportsServicesListRepository,
            Service_Type_ListRepository service_Type_ListRepository,

            MenuClientContactsListRepository menuClientContactsListRepository,

            MenuTransportAddressListRepository menuTransportAddressListRepository,
            Address_Type_ListRepository address_Type_ListRepository,
            DetailTransportAddressListRepository detailTransportAddressListRepository,

            Contragent_Status_ListRepository contragent_Status_ListRepository,
            OwnerShip_Type_ListRepository ownerShip_Type_ListRepository,
            Contragent_Source_ListRepository contragent_Source_ListRepository,
            Country_ListRepository country_ListRepository,

            MenuTransportAutoListRepository menuTransportAutoListRepository,
            DetailTransportAutoListRepository detailTransportAutoListRepository,
            Cars_Type_ListRepository cars_Type_ListRepository,
            Cars_Weight_Type_ListRepository cars_Weight_Type_ListRepository,

            MenuTransportTrailersListRepository menuTransportTrailersListRepository,
            DetailTransportTrailersListRepository detailTransportTrailersListRepository,
            Trailers_Type_ListRepository trailers_Type_ListRepository,

            MenuTransportDriversListRepository menuTransportDriversListRepository,
            DetailTransportDriversListRepository detailTransportDriversListRepository,

            MenuTransportDocumentsListRepository menuTransportDocumentsListRepository,
            DetailTransportDocumentsListRepository detailTransportDocumentsListRepository,

            MenuTransportDocFilesListRepository menuTransportDocFilesListRepository,
            DetailTransportDocFilesListRepository detailTransportDocFilesListRepository,
            TransportDocFileRepository transportDocFileRepository,

            MenuTransportCountryListRepository menuTransportCountryListRepository,
            DetailTransportsCountryListRepository detailTransportsCountryListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuTransportsListRepository = menuTransportsListRepository;
        this.detailTransportsListRepository = detailTransportsListRepository;
        this.tms_Contract_Type_ListRepository = tms_Contract_Type_ListRepository;

        this.menuTransportContactsListRepository = menuTransportContactsListRepository;
        this.detailTransportsContactsListRepository = detailTransportsContactsListRepository;

        this.menuTransportServicesListRepository = menuTransportServicesListRepository;
        this.detailTransportsServicesListRepository = detailTransportsServicesListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;

        this.menuClientContactsListRepository = menuClientContactsListRepository;

        this.menuTransportAddressListRepository = menuTransportAddressListRepository;
        this.address_Type_ListRepository = address_Type_ListRepository;
        this.detailTransportAddressListRepository = detailTransportAddressListRepository;

        this.contragent_Status_ListRepository = contragent_Status_ListRepository;
        this.ownerShip_Type_ListRepository = ownerShip_Type_ListRepository;
        this.contragent_Source_ListRepository = contragent_Source_ListRepository;
        this.country_ListRepository = country_ListRepository;

        this.menuTransportAutoListRepository = menuTransportAutoListRepository;
        this.detailTransportAutoListRepository = detailTransportAutoListRepository;
        this.cars_Type_ListRepository = cars_Type_ListRepository;
        this.cars_Weight_Type_ListRepository = cars_Weight_Type_ListRepository;

        this.menuTransportTrailersListRepository = menuTransportTrailersListRepository;
        this.detailTransportTrailersListRepository = detailTransportTrailersListRepository;
        this.trailers_Type_ListRepository = trailers_Type_ListRepository;

        this.menuTransportDriversListRepository = menuTransportDriversListRepository;
        this.detailTransportDriversListRepository = detailTransportDriversListRepository;

        this.menuTransportDocumentsListRepository = menuTransportDocumentsListRepository;
        this.detailTransportDocumentsListRepository = detailTransportDocumentsListRepository;

        this.menuTransportDocFilesListRepository = menuTransportDocFilesListRepository;
        this.detailTransportDocFilesListRepository = detailTransportDocFilesListRepository;
        this.transportDocFileRepository = transportDocFileRepository;

        this.menuTransportCountryListRepository = menuTransportCountryListRepository;
        this.detailTransportsCountryListRepository = detailTransportsCountryListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,
                              @RequestParam(name = "cnt_doc_id", required = false) Long cnt_doc_id,

                              @RequestParam(name = "req_country_id", required = false, defaultValue = "0") Long req_country_id,
                              @RequestParam(name = "req_address", required = false) String req_address,
                              @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                              @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                              @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                              @RequestParam(value="transports_list_table_order_column", required = false) Long order_column,
                              @RequestParam(value="transports_list_table_order_type", required = false) String order_type,
                              @RequestParam(value="transports_list_table_search", required = false) String table_search,
                              @RequestParam(value="transports_list_table_pagelen", required = false) Long pagelen,
                              @RequestParam(value="transports_list_table_page", required = false) Long page,

                              @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                              @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                              @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                              @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                              @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                              @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                              @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                              @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                              @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        model.addObject("cnt_id", cnt_id);
        model.addObject("cnt_doc_id", cnt_doc_id);
        List<Country_Full_List> country_List;

        model.addObject("req_country_id", req_country_id);
        country_List = country_ListRepository.queryAllTransportContryList();
        model.addObject("country_head_list", country_List);

        model.addObject("req_address", req_address);
        model.addObject("req_tir_check", req_tir_check);
        model.addObject("req_adr_check", req_adr_check);
        model.addObject("req_ekmt_check", req_ekmt_check);

        model.addObject("transports_list_table_order_column", order_column);
        model.addObject("transports_list_table_order_type", order_type);
        model.addObject("transports_list_table_search", table_search);
        model.addObject("transports_list_table_pagelen", pagelen);
        model.addObject("transports_list_table_page", page);

        model.addObject("clients_transports_collapse", clients_transports_collapse);
        model.addObject("clients_trailers_collapse", clients_trailers_collapse);
        model.addObject("clients_drivers_collapse", clients_drivers_collapse);
        model.addObject("clients_contacts_collapse", clients_contacts_collapse);
        model.addObject("clients_country_collapse", clients_country_collapse);
        model.addObject("clients_services_collapse", clients_services_collapse);
        model.addObject("clients_address_collapse", clients_address_collapse);
        model.addObject("clients_documents_collapse", clients_documents_collapse);
        model.addObject("clients_files_collapse", clients_files_collapse);

        model.setViewName("transports/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "req_country_id", required = false, defaultValue = "0") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuTransportsListRepository.querytransportMenuListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id, req_country_id, req_address, req_tir_check, req_adr_check, req_ekmt_check
        ));
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

    @GetMapping("/get_ownership")
    public JSONDatatable get_ownership(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="owt_id", required = false) Long owt_id
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

    @GetMapping("/get_contract_type")
    public JSONDatatable get_contract_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0) {
            result.setData(tms_Contract_Type_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_users")
    public JSONDatatable get_users(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0) {
            result.setData(user_ListRepository.queryUserAll());
        }
        return result;
    }

    @GetMapping("/get_contragent_source")
    public JSONDatatable get_contragent_source(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0) {
            result.setData(contragent_Source_ListRepository.queryTransportContragentSource());
        }
        return result;
    }

    @GetMapping("/get_contragent_status")
    public JSONDatatable get_contragent_status(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cs_id") Long cs_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0 && cs_id != null) {
            result.setData(contragent_Status_ListRepository.queryGetCurrStatus(cs_id));
        }
        return result;
    }

    @GetMapping("/get_contragent_detail")
    public JSONDatatable get_contragent_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cnt_id") Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode != null && mode >= 0) {
            result.setData(detailTransportsListRepository.queryTransportListDetaulByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/add_transport")
    public ModelAndView del_country_airport(
            @RequestParam(name = "cnt_name") String cnt_name,
            @RequestParam(name = "cnt_identifycode") String cnt_identifycode,
            @RequestParam(name = "owt_id", defaultValue = "0", required = false) Long owt_id,
            @RequestParam(name = "cs_id", defaultValue = "0", required = false) Long cs_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Long cnt_resident,
            @RequestParam(name = "cnt_source_id", defaultValue = "0", required = false) Long cnt_source_id,
            @RequestParam(name = "country_id", defaultValue = "0", required = false) Long country_id,
            @RequestParam(name = "tms_contt_id", defaultValue = "0", required = false) Long tms_contt_id,
            @RequestParam(name = "tms_contt_num", required = false) String tms_contt_num,
            @RequestParam(name = "tms_contt_date", required = false) String tms_contt_date,
            @RequestParam(name = "tms_lic_num", required = false) String tms_lic_num,
            @RequestParam(name = "tms_lic_date", required = false) String tms_lic_date,
            @RequestParam(name = "tms_ins_num", required = false) String tms_ins_num,
            @RequestParam(name = "tms_ins_date", required = false) String tms_ins_date,
            @RequestParam(name = "tms_contt_check", required = false) Long tms_contt_check,
            @RequestParam(name = "tms_contt_count", defaultValue = "0", required = false) Long tms_contt_count,
            @RequestParam(name = "tms_tir_check", defaultValue = "0", required = false) Long tms_tir_check,
            @RequestParam(name = "tms_adr_check", defaultValue = "0", required = false) Long tms_adr_check,
            @RequestParam(name = "tms_ekmt_check", defaultValue = "0", required = false) Long tms_ekmt_check,

            @RequestParam(name = "trans_id", defaultValue = "0", required = false) Long trans_id,
            @RequestParam(name = "ship_id", defaultValue = "0", required = false) Long ship_id,
            @RequestParam(name = "air_id", defaultValue = "0", required = false) Long air_id,
            @RequestParam(name = "consegnee_id", defaultValue = "0", required = false) Long consegnee_id,
            @RequestParam(name = "sea_agent_id", defaultValue = "0", required = false) Long sea_agent_id,
            @RequestParam(name = "trecking_url", required = false) String trecking_url,
            @RequestParam(name = "shipper_id", defaultValue = "0", required = false) Long shipper_id,
            @RequestParam(name = "cnt_name_eng", required = false) String cnt_name_eng
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TRANSPORT.pr_AddTransport")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(31, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(32, String.class, ParameterMode.OUT)
                    .setParameter(1, cnt_name)
                    .setParameter(2, cnt_identifycode)
                    .setParameter(3, owt_id)
                    .setParameter(4, cs_id)
                    .setParameter(5, cnt_note)
                    .setParameter(6, cnt_resident)
                    .setParameter(7, user_List.get(0).id)
                    .setParameter(8, user_Role_List.get(0).role_id)
                    .setParameter(9, cnt_source_id)
                    .setParameter(10, country_id)
                    .setParameter(11, tms_contt_id)
                    .setParameter(12, tms_contt_num)
                    .setParameter(13, tms_contt_date)
                    .setParameter(14, tms_lic_num)
                    .setParameter(15, tms_lic_date)
                    .setParameter(16, tms_ins_num)
                    .setParameter(17, tms_ins_date)
                    .setParameter(18, tms_contt_check)
                    .setParameter(19, tms_contt_count)
                    .setParameter(20, tms_tir_check)
                    .setParameter(21, tms_adr_check)
                    .setParameter(22, tms_ekmt_check)
                    .setParameter(23, trans_id)
                    .setParameter(24, sea_agent_id)
                    .setParameter(25, air_id)
                    .setParameter(26, ship_id)
                    .setParameter(27, consegnee_id)
                    .setParameter(28, trecking_url)
                    .setParameter(29, shipper_id)
                    .setParameter(30, cnt_name_eng)
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_transport")
    public ModelAndView edit_transport(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "cnt_name") String cnt_name,
            @RequestParam(name = "cnt_identifycode") String cnt_identifycode,
            @RequestParam(name = "owt_id", defaultValue = "0", required = false) Long owt_id,
            @RequestParam(name = "cs_id", defaultValue = "0", required = false) Long cs_id,
            @RequestParam(name = "cnt_note", required = false) String cnt_note,
            @RequestParam(name = "cnt_resident", required = false) Long cnt_resident,
            @RequestParam(name = "cnt_source_id", defaultValue = "0", required = false) Long cnt_source_id,
            @RequestParam(name = "country_id", defaultValue = "0", required = false) Long country_id,
            @RequestParam(name = "tms_contt_id", defaultValue = "0", required = false) Long tms_contt_id,
            @RequestParam(name = "tms_contt_num", required = false) String tms_contt_num,
            @RequestParam(name = "tms_contt_date", required = false) String tms_contt_date,
            @RequestParam(name = "tms_lic_num", required = false) String tms_lic_num,
            @RequestParam(name = "tms_lic_date", required = false) String tms_lic_date,
            @RequestParam(name = "tms_ins_num", required = false) String tms_ins_num,
            @RequestParam(name = "tms_ins_date", required = false) String tms_ins_date,
            @RequestParam(name = "tms_contt_check", required = false) Long tms_contt_check,
            @RequestParam(name = "tms_contt_count", defaultValue = "0", required = false) Long tms_contt_count,
            @RequestParam(name = "tms_tir_check", defaultValue = "0", required = false) Long tms_tir_check,
            @RequestParam(name = "tms_adr_check", defaultValue = "0", required = false) Long tms_adr_check,
            @RequestParam(name = "tms_ekmt_check", defaultValue = "0", required = false) Long tms_ekmt_check,

            @RequestParam(name = "trans_id", defaultValue = "0", required = false) Long trans_id,
            @RequestParam(name = "ship_id", defaultValue = "0", required = false) Long ship_id,
            @RequestParam(name = "air_id", defaultValue = "0", required = false) Long air_id,
            @RequestParam(name = "consegnee_id", defaultValue = "0", required = false) Long consegnee_id,
            @RequestParam(name = "sea_agent_id", defaultValue = "0", required = false) Long sea_agent_id,
            @RequestParam(name = "trecking_url", required = false) String trecking_url,
            @RequestParam(name = "shipper_id", defaultValue = "0", required = false) Long shipper_id,
            @RequestParam(name = "cnt_name_eng", required = false) String cnt_name_eng
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TRANSPORT.pr_EditTransport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(18, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(19, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(20, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(21, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(22, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(23, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(24, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(25, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(26, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(27, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(28, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(29, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(30, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(31, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(32, String.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, cnt_id)
                    .setParameter(3, cnt_name)
                    .setParameter(4, cnt_identifycode)
                    .setParameter(5, owt_id)
                    .setParameter(6, cs_id)
                    .setParameter(7, cnt_note)
                    .setParameter(8, cnt_resident)
                    .setParameter(9, cnt_source_id)
                    .setParameter(10, country_id)
                    .setParameter(11, tms_contt_id)
                    .setParameter(12, tms_contt_num)
                    .setParameter(13, tms_contt_date)
                    .setParameter(14, tms_lic_num)
                    .setParameter(15, tms_lic_date)
                    .setParameter(16, tms_ins_num)
                    .setParameter(17, tms_ins_date)
                    .setParameter(18, tms_contt_check)
                    .setParameter(19, tms_contt_count)
                    .setParameter(20, tms_tir_check)
                    .setParameter(21, tms_adr_check)
                    .setParameter(22, tms_ekmt_check)
                    .setParameter(23, trans_id)
                    .setParameter(24, sea_agent_id)
                    .setParameter(25, air_id)
                    .setParameter(26, ship_id)
                    .setParameter(27, consegnee_id)
                    .setParameter(28, trecking_url)
                    .setParameter(29, shipper_id)
                    .setParameter(30, cnt_name_eng)
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_transport")
    public ModelAndView del_transport(
            @RequestParam(name = "cnt_id") Long cnt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TRANSPORT.pr_AdminDelTransport")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, cnt_id)
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PersistenceContext
    private EntityManager entityManager;

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
    public ModelAndView contacts_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="cnt_id") Long cnt_id,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="cc_id") Long cc_id,

                                        @RequestParam(name = "req_country_id") Long req_country_id,
                                        @RequestParam(name = "req_address", required = false) String req_address,
                                        @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                        @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                        @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                        @RequestParam(value="transports_contacts_table_order_column") Long order_column,
                                        @RequestParam(value="transports_contacts_table_order_type") String order_type,
                                        @RequestParam(value="transports_contacts_table_search") String table_search,
                                        @RequestParam(value="transports_contacts_table_pagelen") Long pagelen,
                                        @RequestParam(value="transports_contacts_table_page") Long page,

                                        @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                        @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                        @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                        @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                        @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                        @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                        @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                        @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                        @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                        @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                        @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                        @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                        @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                        @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<Direction> directionDetail;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailTransportContactsList> detailTransportsContactsList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);
        }
        else{
            detailTransportsContactsList = detailTransportsContactsListRepository.queryTransportContactsListDetaulByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, cc_id);

            mav.addObject("cnt_id", detailTransportsContactsList.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportsContactsList.get(0).cnt_name);

            mav.addObject("cc_id", detailTransportsContactsList.get(0).cc_id);

            mav.addObject("cc_person", detailTransportsContactsList.get(0).cc_person);

            mav.addObject("cc_position", detailTransportsContactsList.get(0).cc_position);

            mav.addObject("cc_main_phone", detailTransportsContactsList.get(0).cc_main_phone);
            if(detailTransportsContactsList.get(0).cc_main_phone != null && detailTransportsContactsList.get(0).cc_main_phone != "") {
                mav.addObject("cc_main_phone_link", "tel:" + detailTransportsContactsList.get(0).cc_main_phone);
            }

            mav.addObject("cc_second_phone", detailTransportsContactsList.get(0).cc_second_phone);
            if(detailTransportsContactsList.get(0).cc_second_phone != null && detailTransportsContactsList.get(0).cc_second_phone != "") {
                mav.addObject("cc_second_phone_link", "tel:" + detailTransportsContactsList.get(0).cc_second_phone);
            }

            mav.addObject("cc_main_office_phone", detailTransportsContactsList.get(0).cc_main_office_phone);
            if(detailTransportsContactsList.get(0).cc_main_office_phone != null && detailTransportsContactsList.get(0).cc_main_office_phone != "") {
                mav.addObject("cc_main_office_phone_link", "tel:" + detailTransportsContactsList.get(0).cc_main_office_phone);
            }

            mav.addObject("cc_second_office_phone", detailTransportsContactsList.get(0).cc_second_office_phone);
            if(detailTransportsContactsList.get(0).cc_second_office_phone != null && detailTransportsContactsList.get(0).cc_second_office_phone != "") {
                mav.addObject("cc_second_office_phone_link", "tel:" + detailTransportsContactsList.get(0).cc_second_office_phone);
            }

            mav.addObject("cc_email", detailTransportsContactsList.get(0).cc_email);
            if(detailTransportsContactsList.get(0).cc_email != null && detailTransportsContactsList.get(0).cc_email != "") {
                mav.addObject("cc_email_link", "mailto:" + detailTransportsContactsList.get(0).cc_email);
            }

            mav.addObject("cc_skype", detailTransportsContactsList.get(0).cc_skype);
            if(detailTransportsContactsList.get(0).cc_skype != null && detailTransportsContactsList.get(0).cc_skype != "") {
                mav.addObject("cc_skype_link", "skype:" + detailTransportsContactsList.get(0).cc_skype);
            }

            mav.addObject("cc_note", detailTransportsContactsList.get(0).cc_note);
        }


        mav.addObject("transports_contacts_table_order_column", order_column);
        mav.addObject("transports_contacts_table_order_type", order_type);
        mav.addObject("transports_contacts_table_search", table_search);
        mav.addObject("transports_contacts_table_pagelen", pagelen);
        mav.addObject("transports_contacts_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/contacts_detail");
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

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value="transports_contacts_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_contacts_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_contacts_table_search", required = false) String table_search,
            @RequestParam(value="transports_contacts_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_contacts_table_page", required = false) Long page,

            @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
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

        mav.addObject("transports_contacts_table_order_column", order_column);
        mav.addObject("transports_contacts_table_order_type", order_type);
        mav.addObject("transports_contacts_table_search", table_search);
        mav.addObject("transports_contacts_table_pagelen", pagelen);
        mav.addObject("transports_contacts_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/contacts_detail");
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

            result.setData(menuTransportServicesListRepository.queryTransportServicesMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/services_detail")
    public ModelAndView services_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="cnt_id") Long cnt_id,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="cntserl_id") Long cntserl_id,

                                        @RequestParam(name = "req_country_id") Long req_country_id,
                                        @RequestParam(name = "req_address", required = false) String req_address,
                                        @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                        @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                        @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                        @RequestParam(value="transports_services_table_order_column") Long order_column,
                                        @RequestParam(value="transports_services_table_order_type") String order_type,
                                        @RequestParam(value="transports_services_table_search") String table_search,
                                        @RequestParam(value="transports_services_table_pagelen") Long pagelen,
                                        @RequestParam(value="transports_services_table_page") Long page,

                                        @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                        @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                        @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                        @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                        @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                        @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                        @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                        @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                        @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                        @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                        @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                        @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                        @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                        @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Service_Type_List> service_Type_List;
        List<DetailTransportServicesList> detailTransportsServicesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            if(user_Role_List.get(0).role_id == 1 || user_Role_List.get(0).role_id == 7 || user_Role_List.get(0).role_id == 8) {
                service_Type_List = service_Type_ListRepository.queryNewSerIDByCnt(cnt_id);
            }
            else{
                service_Type_List = service_Type_ListRepository.queryNewOPSSerIDByUserID(user_List.get(0).id, cnt_id);
            }
            mav.addObject("ser_list", service_Type_List);
        }
        else{
            detailTransportsServicesList = detailTransportsServicesListRepository.queryTransportServicesListDetaulByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cntserl_id);

            mav.addObject("cnt_id", detailTransportsServicesList.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportsServicesList.get(0).cnt_name);

            mav.addObject("cntserl_id", detailTransportsServicesList.get(0).cntserl_id);
            mav.addObject("cntserl_date", detailTransportsServicesList.get(0).cntserl_date);

            mav.addObject("ser_id", detailTransportsServicesList.get(0).ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(detailTransportsServicesList.get(0).ser_id);
            mav.addObject("ser_list", service_Type_List);
        }

        mav.addObject("req_country_id", req_country_id);

        mav.addObject("transports_services_table_order_column", order_column);
        mav.addObject("transports_services_table_order_type", order_type);
        mav.addObject("transports_services_table_search", table_search);
        mav.addObject("transports_services_table_pagelen", pagelen);
        mav.addObject("transports_services_table_page", page);

        mav.setViewName("/transports/services_detail");
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

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value="transports_services_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_services_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_services_table_search", required = false) String table_search,
            @RequestParam(value="transports_services_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_services_table_page", required = false) Long page,

            @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        List<Service_Type_List> service_Type_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

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
            if(user_Role_List.get(0).role_id == 1 || user_Role_List.get(0).role_id == 7 || user_Role_List.get(0).role_id == 8) {
                service_Type_List = service_Type_ListRepository.queryNewSerIDByCnt(cnt_id);
            }
            else{
                service_Type_List = service_Type_ListRepository.queryNewOPSSerIDByUserID(user_List.get(0).id, cnt_id);
            }
            mav.addObject("ser_list", service_Type_List);
        }
        else{
            mav.addObject("ser_id", ser_id);
            service_Type_List = service_Type_ListRepository.queryGetByID(ser_id);
            mav.addObject("ser_list", service_Type_List);
        }

        mav.addObject("transports_services_table_order_column", order_column);
        mav.addObject("transports_services_table_order_type", order_type);
        mav.addObject("transports_services_table_search", table_search);
        mav.addObject("transports_services_table_pagelen", pagelen);
        mav.addObject("transports_services_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/services_detail");
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

            result.setData(menuTransportAddressListRepository.queryTransportAddressMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/address_detail")
    public ModelAndView address_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="cnt_id") Long cnt_id,
                                       @RequestParam(value="cnt_name") String cnt_name,
                                       @RequestParam(value="cnt_add_id") Long cnt_add_id,

                                       @RequestParam(name = "req_country_id") Long req_country_id,
                                       @RequestParam(name = "req_address", required = false) String req_address,
                                       @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                       @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                       @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                       @RequestParam(value="transports_address_table_order_column") Long order_column,
                                       @RequestParam(value="transports_address_table_order_type") String order_type,
                                       @RequestParam(value="transports_address_table_search") String table_search,
                                       @RequestParam(value="transports_address_table_pagelen") Long pagelen,
                                       @RequestParam(value="transports_address_table_page") Long page,

                                       @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                       @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                       @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                       @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                       @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                       @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                       @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                       @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                       @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                       @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                       @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                       @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                       @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                       @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Address_Type_List> address_Type_List;
        List<DetailTransportAddressList> detailTransportAddressList;

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
            detailTransportAddressList = detailTransportAddressListRepository.queryTransportAddressDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_add_id);

            mav.addObject("cnt_id", detailTransportAddressList.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportAddressList.get(0).cnt_name);

            mav.addObject("cnt_add_id", detailTransportAddressList.get(0).cnt_add_id);

            mav.addObject("add_type_id", detailTransportAddressList.get(0).add_type_id);
            address_Type_List = address_Type_ListRepository.queryClientAddressTypeByID(detailTransportAddressList.get(0).add_type_id);
            mav.addObject("add_type_list", address_Type_List);

            mav.addObject("cnt_add_value", detailTransportAddressList.get(0).cnt_add_value);

            mav.addObject("cnt_add_note", detailTransportAddressList.get(0).cnt_add_note);
        }

        mav.addObject("transports_address_table_order_column", order_column);
        mav.addObject("transports_address_table_order_type", order_type);
        mav.addObject("transports_address_table_search", table_search);
        mav.addObject("transports_address_table_pagelen", pagelen);
        mav.addObject("transports_address_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/address_detail");
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

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value="transports_address_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_address_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_address_table_search", required = false) String table_search,
            @RequestParam(value="transports_address_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_address_table_page", required = false) Long page,

            @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
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

        mav.addObject("transports_address_table_order_column", order_column);
        mav.addObject("transports_address_table_order_type", order_type);
        mav.addObject("transports_address_table_search", table_search);
        mav.addObject("transports_address_table_pagelen", pagelen);
        mav.addObject("transports_address_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/address_detail");
        return mav;
    }

    @PostMapping("/get_auto_table")
    public JSONDatatable get_auto_table(
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

            result.setData(menuTransportAutoListRepository.querytransportAutoMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @GetMapping("/get_auto_type")
    public JSONDatatable get_auto_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0) {
            result.setData(cars_Type_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_auto_weight_type")
    public JSONDatatable get_auto_weight_type(
            @RequestParam(value="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode != null && mode >= 0) {
            result.setData(cars_Weight_Type_ListRepository.findAll());
        }
        return result;
    }

    @GetMapping("/get_auto_detail")
    public JSONDatatable get_auto_detail(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="cntc_id") Long cntc_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode != null && mode >= 0 && cntc_id > 0) {
            result.setData(detailTransportAutoListRepository.querytransportAutoDetailListByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, cntc_id
            ));
        }
        return result;
    }

    @PostMapping("/add_auto")
    public ModelAndView add_auto(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "cntc_type_id", required = false) Long cntc_type_id,
            @RequestParam(name = "cntc_type_brand", required = false) String cntc_type_brand,
            @RequestParam(name = "cntc_type_number", required = false) String cntc_type_number,
            @RequestParam(name = "cntc_weight_type_id", required = false, defaultValue ="0") Long cntc_weight_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_AddClientCar")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cnt_id)
                    .setParameter(4, cntc_type_id)
                    .setParameter(5, cntc_type_brand)
                    .setParameter(6, cntc_type_number)
                    .setParameter(7, cntc_weight_type_id)
                    ;
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_auto")
    public ModelAndView edit_auto(
            @RequestParam(name = "cntc_id") Long cntc_id,
            @RequestParam(name = "cntc_type_id", required = false) Long cntc_type_id,
            @RequestParam(name = "cntc_type_brand", required = false) String cntc_type_brand,
            @RequestParam(name = "cntc_type_number", required = false) String cntc_type_number,
            @RequestParam(name = "cntc_weight_type_id", required = false, defaultValue ="0") Long cntc_weight_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_EditClientCar")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cntc_id)
                    .setParameter(4, cntc_type_id)
                    .setParameter(5, cntc_type_brand)
                    .setParameter(6, cntc_type_number)
                    .setParameter(7, cntc_weight_type_id)
                    ;
            ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_auto")
    public ModelAndView del_auto(
            @RequestParam(name = "cntc_id") Long cntc_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_TMS.pr_DelClientCar")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, cntc_id)
                    ;
            ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_trailers_table")
    public JSONDatatable get_trailers_table(
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

            result.setData(menuTransportTrailersListRepository.querytransportTrailersMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @RequestMapping("/trailers_detail")
    public ModelAndView trailers_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="cnt_id") Long cnt_id,
                                        @RequestParam(value="cnt_name") String cnt_name,
                                        @RequestParam(value="cntt_id") Long cntt_id,

                                        @RequestParam(name = "req_country_id") Long req_country_id,
                                        @RequestParam(name = "req_address", required = false) String req_address,
                                        @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                        @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                        @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                        @RequestParam(value="transports_trailers_table_order_column") Long order_column,
                                        @RequestParam(value="transports_trailers_table_order_type") String order_type,
                                        @RequestParam(value="transports_trailers_table_search") String table_search,
                                        @RequestParam(value="transports_trailers_table_pagelen") Long pagelen,
                                        @RequestParam(value="transports_trailers_table_page") Long page,

                                        @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                        @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                        @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                        @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                        @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                        @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                        @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                        @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                        @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                        @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                        @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                        @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                        @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                        @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        List<Trailers_Type_List> trailers_Type_List;
        List<DetailTransportTrailersList> detailTransportTrainersList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            trailers_Type_List = (List<Trailers_Type_List>) trailers_Type_ListRepository.findAll();
            mav.addObject("cntt_type_list", trailers_Type_List);
        }
        else{
            detailTransportTrainersList = detailTransportTrailersListRepository.querytransportTrailersDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cntt_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("cntt_id", detailTransportTrainersList.get(0).cntt_id);

            mav.addObject("cntt_type_id", detailTransportTrainersList.get(0).cntt_type_id);
            trailers_Type_List = (List<Trailers_Type_List>) trailers_Type_ListRepository.findAll();
            mav.addObject("cntt_type_list", trailers_Type_List);

            mav.addObject("cntt_type_brand", detailTransportTrainersList.get(0).cntt_type_brand);

            mav.addObject("cntt_type_number", detailTransportTrainersList.get(0).cntt_type_number);
        }

        mav.addObject("transports_trailers_table_order_column", order_column);
        mav.addObject("transports_trailers_table_order_type", order_type);
        mav.addObject("transports_trailers_table_search", table_search);
        mav.addObject("transports_trailers_table_pagelen", pagelen);
        mav.addObject("transports_trailers_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/trailers_detail");
        return mav;
    }

    @PostMapping("/trailers_model")
    public ModelAndView trailers_model(
                        @RequestParam(value="mode") Long mode,
                        @RequestParam(name = "cnt_id", required = false) Long cnt_id,
                        @RequestParam(name = "cnt_name", required = false) String cnt_name,

                        @RequestParam(name = "cntt_id", required = false) Long cntt_id,
                        @RequestParam(name = "cntt_type_id", required = false) Long cntt_type_id,
                        @RequestParam(name = "cntt_type_brand", required = false) String cntt_type_brand,
                        @RequestParam(name = "cntt_type_number", required = false) String cntt_type_number,

                        @RequestParam(name = "req_country_id") Long req_country_id,
                        @RequestParam(name = "req_address", required = false) String req_address,
                        @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                        @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                        @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                        @RequestParam(value="transports_trailers_table_order_column", required = false) Long order_column,
                        @RequestParam(value="transports_trailers_table_order_type", required = false) String order_type,
                        @RequestParam(value="transports_trailers_table_search", required = false) String table_search,
                        @RequestParam(value="transports_trailers_table_pagelen", required = false) Long pagelen,
                        @RequestParam(value="transports_trailers_table_page", required = false) Long page,

                        @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                        @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                        @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                        @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                        @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                        @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                        @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                        @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                        @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                        @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                        @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                        @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                        @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                        @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<Trailers_Type_List> trailers_Type_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTransportTrailersQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddClientTrailer")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cnt_id)
                            .setParameter(4, cntt_type_id)
                            .setParameter(5, cntt_type_brand)
                            .setParameter(6, cntt_type_number);
                    AddTransportTrailersQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTransportTrailersQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditClientTrailer")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cntt_id)
                            .setParameter(4, cntt_type_id)
                            .setParameter(5, cntt_type_brand)
                            .setParameter(6, cntt_type_number);
                    EditTransportTrailersQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTransportTrailersQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelClientTrailer")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cntt_id);
                    DelTransportTrailersQuery.execute();
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

        mav.addObject("cntt_id", cntt_id);

        if(mode == 0){
            mav.addObject("cntt_type_id", cntt_type_id);
            trailers_Type_List = (List<Trailers_Type_List>) trailers_Type_ListRepository.findAll();
            mav.addObject("cntt_type_list", trailers_Type_List);
        }
        else{
            mav.addObject("cntt_type_id", cntt_type_id);
            trailers_Type_List = trailers_Type_ListRepository.queryClientTrailersTypeByID(cntt_type_id);
            mav.addObject("cntt_type_list", trailers_Type_List);
        }

        mav.addObject("cntt_type_brand", cntt_type_brand);

        mav.addObject("cntt_type_number", cntt_type_number);

        mav.addObject("transports_trailers_table_order_column", order_column);
        mav.addObject("transports_trailers_table_order_type", order_type);
        mav.addObject("transports_trailers_table_search", table_search);
        mav.addObject("transports_trailers_table_pagelen", pagelen);
        mav.addObject("transports_trailers_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/trailers_detail");
        return mav;
    }

    @PostMapping("/get_drivers_table")
    public JSONDatatable get_drivers_table(
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

            result.setData(menuTransportDriversListRepository.querytransportDriversMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/drivers_detail")
    public ModelAndView drivers_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="cnt_id") Long cnt_id,
                                       @RequestParam(value="cnt_name") String cnt_name,
                                       @RequestParam(value="cntd_id") Long cntd_id,

                                       @RequestParam(name = "req_country_id") Long req_country_id,
                                       @RequestParam(name = "req_address", required = false) String req_address,
                                       @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                       @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                       @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                       @RequestParam(value="transports_drivers_table_order_column") Long order_column,
                                       @RequestParam(value="transports_drivers_table_order_type") String order_type,
                                       @RequestParam(value="transports_drivers_table_search") String table_search,
                                       @RequestParam(value="transports_drivers_table_pagelen") Long pagelen,
                                       @RequestParam(value="transports_drivers_table_page") Long page,

                                       @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                       @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                       @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                       @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                       @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                       @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                       @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                       @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                       @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                       @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                       @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                       @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                       @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                       @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        List<DetailTransportDriversList> detailTransportDriversList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);
        }
        else{
            detailTransportDriversList = detailTransportDriversListRepository.querytransportDriversDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cntd_id);

            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            mav.addObject("cntd_id", detailTransportDriversList.get(0).cntd_id);
            mav.addObject("cntd_name", detailTransportDriversList.get(0).cntd_name);
            mav.addObject("cntd_name_eng", detailTransportDriversList.get(0).cntd_name_eng);
            mav.addObject("cntd_name_doc", detailTransportDriversList.get(0).cntd_name_doc);
            mav.addObject("cntd_name_lisence", detailTransportDriversList.get(0).cntd_name_lisence);
            mav.addObject("cntd_main_phone", detailTransportDriversList.get(0).cntd_main_phone);
            mav.addObject("cntd_second_phone", detailTransportDriversList.get(0).cntd_second_phone);
            mav.addObject("cntd_second_email", detailTransportDriversList.get(0).cntd_second_email);
        }

        mav.addObject("transports_drivers_table_order_column", order_column);
        mav.addObject("transports_drivers_table_order_type", order_type);
        mav.addObject("transports_drivers_table_search", table_search);
        mav.addObject("transports_drivers_table_pagelen", pagelen);
        mav.addObject("transports_drivers_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/drivers_detail");
        return mav;
    }

    @PostMapping("/drivers_model")
    public ModelAndView drivers_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,

            @RequestParam(name = "cntd_id", required = false) Long cntd_id,
            @RequestParam(name = "cntd_name", required = false) String cntd_name,
            @RequestParam(name = "cntd_name_eng", required = false) String cntd_name_eng,
            @RequestParam(name = "cntd_name_doc", required = false) String cntd_name_doc,
            @RequestParam(name = "cntd_name_lisence", required = false) String cntd_name_lisence,
            @RequestParam(name = "cntd_main_phone", required = false) String cntd_main_phone,
            @RequestParam(name = "cntd_second_phone", required = false) String cntd_second_phone,
            @RequestParam(name = "cntd_second_email", required = false) String cntd_second_email,

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value="transports_drivers_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_drivers_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_drivers_table_search", required = false) String table_search,
            @RequestParam(value="transports_drivers_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_drivers_table_page", required = false) Long page,

            @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddTransportDriversQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_AddClientDriver")
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
                            .setParameter(3, cnt_id)
                            .setParameter(4, cntd_name)
                            .setParameter(5, cntd_name_eng)
                            .setParameter(6, cntd_name_doc)
                            .setParameter(7, cntd_name_lisence)
                            .setParameter(8, cntd_main_phone)
                            .setParameter(9, cntd_second_phone)
                            .setParameter(10, cntd_second_email);
                    AddTransportDriversQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditTransportDriversQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_EditClientDriver")
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
                            .setParameter(3, cntd_id)
                            .setParameter(4, cntd_name)
                            .setParameter(5, cntd_name_eng)
                            .setParameter(6, cntd_name_doc)
                            .setParameter(7, cntd_name_lisence)
                            .setParameter(8, cntd_main_phone)
                            .setParameter(9, cntd_second_phone)
                            .setParameter(10, cntd_second_email);
                    EditTransportDriversQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelTransportDriversQuery = entityManager
                            .createStoredProcedureQuery("PKG_TMS.pr_DelClientDriver")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cntd_id);
                    DelTransportDriversQuery.execute();
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

        mav.addObject("cntd_id", cntd_id);
        mav.addObject("cntd_name", cntd_name);
        mav.addObject("cntd_name_eng", cntd_name_eng);
        mav.addObject("cntd_name_doc", cntd_name_doc);
        mav.addObject("cntd_name_lisence", cntd_name_lisence);
        mav.addObject("cntd_main_phone", cntd_main_phone);
        mav.addObject("cntd_second_phone", cntd_second_phone);
        mav.addObject("cntd_second_email", cntd_second_email);

        mav.addObject("transports_drivers_table_order_column", order_column);
        mav.addObject("transports_drivers_table_order_type", order_type);
        mav.addObject("transports_drivers_table_search", table_search);
        mav.addObject("transports_drivers_table_pagelen", pagelen);
        mav.addObject("transports_drivers_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/drivers_detail");
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

            result.setData(menuTransportDocumentsListRepository.queryTransportDocumentsMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/documents_detail")
    public ModelAndView documents_detail(@RequestParam(value="mode") Long mode,
                                         @RequestParam(value="cnt_id") Long cnt_id,
                                         @RequestParam(value="cnt_name") String cnt_name,
                                         @RequestParam(value="cnt_doc_id") Long cnt_doc_id,

                                         @RequestParam(value="transports_documents_table_order_column") Long order_column,
                                         @RequestParam(value="transports_documents_table_order_type") String order_type,
                                         @RequestParam(value="transports_documents_table_search") String table_search,
                                         @RequestParam(value="transports_documents_table_pagelen") Long pagelen,
                                         @RequestParam(value="transports_documents_table_page") Long page,

                                         @RequestParam(name = "req_country_id") Long req_country_id,
                                         @RequestParam(name = "req_address", required = false) String req_address,
                                         @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                         @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                         @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                         @RequestParam(value = "transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                         @RequestParam(value = "transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                         @RequestParam(value = "transports_list_table_search", required = false) String transports_list_table_search,
                                         @RequestParam(value = "transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                         @RequestParam(value = "transports_list_table_page", required = false) Long transports_list_table_page,

                                         @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                         @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                         @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                         @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                         @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                         @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                         @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                         @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                         @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailTransportDocumentsList> detailTransportDocumentsList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);
        }
        else{
            detailTransportDocumentsList = detailTransportDocumentsListRepository.queryTransportDocumentsDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_doc_id);

            mav.addObject("cnt_id", detailTransportDocumentsList.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportDocumentsList.get(0).cnt_name);

            mav.addObject("cnt_doc_id", detailTransportDocumentsList.get(0).cnt_doc_id);
            mav.addObject("cnt_doc_num", detailTransportDocumentsList.get(0).cnt_doc_num);

            mav.addObject("cnt_doc_start_date", detailTransportDocumentsList.get(0).cnt_doc_start_date );

            mav.addObject("cnt_doc_end_date", detailTransportDocumentsList.get(0).cnt_doc_end_date);

            mav.addObject("cnt_doc_note", detailTransportDocumentsList.get(0).cnt_doc_note);
        }

        mav.addObject("transports_documents_table_order_column", order_column);
        mav.addObject("transports_documents_table_order_type", order_type);
        mav.addObject("transports_documents_table_search", table_search);
        mav.addObject("transports_documents_table_pagelen", pagelen);
        mav.addObject("transports_documents_table_page", page);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/documents_detail");
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

            @RequestParam(value="transports_documents_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_documents_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_documents_table_search", required = false) String table_search,
            @RequestParam(value="transports_documents_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_documents_table_page", required = false) Long page,

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value = "transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value = "transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value = "transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value = "transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value = "transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
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
                            .setParameter(7, cnt_doc_note);
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
                            .setParameter(8, cnt_doc_note);
                    EditQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContDoc")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, cnt_id)
                            .setParameter(4, cnt_doc_id);
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

        mav.addObject("transports_documents_table_order_column", order_column);
        mav.addObject("transports_documents_table_order_type", order_type);
        mav.addObject("transports_documents_table_search", table_search);
        mav.addObject("transports_documents_table_pagelen", pagelen);
        mav.addObject("transports_documents_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/documents_detail");
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

            result.setData(menuTransportDocFilesListRepository.queryTransportDocFilesMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, cnt_doc_id));
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

                                     @RequestParam(value="transports_files_table_order_column") Long order_column,
                                     @RequestParam(value="transports_files_table_order_type") String order_type,
                                     @RequestParam(value="transports_files_table_search") String table_search,
                                     @RequestParam(value="transports_files_table_pagelen") Long pagelen,
                                     @RequestParam(value="transports_files_table_page") Long page,

                                     @RequestParam(name = "req_country_id") Long req_country_id,
                                     @RequestParam(name = "req_address", required = false) String req_address,
                                     @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                     @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                     @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                     @RequestParam(value = "transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                     @RequestParam(value = "transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                     @RequestParam(value = "transports_list_table_search", required = false) String transports_list_table_search,
                                     @RequestParam(value = "transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                     @RequestParam(value = "transports_list_table_page", required = false) Long transports_list_table_page,

                                     @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                     @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                     @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                     @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                     @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                     @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                     @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                     @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                     @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<DetailTransportDocFilesList> detailTransportDocFilesList;

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
            detailTransportDocFilesList =  detailTransportDocFilesListRepository.queryTransportDocFileDetailListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_doc_file_id);

            mav.addObject("cnt_id", detailTransportDocFilesList.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportDocFilesList.get(0).cnt_name);

            mav.addObject("cnt_doc_id", detailTransportDocFilesList.get(0).cnt_doc_id);
            mav.addObject("cnt_doc_num", detailTransportDocFilesList.get(0).cnt_doc_num);

            mav.addObject("cnt_doc_file_id", detailTransportDocFilesList.get(0).cnt_doc_file_id);
            mav.addObject("cnt_doc_file_date", detailTransportDocFilesList.get(0).cnt_doc_file_date);
            mav.addObject("cnt_doc_file_name", detailTransportDocFilesList.get(0).cnt_doc_file_name);
        }

        mav.addObject("transports_files_table_order_column", order_column);
        mav.addObject("transports_files_table_order_type", order_type);
        mav.addObject("transports_files_table_search", table_search);
        mav.addObject("transports_files_table_pagelen", pagelen);
        mav.addObject("transports_files_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/files_detail");
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

            @RequestParam(value = "transports_documents_table_order_column", required = false) Long order_column,
            @RequestParam(value = "transports_documents_table_order_type", required = false) String order_type,
            @RequestParam(value = "transports_documents_table_search", required = false) String table_search,
            @RequestParam(value = "transports_documents_table_pagelen", required = false) Long pagelen,
            @RequestParam(value = "transports_documents_table_page", required = false) Long page,

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value = "transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value = "transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value = "transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value = "transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value = "transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    TransportDocFile transport_doc_file = new TransportDocFile();

                    transport_doc_file.cnt_id = cnt_id;
                    transport_doc_file.cnt_doc_id = cnt_doc_id;
                    transport_doc_file.cnt_doc_file_name = file.getOriginalFilename();
                    transport_doc_file.cnt_doc_file_contenttype = file.getContentType();

                    try (InputStream inputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(inputStream);
                        transport_doc_file.cnt_doc_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    transport_doc_file = transportDocFileRepository.save(transport_doc_file);
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

        mav.addObject("cnt_id", cnt_id);
        mav.addObject("cnt_doc_id", cnt_doc_id);

        mav.addObject("transports_documents_table_order_column", order_column);
        mav.addObject("transports_documents_table_order_type", order_type);
        mav.addObject("transports_documents_table_search", table_search);
        mav.addObject("transports_documents_table_pagelen", pagelen);
        mav.addObject("transports_documents_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/documents_detail");
        return mav;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(
            @RequestParam(name = "cnt_doc_file_id") Long cnt_doc_file_id
    ) throws SQLException {

        TransportDocFile transportDocFile = transportDocFileRepository.queryDOCFileByID(cnt_doc_file_id);

        int blobLength = (int) transportDocFile.cnt_doc_file_body.length();
        byte[] output = transportDocFile.cnt_doc_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(transportDocFile.cnt_doc_file_contenttype));
        responseHeaders.setContentLength(output.length);
        try {
            responseHeaders.set("Content-disposition", "attachment; filename="+
                    MimeUtility.encodeWord(transportDocFile.cnt_doc_file_name, "utf-8", "Q")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/get_country_table")
    public JSONDatatable get_country_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuTransportCountryListRepository.queryTransportCountryMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id));
        }
        return result;
    }

    @RequestMapping("/country_detail")
    public ModelAndView country_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="cnt_id") Long cnt_id,
                                       @RequestParam(value="cnt_name") String cnt_name,
                                       @RequestParam(value="cntcl_id") Long cntcl_id,

                                       @RequestParam(name = "req_country_id") Long req_country_id,
                                       @RequestParam(name = "req_address", required = false) String req_address,
                                       @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
                                       @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
                                       @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

                                       @RequestParam(value="transports_country_table_order_column") Long order_column,
                                       @RequestParam(value="transports_country_table_order_type") String order_type,
                                       @RequestParam(value="transports_country_table_search") String table_search,
                                       @RequestParam(value="transports_country_table_pagelen") Long pagelen,
                                       @RequestParam(value="transports_country_table_page") Long page,

                                       @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
                                       @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
                                       @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
                                       @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
                                       @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

                                       @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
                                       @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
                                       @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
                                       @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
                                       @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
                                       @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
                                       @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
                                       @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
                                       @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Country_Full_List> country_Full_List;
        List<DetailTransportCountryList> detailTransportsCountry;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            mav.addObject("cnt_name", cnt_name);

            country_Full_List = country_ListRepository.queryNewTransportContryList(cnt_id);
            mav.addObject("country_list", country_Full_List);
        }
        else{
            detailTransportsCountry = detailTransportsCountryListRepository.queryTransportCountryListDetaulByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cntcl_id);

            mav.addObject("cnt_id", detailTransportsCountry.get(0).cnt_id);
            mav.addObject("cnt_name", detailTransportsCountry.get(0).cnt_name);

            mav.addObject("cntcl_id", detailTransportsCountry.get(0).cntcl_id);
            mav.addObject("cntserl_date", detailTransportsCountry.get(0).cntcl_date);

            mav.addObject("country_id", detailTransportsCountry.get(0).country_id);
            country_Full_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_Full_List);
        }

        mav.addObject("transports_country_table_order_column", order_column);
        mav.addObject("transports_country_table_order_type", order_type);
        mav.addObject("transports_country_table_search", table_search);
        mav.addObject("transports_country_table_pagelen", pagelen);
        mav.addObject("transports_country_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/country_detail");
        return mav;
    }

    @PostMapping("/country_model")
    public ModelAndView country_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "cnt_name", required = false) String cnt_name,

            @RequestParam(name = "cntcl_id", required = false) Long cntcl_id,
            @RequestParam(name = "country_id", required = false) Long country_id,
            @RequestParam(name = "cntcl_date", required = false) String cntcl_date,

            @RequestParam(name = "req_country_id") Long req_country_id,
            @RequestParam(name = "req_address", required = false) String req_address,
            @RequestParam(name = "req_tir_check", required = false, defaultValue = "0") Long req_tir_check,
            @RequestParam(name = "req_adr_check", required = false, defaultValue = "0") Long req_adr_check,
            @RequestParam(name = "req_ekmt_check", required = false, defaultValue = "0") Long req_ekmt_check,

            @RequestParam(value="transports_country_table_order_column", required = false) Long order_column,
            @RequestParam(value="transports_country_table_order_type", required = false) String order_type,
            @RequestParam(value="transports_country_table_search", required = false) String table_search,
            @RequestParam(value="transports_country_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="transports_country_table_page", required = false) Long page,

            @RequestParam(value="transports_list_table_order_column", required = false) Long transports_list_table_order_column,
            @RequestParam(value="transports_list_table_order_type", required = false) String transports_list_table_order_type,
            @RequestParam(value="transports_list_table_search", required = false) String transports_list_table_search,
            @RequestParam(value="transports_list_table_pagelen", required = false) Long transports_list_table_pagelen,
            @RequestParam(value="transports_list_table_page", required = false) Long transports_list_table_page,

            @RequestParam(name = "clients_transports_collapse", required = false) Boolean clients_transports_collapse,
            @RequestParam(name = "clients_trailers_collapse", required = false) Boolean clients_trailers_collapse,
            @RequestParam(name = "clients_drivers_collapse", required = false) Boolean clients_drivers_collapse,
            @RequestParam(name = "clients_contacts_collapse", required = false) Boolean clients_contacts_collapse,
            @RequestParam(name = "clients_country_collapse", required = false) Boolean clients_country_collapse,
            @RequestParam(name = "clients_services_collapse", required = false) Boolean clients_services_collapse,
            @RequestParam(name = "clients_address_collapse", required = false) Boolean clients_address_collapse,
            @RequestParam(name = "clients_documents_collapse", required = false) Boolean clients_documents_collapse,
            @RequestParam(name = "clients_files_collapse", required = false) Boolean clients_files_collapse
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<Country_Full_List> country_Full_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddCountryQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContCountry")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, country_id);
                    AddCountryQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelCountryQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContCountry")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntcl_id);
                    DelCountryQuery.execute();
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

        mav.addObject("cntcl_id", cntcl_id);
        mav.addObject("cntcl_date", cntcl_date);

        if(mode == 0){
            mav.addObject("country_id", country_id);
            country_Full_List = country_ListRepository.queryNewTransportContryList(cnt_id);
            mav.addObject("country_list", country_Full_List);
        }
        else{
            mav.addObject("country_id", country_id);
            country_Full_List = country_ListRepository.queryAllContryList();
            mav.addObject("country_list", country_Full_List);
        }

        mav.addObject("transports_country_table_order_column", order_column);
        mav.addObject("transports_country_table_order_type", order_type);
        mav.addObject("transports_country_table_search", table_search);
        mav.addObject("transports_country_table_pagelen", pagelen);
        mav.addObject("transports_country_table_page", page);

        mav.addObject("req_country_id", req_country_id);
        mav.addObject("req_address", req_address);
        mav.addObject("req_tir_check", req_tir_check);
        mav.addObject("req_adr_check", req_adr_check);
        mav.addObject("req_ekmt_check", req_ekmt_check);

        mav.addObject("clients_transports_collapse", clients_transports_collapse);
        mav.addObject("clients_trailers_collapse", clients_trailers_collapse);
        mav.addObject("clients_drivers_collapse", clients_drivers_collapse);
        mav.addObject("clients_contacts_collapse", clients_contacts_collapse);
        mav.addObject("clients_country_collapse", clients_country_collapse);
        mav.addObject("clients_services_collapse", clients_services_collapse);
        mav.addObject("clients_address_collapse", clients_address_collapse);
        mav.addObject("clients_documents_collapse", clients_documents_collapse);
        mav.addObject("clients_files_collapse", clients_files_collapse);

        mav.addObject("transports_list_table_order_column", transports_list_table_order_column);
        mav.addObject("transports_list_table_order_type", transports_list_table_order_type);
        mav.addObject("transports_list_table_search", transports_list_table_search);
        mav.addObject("transports_list_table_pagelen", transports_list_table_pagelen);
        mav.addObject("transports_list_table_page", transports_list_table_page);

        mav.setViewName("/transports/country_detail");
        return mav;
    }
}

package com.idltd.hydramob.controller.kpi_facts;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.kpi_facts.*;
import com.idltd.hydramob.repository.kpi_facts.cs.*;
import com.idltd.hydramob.repository.kpi_facts.cs_crm.*;
import com.idltd.hydramob.repository.kpi_facts.cs_crm_general.MenuKPIFactsCsCrmClientsNotFillPassRepository;
import com.idltd.hydramob.repository.kpi_facts.cs_crm_general.MenuKPIFactsCsCrmClientsNotFillRepository;
import com.idltd.hydramob.repository.kpi_facts.cs_crm_general.MenuKPIFactsCsCrmClientsOutTimeRepository;
import com.idltd.hydramob.repository.kpi_facts.div_crm.MenuKPIFactsDivCRMGeneralRepository;
import com.idltd.hydramob.repository.kpi_facts.div_crm.MenuKPIFactsDivCRMRequestsRepository;
import com.idltd.hydramob.repository.kpi_facts.div_crm.MenuKPIFactsDivCRMTendersRepository;
import com.idltd.hydramob.repository.kpi_facts.ops.*;
import com.idltd.hydramob.utils.JSONDatatable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kpi_facts")
public class KPIFactsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuKPIFactsMainRepository menuKPIFactsMainRepository;
    private MenuKPIFactsBranchRepository menuKPIFactsBranchRepository;
    private MenuKPIFactsUsersRepository menuKPIFactsUsersRepository;
    private MenuKPIFactsCalcRepository menuKPIFactsCalcRepository;
    //Cs
    private MenuKPIFactsCsMeetsRepository menuKPIFactsMeetsRepository;
    private MenuKPIFactsCsCallsRepository menuKPIFactsCsCallsRepository;
    private MenuKPIFactsCsRequestsRepository menuKPIFactsCsRequestsRepository;
    private MenuKPIFactsCsTendersRepository menuKPIFactsCsTendersRepository;

    private MenuKPIFactsCsClientsFinRepository menuKPIFactsCsClientsFinRepository;
    private MenuKPIFactsCsClientsFinContragentRepository menuKPIFactsCsClientsFinContragentRepository;
    private MenuKPIFactsCsClientsFinDetailRepository menuKPIFactsCsClientsFinDetailRepository;

    private MenuKPIFactsCsClientsProfitRepository menuKPIFactsCsClientsProfitRepository;
    private MenuKPIFactsCsClientsProfitDetailRepository menuKPIFactsCsClientsProfitDetailRepository;
    private MenuKPIFactsCsClientsUnpaidRepository menuKPIFactsCsClientsUnpaidRepository;
    private MenuKPIFactsCsClientsUnpaidDetailRepository menuKPIFactsCsClientsUnpaidDetailRepository;
    private MenuKPIFactsCsClientsDebtorsResultRepository menuKPIFactsCsClientsDebtorsResultRepository;
    private MenuKPIFactsCsClientsDebtorsRepository menuKPIFactsCsClientsDebtorsRepository;
    private MenuKPIFactsCsClientsDebtorsDetailRepository menuKPIFactsCsClientsDebtorsDetailRepository;
    //Cs CRM
    private MenuKPIFactsCsCRMRepository menuKPIFactsCsCRMRepository;
    private MenuKPIFactsCsCRMClientsRepository menuKPIFactsCsCRMClientsRepository;
    private MenuKPIFactsCsCRMTodoRepository menuKPIFactsCsCRMTodoRepository;
    private MenuKPIFactsCsCRMRequestsRepository menuKPIFactsCsCRMRequestsRepository;
    private MenuKPIFactsCsCRMTendersRepository menuKPIFactsCsCRMTendersRepository;
    private MenuKPIFactsCsCRMToplistRepository menuKPIFactsCsCRMToplistRepository;
    //CS CRM General
    private MenuKPIFactsCsCRMGeneralRepository menuKPIFactsCsCRMGeneralRepository;
    private MenuKPIFactsCsCrmClientsOutTimeRepository menuKPIFactsCsCrmClientsOutTimeRepository;
    private MenuKPIFactsCsCrmClientsNotFillPassRepository menuKPIFactsCsCrmClientsNotFillPassRepository;
    private MenuKPIFactsCsCrmClientsNotFillRepository menuKPIFactsCsCrmClientsNotFillRepository;
    //Chief Cs
    private MenuKPIFactsChiefCsCRMRepository menuKPIFactsChiefCsCRMRepository;
    //Ops
    private MenuKPIFactsOpsClientsFinNewRepository menuKPIFactsOpsClientsFinNewRepository;
    private MenuKPIFactsOpsClientsFinNewDetailRepository menuKPIFactsOpsClientsFinNewDetailRepository;
    private MenuKPIFactsOpsClientsFinOldRepository menuKPIFactsOpsClientsFinOldRepository;
    private MenuKPIFactsOpsClientsFinOldDetailRepository menuKPIFactsOpsClientsFinOldDetailRepository;
    private MenuKPIFactsOpsClientsFinAllRepository menuKPIFactsOpsClientsFinAllRepository;
    private MenuKPIFactsOpsClientsFinAllDetailRepository menuKPIFactsOpsClientsFinAllDetailRepository;
    private MenuKPIFactsOpsClientsFinCountRepository menuKPIFactsOpsClientsFinCountRepository;
    private MenuKPIFactsOpsClientsFinCountDetailRepository menuKPIFactsOpsClientsFinCountDetailRepository;
    //Div CRM
    private MenuKPIFactsDivCRMGeneralRepository menuKPIFactsDivCRMGeneralRepository;
    private MenuKPIFactsDivCRMRequestsRepository menuKPIFactsDivCRMRequestsRepository;
    private MenuKPIFactsDivCRMTendersRepository menuKPIFactsDivCRMTendersRepository;

    public KPIFactsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuKPIFactsMainRepository menuKPIFactsMainRepository,
            MenuKPIFactsBranchRepository menuKPIFactsBranchRepository,
            MenuKPIFactsUsersRepository menuKPIFactsUsersRepository,
            MenuKPIFactsCalcRepository menuKPIFactsCalcRepository,

            MenuKPIFactsCsMeetsRepository menuKPIFactsMeetsRepository,
            MenuKPIFactsCsCallsRepository menuKPIFactsCsCallsRepository,
            MenuKPIFactsCsRequestsRepository menuKPIFactsCsRequestsRepository,
            MenuKPIFactsCsTendersRepository menuKPIFactsCsTendersRepository,

            MenuKPIFactsCsClientsFinRepository menuKPIFactsCsClientsFinRepository,
            MenuKPIFactsCsClientsFinContragentRepository menuKPIFactsCsClientsFinContragentRepository,
            MenuKPIFactsCsClientsFinDetailRepository menuKPIFactsCsClientsFinDetailRepository,

            MenuKPIFactsCsClientsProfitRepository menuKPIFactsCsClientsProfitRepository,
            MenuKPIFactsCsClientsProfitDetailRepository menuKPIFactsCsClientsProfitDetailRepository,
            MenuKPIFactsCsClientsUnpaidRepository menuKPIFactsCsClientsUnpaidRepository,
            MenuKPIFactsCsClientsUnpaidDetailRepository menuKPIFactsCsClientsUnpaidDetailRepository,
            MenuKPIFactsCsClientsDebtorsResultRepository menuKPIFactsCsClientsDebtorsResultRepository,
            MenuKPIFactsCsClientsDebtorsRepository menuKPIFactsCsClientsDebtorsRepository,
            MenuKPIFactsCsClientsDebtorsDetailRepository menuKPIFactsCsClientsDebtorsDetailRepository,

            MenuKPIFactsCsCRMRepository menuKPIFactsCsCRMRepository,
            MenuKPIFactsCsCRMClientsRepository menuKPIFactsCsCRMClientsRepository,
            MenuKPIFactsCsCRMTodoRepository menuKPIFactsCsCRMTodoRepository,
            MenuKPIFactsCsCRMRequestsRepository menuKPIFactsCsCRMRequestsRepository,
            MenuKPIFactsCsCRMTendersRepository menuKPIFactsCsCRMTendersRepository,
            MenuKPIFactsCsCRMToplistRepository menuKPIFactsCsCRMToplistRepository,

            MenuKPIFactsCsCRMGeneralRepository menuKPIFactsCsCRMGeneralRepository,
            MenuKPIFactsCsCrmClientsOutTimeRepository menuKPIFactsCsCrmClientsOutTimeRepository,
            MenuKPIFactsCsCrmClientsNotFillPassRepository menuKPIFactsCsCrmClientsNotFillPassRepository,
            MenuKPIFactsCsCrmClientsNotFillRepository menuKPIFactsCsCrmClientsNotFillRepository,

            MenuKPIFactsChiefCsCRMRepository menuKPIFactsChiefCsCRMRepository,

            MenuKPIFactsOpsClientsFinNewRepository menuKPIFactsOpsClientsFinNewRepository,
            MenuKPIFactsOpsClientsFinNewDetailRepository menuKPIFactsOpsClientsFinNewDetailRepository,
            MenuKPIFactsOpsClientsFinOldRepository menuKPIFactsOpsClientsFinOldRepository,
            MenuKPIFactsOpsClientsFinOldDetailRepository menuKPIFactsOpsClientsFinOldDetailRepository,
            MenuKPIFactsOpsClientsFinAllRepository menuKPIFactsOpsClientsFinAllRepository,
            MenuKPIFactsOpsClientsFinAllDetailRepository menuKPIFactsOpsClientsFinAllDetailRepository,
            MenuKPIFactsOpsClientsFinCountRepository menuKPIFactsOpsClientsFinCountRepository,
            MenuKPIFactsOpsClientsFinCountDetailRepository menuKPIFactsOpsClientsFinCountDetailRepository,

            MenuKPIFactsDivCRMGeneralRepository menuKPIFactsDivCRMGeneralRepository,
            MenuKPIFactsDivCRMRequestsRepository menuKPIFactsDivCRMRequestsRepository,
            MenuKPIFactsDivCRMTendersRepository menuKPIFactsDivCRMTendersRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuKPIFactsMainRepository = menuKPIFactsMainRepository;
        this.menuKPIFactsBranchRepository = menuKPIFactsBranchRepository;
        this.menuKPIFactsUsersRepository = menuKPIFactsUsersRepository;
        this.menuKPIFactsCalcRepository = menuKPIFactsCalcRepository;

        this.menuKPIFactsMeetsRepository = menuKPIFactsMeetsRepository;
        this.menuKPIFactsCsCallsRepository = menuKPIFactsCsCallsRepository;
        this.menuKPIFactsCsRequestsRepository = menuKPIFactsCsRequestsRepository;
        this.menuKPIFactsCsTendersRepository = menuKPIFactsCsTendersRepository;

        this.menuKPIFactsCsClientsFinRepository = menuKPIFactsCsClientsFinRepository;
        this.menuKPIFactsCsClientsFinContragentRepository = menuKPIFactsCsClientsFinContragentRepository;
        this.menuKPIFactsCsClientsFinDetailRepository = menuKPIFactsCsClientsFinDetailRepository;

        this.menuKPIFactsCsClientsProfitRepository = menuKPIFactsCsClientsProfitRepository;
        this.menuKPIFactsCsClientsProfitDetailRepository = menuKPIFactsCsClientsProfitDetailRepository;
        this.menuKPIFactsCsClientsUnpaidRepository = menuKPIFactsCsClientsUnpaidRepository;
        this.menuKPIFactsCsClientsUnpaidDetailRepository = menuKPIFactsCsClientsUnpaidDetailRepository;
        this.menuKPIFactsCsClientsDebtorsResultRepository = menuKPIFactsCsClientsDebtorsResultRepository;
        this.menuKPIFactsCsClientsDebtorsRepository = menuKPIFactsCsClientsDebtorsRepository;
        this.menuKPIFactsCsClientsDebtorsDetailRepository = menuKPIFactsCsClientsDebtorsDetailRepository;

        this.menuKPIFactsCsCRMRepository = menuKPIFactsCsCRMRepository;
        this.menuKPIFactsCsCRMClientsRepository = menuKPIFactsCsCRMClientsRepository;
        this.menuKPIFactsCsCRMTodoRepository = menuKPIFactsCsCRMTodoRepository;
        this.menuKPIFactsCsCRMRequestsRepository = menuKPIFactsCsCRMRequestsRepository;
        this.menuKPIFactsCsCRMTendersRepository = menuKPIFactsCsCRMTendersRepository;
        this.menuKPIFactsCsCRMToplistRepository = menuKPIFactsCsCRMToplistRepository;

        this.menuKPIFactsCsCRMGeneralRepository = menuKPIFactsCsCRMGeneralRepository;
        this.menuKPIFactsCsCrmClientsOutTimeRepository = menuKPIFactsCsCrmClientsOutTimeRepository;
        this.menuKPIFactsCsCrmClientsNotFillPassRepository = menuKPIFactsCsCrmClientsNotFillPassRepository;
        this.menuKPIFactsCsCrmClientsNotFillRepository = menuKPIFactsCsCrmClientsNotFillRepository;

        this.menuKPIFactsChiefCsCRMRepository = menuKPIFactsChiefCsCRMRepository;

        this.menuKPIFactsOpsClientsFinNewRepository = menuKPIFactsOpsClientsFinNewRepository;
        this.menuKPIFactsOpsClientsFinNewDetailRepository = menuKPIFactsOpsClientsFinNewDetailRepository;
        this.menuKPIFactsOpsClientsFinOldRepository = menuKPIFactsOpsClientsFinOldRepository;
        this.menuKPIFactsOpsClientsFinOldDetailRepository = menuKPIFactsOpsClientsFinOldDetailRepository;
        this.menuKPIFactsOpsClientsFinAllRepository = menuKPIFactsOpsClientsFinAllRepository;
        this.menuKPIFactsOpsClientsFinAllDetailRepository = menuKPIFactsOpsClientsFinAllDetailRepository;
        this.menuKPIFactsOpsClientsFinCountRepository = menuKPIFactsOpsClientsFinCountRepository;
        this.menuKPIFactsOpsClientsFinCountDetailRepository = menuKPIFactsOpsClientsFinCountDetailRepository;

        this.menuKPIFactsDivCRMGeneralRepository = menuKPIFactsDivCRMGeneralRepository;
        this.menuKPIFactsDivCRMRequestsRepository = menuKPIFactsDivCRMRequestsRepository;
        this.menuKPIFactsDivCRMTendersRepository = menuKPIFactsDivCRMTendersRepository;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model
    ){
        model.setViewName("kpi_facts/cover");
        return model;
    }

    @PostMapping("/main_table")
    public JSONDatatable main_table(
            @RequestParam(name = "kpi_facts_year", required = false, defaultValue = "0") Long kpi_facts_year
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuKPIFactsMainRepository.queryKPIFactsMainAll(
                user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_year
        ));

        return result;
    }

    @PostMapping("/branch_table")
    public JSONDatatable branch_table(
            @RequestParam(name = "kpi_facts_year", required = false, defaultValue = "0") Long kpi_facts_year,
            @RequestParam(name = "kpi_dep_id", required = false) Long kpi_dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if( kpi_dep_id != null && kpi_dep_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsBranchRepository.queryKPIFactsBrnachById(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_year, kpi_dep_id
            ));
        }
        return result;
    }

    @PostMapping("/users_table")
    public JSONDatatable users_table(
            @RequestParam(name = "kpi_facts_year", required = false, defaultValue = "0") Long kpi_facts_year,
            @RequestParam(name = "kpi_main_dep_id", required = false) Long kpi_main_dep_id,
            @RequestParam(name = "kpi_dep_id", required = false) Long kpi_dep_id

    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if( kpi_dep_id != null && kpi_dep_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsUsersRepository.queryKPIFactsUsersAll(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_year, kpi_dep_id, kpi_main_dep_id
            ));
        }
        return result;
    }

    @PostMapping("/get_calc_table")
    public JSONDatatable get_calc_table(
            @RequestParam(name = "kpi_user_id", required = false) Long kpi_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "dep_id", required = false) Long dep_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_user_id != null && kpi_facts_year != null && kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCalcRepository.queryUserKPICalcByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_user_id, kpi_facts_year, kpi_facts_month, dep_id
            ));
        }
        return result;
    }

    //CS Meets
    @PostMapping("/cs_meets_menu")
    public JSONDatatable cs_meets_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_year != null && kpi_facts_month != null &&  kpi_facts_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsMeetsRepository.queryMenuKPIFactsCsMeetsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CS Calls
    @PostMapping("/cs_calls_menu")
    public JSONDatatable cs_calls_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_year != null && kpi_facts_month != null &&  kpi_facts_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCallsRepository.queryMenuKPIFactsCsCallsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CS Requests
    @PostMapping("/cs_requests_menu")
    public JSONDatatable cs_requests_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsRequestsRepository.queryMenuKPIFactsCsRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS Tenders
    @PostMapping("/cs_tenders_menu")
    public JSONDatatable cs_tenders_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsTendersRepository.queryMenuKPIFactsCsTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS All Finance
    @PostMapping("/cs_clients_finance_menu")
    public JSONDatatable cs_clients_finance_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsFinRepository.queryMenuKPIFactsCsClientsFinByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS All Finance
    @PostMapping("/cs_clients_finance_contragent_menu")
    public JSONDatatable cs_clients_finance_contragent_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsFinContragentRepository.queryMenuKPIFactsCsClientsFinContragentByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_ser_id
            ));
        }
        return result;
    }

    //CS All Finance Detail
    @PostMapping("/cs_clients_finance_detail_menu")
    public JSONDatatable cs_clients_finance_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsFinDetailRepository.queryMenuKPIFactsCleintsFinDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }
    //CS All Profit
    @PostMapping("/cs_clients_profit_menu")
    public JSONDatatable cs_clients_profit_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsProfitRepository.queryMenuKPIFactsCsClientsProfitByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS All Finance Detail
    @PostMapping("/cs_clients_profit_detail_menu")
    public JSONDatatable cs_clients_profit_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsProfitDetailRepository.queryMenuKPIFactsCsClientsProfitDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }
    //CS Unpaid Finance
    @PostMapping("/cs_clients_unpaid_menu")
    public JSONDatatable cs_clients_unpaid_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsUnpaidRepository.queryMenuKPIFactsCsClientsUnpaidByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS Unpaid Finance Detail
    @PostMapping("/cs_clients_unpaid_detail_menu")
    public JSONDatatable cs_clients_unpaid_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsUnpaidDetailRepository.queryMenuKPIFactsCsClientsUnpaidDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }


    @PostMapping("/cs_clients_debtors_result_menu")
    public JSONDatatable cs_clients_debtors_result_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsDebtorsResultRepository.queryMenuKPIFactsCsClientsDebtorsResultByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CS Debtors Finance
    @PostMapping("/cs_clients_debtors_menu")
    public JSONDatatable cs_clients_debtors_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_res_month", required = false) Long kpi_facts_res_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsDebtorsRepository.queryMenuKPIFactsCsClientsDebtorsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_res_month
            ));
        }
        return result;
    }

    //CS Debtors Finance Detail
    @PostMapping("/cs_clients_debtors_detail_menu")
    public JSONDatatable cs_clients_debtors_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id,
            @RequestParam(name = "kpi_facts_res_month", required = false) Long kpi_facts_res_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsClientsDebtorsDetailRepository.queryMenuKPIFactsCsClientsDebtorsDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id, kpi_facts_res_month
            ));
        }
        return result;
    }

    //Cs CRM
    @PostMapping("/cs_crm_menu")
    public JSONDatatable cs_crm_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if( kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
            if(kpi_facts_param_id == 5) {
                result.setData(menuKPIFactsCsCRMRepository.queryUserKPICsCRMByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
                ));
            }
            else if(kpi_facts_param_id == 13){
                result.setData(menuKPIFactsChiefCsCRMRepository.queryUserKPIChiefCsCRMByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
                ));
            }
        }
        return result;
    }
    //CS CRM Client Card
    @PostMapping("/cs_crm_clients_menu")
    public JSONDatatable cs_crm_clients_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMClientsRepository.queryMenuKPIFactsCsCRMClientsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //CS CRM TodoList
    @PostMapping("/cs_crm_todo_menu")
    public JSONDatatable cs_crm_todo_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMTodoRepository.queryMenuKPIFactsCsCRMTodoByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CS CRM Requests
    @PostMapping("/cs_crm_requests_menu")
    public JSONDatatable cs_crm_requests_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMRequestsRepository.queryMenuKPIFactsCsCRMRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CS CRM Tenders
    @PostMapping("/cs_crm_tenders_menu")
    public JSONDatatable cs_crm_tenders_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMTendersRepository.queryMenuKPIFactsCsCRMTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CRM CS Toplist List
    @PostMapping("/cs_crm_toplist_menu")
    public JSONDatatable cs_crm_toplist_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMToplistRepository.queryMenuKPIFactsCsCRMToplistByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    //CRM CS General List
    @PostMapping("/cs_crm_general_menu")
    public JSONDatatable cs_crm_general_menu(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCRMGeneralRepository.queryMenuKPIFactsCsCRMGeneralByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @GetMapping("/cs_report")
    public void cs_report(
            @RequestParam(name = "req_year", required = false) Long req_year,
            @RequestParam(name = "req_month", required = false) Long req_month,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws JRException, IOException, SQLException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<User_Role_List> user_Role_List;

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(User_List.get(0).id);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_33_xls.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("ROLE_ID", user_Role_List.get(0).role_id.intValue());
        params.put("YEAR", req_year.intValue());
        params.put("MONTH", req_month.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=WMS.Zammler.SaleKPI_"+req_month+"."+req_year+".xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }

    @GetMapping("/ops_report")
    public void ops_report(
            @RequestParam(name = "req_year", required = false) Long req_year,
            @RequestParam(name = "req_month", required = false) Long req_month,
            HttpServletResponse response,
            HttpServletRequest request
    ) throws JRException, IOException, SQLException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<User_Role_List> user_Role_List;

        List<User_List> User_List = (List<User_List>) user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(User_List.get(0).id);

        InputStream jasperStream = this.getClass().getResourceAsStream("/static/report/report_36.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("USER_ID", User_List.get(0).id.intValue());
        params.put("ROLE_ID", user_Role_List.get(0).role_id.intValue());
        params.put("YEAR", req_year.intValue());
        params.put("MONTH", req_month.intValue());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=WMS.Zammler.OPSKPI_"+req_month+"."+req_year+".xls");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
    }

// CS Finance Add Block
    @PostMapping("/add_cs_clients_finance_detail_block")
    public JSONDatatable add_cs_clients_finance_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIFinanceBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIFinanceBlockQuery.execute();
        }
        return null;
    }
// CS Finance Del Block
    @PostMapping("/del_cs_clients_finance_detail_block")
    public JSONDatatable del_cs_clients_finance_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIFinanceBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Finance Edit Comment
    @PostMapping("/add_comment_cs_clients_finance_detail_block")
    public JSONDatatable add_comment_cs_clients_finance_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentDEPKPIFinanceBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }
// CS Profit Add Block
    @PostMapping("/add_cs_clients_profit_detail_block")
    public JSONDatatable add_cs_clients_profit_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIProfitBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Profit Del Block
    @PostMapping("/del_cs_clients_profit_detail_block")
    public JSONDatatable del_cs_clients_profit_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIProfitBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

    // CS Profit Edit Comment
    @PostMapping("/add_comment_cs_clients_profit_detail_block")
    public JSONDatatable add_comment_cs_clients_profit_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentDEPKPIProfitBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Debtors Add Block
    @PostMapping("/add_cs_clients_debtors_detail_block")
    public JSONDatatable add_cs_clients_debtors_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIDebtorsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Debtors Del Block
    @PostMapping("/del_cs_clients_debtors_detail_block")
    public JSONDatatable del_cs_clients_debtors_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIDebtorsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

    // CS Debtors Edit Comment
    @PostMapping("/add_comment_cs_clients_debtors_detail_block")
    public JSONDatatable add_comment_cs_clients_debtors_detail_block(
            @RequestParam(name = "block_fin_id", required = false) Long block_fin_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_fin_id != null && block_fin_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentDEPKPIDebtorsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_fin_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Meets Add Block
    @PostMapping("/add_cs_meets_block")
    public JSONDatatable add_meets_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIMeetsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIMeetsBlockQuery.execute();
        }
        return null;
    }
// CS Meets Del Block
    @PostMapping("/del_cs_meets_block")
    public JSONDatatable del_cs_meets_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIMeetsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIMeetsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIMeetsBlockQuery.execute();
        }
        return null;
    }

    // CS Meets Edit Comment
    @PostMapping("/add_comment_cs_meets_block")
    public JSONDatatable add_comment_cs_meets_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentCsMeetsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Calls Add Block
    @PostMapping("/add_cs_calls_block")
    public JSONDatatable add_cs_calls_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPICallsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICallsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPICallsBlockQuery.execute();
        }
        return null;
    }

// CS Calls Del Block
    @PostMapping("/del_cs_calls_block")
    public JSONDatatable del_cs_calls_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPICallsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPICallsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPICallsBlockQuery.execute();
        }
        return null;
    }

    // CS Calls Edit Comment
    @PostMapping("/add_comment_cs_calls_block")
    public JSONDatatable add_comment_cs_calls_block(
            @RequestParam(name = "block_act_id", required = false) Long block_act_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_act_id != null && block_act_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentCsCallsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_act_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Request Add Block
    @PostMapping("/add_requests_block")
    public JSONDatatable add_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPIRequestsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPIRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPIRequestsBlockQuery.execute();
        }
        return null;
    }
// CS Request Del Block
    @PostMapping("/del_requests_block")
    public JSONDatatable del_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIRequestsBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPIRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPIRequestsBlockQuery.execute();
        }
        return null;
    }

    // CS Request Edit Comment
    @PostMapping("/add_comment_cs_requests_block")
    public JSONDatatable add_comment_cs_requests_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentCsRequestsBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

// CS Tender Add Block
    @PostMapping("/add_tenders_block")
    public JSONDatatable add_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPITendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPITendersBlockQuery.execute();
        }
        return null;
    }
// CS Tender Del Block
    @PostMapping("/del_tenders_block")
    public JSONDatatable del_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPITendersBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPITendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPITendersBlockQuery.execute();
        }
        return null;
    }

    // CS Tender Edit Comment
    @PostMapping("/add_comment_cs_tenders_block")
    public JSONDatatable add_comment_cs_tenders_block(
            @RequestParam(name = "block_req_id", required = false) Long block_req_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month,
            @RequestParam(name = "block_note", required = false) String block_note
    ) {
        if(block_req_id != null && block_req_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPIFinanceBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddCommentCsTendersBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_req_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    .setParameter(7, block_note)
                    ;
            DelKPIFinanceBlockQuery.execute();
        }
        return null;
    }

    //Clients NotFill
    @PostMapping("/get_notfill_table")
    public JSONDatatable get_notfill_table(
            @RequestParam(name = "kpi_facts_general_param_id", required = false) Long kpi_facts_general_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_general_param_id != null &&  kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCrmClientsNotFillRepository.queryUserKPICRMKPIFactsClientsNotFill(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    @PostMapping("/add_notfill_block")
    public JSONDatatable add_notfill_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmNotfillBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPINotfillBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_notfill_block")
    public JSONDatatable del_notfill_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPINotfillBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPINotfillBlockQuery.execute();
        }
        return null;
    }

    //Clients NotFill Pass
    @PostMapping("/get_notfill_pass_table")
    public JSONDatatable get_notfill_pass_table(
            @RequestParam(name = "kpi_facts_general_param_id", required = false) Long kpi_facts_general_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_general_param_id != null &&  kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCrmClientsNotFillPassRepository.queryUserKPICRMKPIFactsClientsNotFillPass(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }

    @PostMapping("/add_notfill_pass_block")
    public JSONDatatable add_notfill_pass_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery AddKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddDEPKPICrmNotfillPassBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            AddKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    @PostMapping("/del_notfill_pass_block")
    public JSONDatatable del_notfill_pass_block(
            @RequestParam(name = "block_cnt_id", required = false) Long block_cnt_id,
            @RequestParam(name = "block_user_id", required = false) Long block_user_id,
            @RequestParam(name = "block_year", required = false) Long block_year,
            @RequestParam(name = "block_month", required = false) Long block_month
    ) {
        if(block_cnt_id != null && block_cnt_id > 0 && block_user_id != null && block_user_id > 0 && block_year != null && block_year >= 0 && block_month != null && block_month >= 0)
        {
            List<User_List> user_List;
            List<User_Role_List> user_Role_List;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            StoredProcedureQuery DelKPINotfillPassBlockQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelDEPKPINotfillPassBlock")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, block_cnt_id)
                    .setParameter(4, block_user_id)
                    .setParameter(5, block_year)
                    .setParameter(6, block_month)
                    ;
            DelKPINotfillPassBlockQuery.execute();
        }
        return null;
    }

    //Clients OutTime
    @PostMapping("/get_outtime_table")
    public JSONDatatable get_outtime_table(
            @RequestParam(name = "kpi_facts_general_param_id", required = false) Long kpi_facts_general_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_general_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsCsCrmClientsOutTimeRepository.queryUserKPICsCRMKPIFactsClientsOutTime(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //OPS New Finance
    @PostMapping("/ops_clients_finance_new_menu")
    public JSONDatatable ops_clients_finance_new_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinNewRepository.queryMenuKPIFactsOpsClientsFinNewByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //OPS New Finance Detail
    @PostMapping("/ops_clients_finance_new_detail_menu")
    public JSONDatatable ops_clients_finance_new_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinNewDetailRepository.queryMenuKPIFactsOpsCleintsFinNewDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }
    //OPS New Finance
    @PostMapping("/ops_clients_finance_old_menu")
    public JSONDatatable ops_clients_finance_old_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinOldRepository.queryMenuKPIFactsOpsClientsFinOldByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //OPS New Finance Detail
    @PostMapping("/ops_clients_finance_old_detail_menu")
    public JSONDatatable ops_clients_finance_old_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinOldDetailRepository.queryMenuKPIFactsOpsCleintsFinOldDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }
    //OPS All Finance
    @PostMapping("/ops_clients_finance_all_menu")
    public JSONDatatable ops_clients_finance_all_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinAllRepository.queryMenuKPIFactsOpsClientsFinAllByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //OPS All Finance Detail
    @PostMapping("/ops_clients_finance_all_detail_menu")
    public JSONDatatable ops_clients_finance_all_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id,
            @RequestParam(name = "kpi_facts_ser_id", required = false) Long kpi_facts_ser_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null && kpi_facts_ser_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinAllDetailRepository.queryMenuKPIFactsOpsCleintsFinAllDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id, kpi_facts_ser_id
            ));
        }
        return result;
    }

    //OPS All Finance
    @PostMapping("/ops_clients_finance_count_menu")
    public JSONDatatable ops_clients_finance_count_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null &&  kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinCountRepository.queryMenuKPIFactsOpsClientsFinCountByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
            ));
        }
        return result;
    }
    //OPS All Finance Detail
    @PostMapping("/ops_clients_finance_count_detail_menu")
    public JSONDatatable ops_clients_finance_count_detail_menu(
            @RequestParam(name = "kpi_facts_param_id", required = false) Long kpi_facts_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month,
            @RequestParam(name = "kpi_facts_cnt_id", required = false) Long kpi_facts_cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_facts_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null && kpi_facts_cnt_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsOpsClientsFinCountDetailRepository.queryMenuKPIFactsOpsCleintsFinCountDetailByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month, kpi_facts_cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/get_div_crm_table")
    public JSONDatatable get_div_crm_table(
            @RequestParam(name = "kpi_facts_crm_param_id", required = false) Long kpi_facts_crm_param_id,
            @RequestParam(name = "kpi_facts_user_id", required = false) Long kpi_facts_user_id,
            @RequestParam(name = "kpi_facts_year", required = false) Long kpi_facts_year,
            @RequestParam(name = "kpi_facts_month", required = false) Long kpi_facts_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if( kpi_facts_crm_param_id != null && kpi_facts_user_id != null && kpi_facts_year != null && kpi_facts_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
            if(kpi_facts_crm_param_id == 5) {
                result.setData(menuKPIFactsDivCRMGeneralRepository.queryUserKPICRMChiefMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
                ));
            }
            else if(kpi_facts_crm_param_id == 13){
                result.setData(menuKPIFactsDivCRMGeneralRepository.queryUserKPICRMChiefMainListByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, kpi_facts_user_id, kpi_facts_year, kpi_facts_month
                ));
            }
        }
        return result;
    }

    //DIV CRM Requests
    @PostMapping("/div_crm_requests_menu")
    public JSONDatatable div_crm_requests_menu(
            @RequestParam(name = "kpi_fact_crm_param_id", required = false) Long kpi_fact_crm_param_id,
            @RequestParam(name = "kpi_fact_user_id", required = false) Long kpi_fact_user_id,
            @RequestParam(name = "kpi_fact_year", required = false) Long kpi_fact_year,
            @RequestParam(name = "kpi_fact_month", required = false) Long kpi_fact_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_fact_crm_param_id != null && kpi_fact_user_id != null && kpi_fact_year != null &&  kpi_fact_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsDivCRMRequestsRepository.queryMenuDepKPIFactCRMRequestsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_fact_user_id, kpi_fact_year, kpi_fact_month
            ));
        }
        return result;
    }

    //Div CRM Tenders
    @PostMapping("/div_crm_tenders_menu")
    public JSONDatatable div_crm_tenders_menu(
            @RequestParam(name = "kpi_fact_crm_param_id", required = false) Long kpi_fact_crm_param_id,
            @RequestParam(name = "kpi_fact_user_id", required = false) Long kpi_fact_user_id,
            @RequestParam(name = "kpi_fact_year", required = false) Long kpi_fact_year,
            @RequestParam(name = "kpi_fact_month", required = false) Long kpi_fact_month
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(kpi_fact_crm_param_id != null && kpi_fact_user_id != null && kpi_fact_year != null &&  kpi_fact_month != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuKPIFactsDivCRMTendersRepository.queryMenuDepKPIFactCRMTendersByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, kpi_fact_user_id, kpi_fact_year, kpi_fact_month
            ));
        }
        return result;
    }

    @PostMapping("/edit_fact_plan")
    public ModelAndView edit_fact_plan(
            @RequestParam(name = "kpirupl_id") Long kpirupl_id,
            @RequestParam(name = "kpirupl_plan") String kpirupl_plan
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery editFactPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditDEP_KPI_PLAN_FORM_FACT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, kpirupl_id)
                    .setParameter(4, kpirupl_plan)
                    ;
            editFactPlanQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_fact_malus")
    public ModelAndView edit_fact_malus(
            @RequestParam(name = "kpirupl_id") Long kpirupl_id,
            @RequestParam(name = "kpirupl_malus") String kpirupl_malus
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery editFactPlanQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditDEP_KPI_MALUS_FORM_FACT")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, kpirupl_id)
                    .setParameter(4, kpirupl_malus)
                    ;
            editFactPlanQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}